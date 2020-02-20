package com.application.muhammadshiraz.injection.component

import dagger.Component
import com.application.muhammadshiraz.injection.module.NetworkModule
import com.application.muhammadshiraz.ui.article.ArticleDetailViewModel
import com.application.muhammadshiraz.ui.article.ArticleViewModel
import com.application.muhammadshiraz.ui.article.ArticlesListViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified ArticleListViewModel.
     * @param ArticleListViewModel ArticleListViewModel in which to inject the dependencies
     */
    fun inject(ArticleListViewModel: ArticlesListViewModel)
    /**
     * Injects required dependencies into the specified ArticleViewModel.
     * @param ArticleViewModel ArticleViewModel in which to inject the dependencies
     */
    fun inject(articleViewModel: ArticleViewModel)

    /**
     * Injects required dependencies into the specified ArticleDetailViewModel.
     * @param ArticleDetailViewModel ArticleViewModel in which to inject the dependencies
     */
    fun inject(articleDetailViewModel: ArticleDetailViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}