package com.application.muhammadshiraz.ui.article

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.application.muhammadshiraz.R
import com.application.muhammadshiraz.base.BaseViewModel
import com.application.muhammadshiraz.model.Article
import com.application.muhammadshiraz.model.ArticleDao
import com.application.muhammadshiraz.model.ArticleItem
import com.application.muhammadshiraz.network.NYTimesApi
import com.application.muhammadshiraz.utils.API_KEY
import javax.inject.Inject
import java.text.SimpleDateFormat


class ArticlesListViewModel(private val articleDao: ArticleDao): BaseViewModel(){
    @Inject
    lateinit var nyTimesApi: NYTimesApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val articleSelected:MutableLiveData<ArticleItem> = MutableLiveData()

    val errorClickListener = View.OnClickListener { loadArticles("7",API_KEY) }
    val  articleListAdapter = ArticlesListAdapter {

        var articleitem:ArticleItem = ArticleItem(it.id,it.title,it.dateTime,it.title,it.image,it.description)

         articleSelected.value =articleitem}


    private lateinit var subscription: Disposable

    init{
        loadArticles("7",API_KEY)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadArticles(part:String, apiKey :String){
        subscription = Observable.fromCallable { articleDao.all }
                .concatMap {
                    dbArticleList ->
                        if(dbArticleList.isEmpty())

                            //Adding data in database
                            nyTimesApi.getArticles(part, apiKey).concatMap { apiArticleList ->

                                    val  articleList = ArrayList<Article>()
                                    for (results in  apiArticleList.results){

                                        var imgUrl:String =""
                                        for (media in  results.media) {
                                            for (item in  media.`media-metadata`){
                                                imgUrl =item.url
                                            }
                                        }

                                        val article = Article(results.id, results.title, results.published_date, results.title, imgUrl, results.abstract)

                                        articleDao.insertAll(article)
                                        articleList.add(article)
                                    }
                                Observable.just(articleList)

                        }
                        else  Observable.just(dbArticleList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveArticleListStart() }
                .doOnTerminate { onRetrieveArticleListFinish() }
                .subscribe(
                        { result ->
                        //observing data
                                onRetrieveArticlesListSuccess(result as List<Article>)

                        },
                        { onRetrieveArticleListError() }
                )
    }


    private fun onRetrieveArticleListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveArticleListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveArticlesListSuccess(articleList:List<Article>){
        articleListAdapter.updateArticleList(articleList)
    }

    private fun onRetrieveArticleListError(){
        errorMessage.value = R.string.article_error
    }
}