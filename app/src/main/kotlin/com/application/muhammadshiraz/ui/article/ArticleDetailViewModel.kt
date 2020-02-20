package com.application.muhammadshiraz.ui.article

import android.arch.lifecycle.MutableLiveData
import com.application.muhammadshiraz.base.BaseViewModel
import com.application.muhammadshiraz.model.Article

class ArticleDetailViewModel: BaseViewModel() {
    private val articleImage = MutableLiveData<String>()
    private val articleTitle = MutableLiveData<String>()
    private val articleDate = MutableLiveData<String>()
    private val articleDescription = MutableLiveData<String>()

    fun bind(article: Article){
        articleTitle.value = article.title
        articleDate.value = "Published Date : "+article.dateTime
        articleDescription.value = article.description
        articleImage.value = article.image
    }

    fun getArticleTitle():MutableLiveData<String>{
        return articleTitle
    }


    fun getArticleDate():MutableLiveData<String>{
        return  articleDate
    }
    fun getArticleDescription():MutableLiveData<String>{
        return articleDescription
    }
    fun getArticleImage():MutableLiveData<String>{
        return articleImage
    }
}