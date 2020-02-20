package com.application.muhammadshiraz.ui.article

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import com.application.muhammadshiraz.R
import com.application.muhammadshiraz.databinding.ActivityArticleDetailBinding
import com.application.muhammadshiraz.injection.ViewModelFactory
import com.application.muhammadshiraz.model.Article
import com.application.muhammadshiraz.model.ArticleItem
import com.application.muhammadshiraz.utils.ARTICLE_SELECTED

class ArticleDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private lateinit var viewModel: ArticleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ArticleDetailViewModel::class.java)

        var articleItem =intent.extras.getSerializable(ARTICLE_SELECTED) as ArticleItem

        var article: Article = Article(articleItem.id,articleItem.title,articleItem.dateTime,articleItem.subject,articleItem.image,articleItem.description)
        viewModel.bind(article)
        binding.viewModel =viewModel

    }

}