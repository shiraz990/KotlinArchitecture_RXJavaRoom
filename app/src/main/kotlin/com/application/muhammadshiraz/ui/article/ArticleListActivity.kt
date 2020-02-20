package com.application.muhammadshiraz.ui.article

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.application.muhammadshiraz.R
import com.application.muhammadshiraz.databinding.ActivityArticleListBinding
 import com.application.muhammadshiraz.injection.ViewModelFactory
import com.application.muhammadshiraz.utils.ARTICLE_SELECTED

class ArticleListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityArticleListBinding
    private lateinit var viewModel: ArticlesListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list)
        binding.articleList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ArticlesListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.articleSelected.observe(this, Observer {

            var bundle = Bundle()
            bundle.putSerializable(ARTICLE_SELECTED,it!!)
            navigateToDetailActivity(this,bundle)
            //Toast.makeText(this,it!!.title.toString(),Toast.LENGTH_LONG).show()

         })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }


    fun navigateToDetailActivity(activity: AppCompatActivity, bundle: Bundle) {
        val intent = Intent(activity, ArticleDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(activity, intent)
        activity.overridePendingTransition(R.anim.fade_in, R.anim.layout_animation)
    }


    private fun startActivity(activity: AppCompatActivity, intent: Intent) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
    }
    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}