package com.sevenpeakssoftware.muhammadshiraz.injection.component

import dagger.Component
import com.sevenpeakssoftware.muhammadshiraz.injection.module.NetworkModule
import com.sevenpeakssoftware.muhammadshiraz.ui.article.CarListViewModel
import com.sevenpeakssoftware.muhammadshiraz.ui.article.CarViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified CarListViewModel.
     * @param CarListViewModel CarListViewModel in which to inject the dependencies
     */
    fun inject(CarListViewModel: CarListViewModel)
    /**
     * Injects required dependencies into the specified CarViewModel.
     * @param carViewModel CarViewModel in which to inject the dependencies
     */
    fun inject(carViewModel: CarViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}