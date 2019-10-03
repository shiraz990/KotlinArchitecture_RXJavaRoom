package com.sevenpeakssoftware.muhammadshiraz.base

import android.arch.lifecycle.ViewModel
import com.sevenpeakssoftware.muhammadshiraz.injection.component.DaggerViewModelInjector
import com.sevenpeakssoftware.muhammadshiraz.injection.component.ViewModelInjector
import com.sevenpeakssoftware.muhammadshiraz.injection.module.NetworkModule
import com.sevenpeakssoftware.muhammadshiraz.ui.article.CarListViewModel
import com.sevenpeakssoftware.muhammadshiraz.ui.article.CarViewModel

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