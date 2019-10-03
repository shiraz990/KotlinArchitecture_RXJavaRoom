package com.application.muhammadshiraz.base

import android.arch.lifecycle.ViewModel
import com.application.muhammadshiraz.injection.component.DaggerViewModelInjector
import com.application.muhammadshiraz.injection.component.ViewModelInjector
import com.application.muhammadshiraz.injection.module.NetworkModule
import com.application.muhammadshiraz.ui.article.CarListViewModel
import com.application.muhammadshiraz.ui.article.CarViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CarListViewModel -> injector.inject(this)
            is CarViewModel -> injector.inject(this)
        }
    }
}