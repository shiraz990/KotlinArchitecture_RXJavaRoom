package com.sevenpeakssoftware.muhammadshiraz.ui.article

import android.arch.lifecycle.MutableLiveData
import com.sevenpeakssoftware.muhammadshiraz.base.BaseViewModel
import com.sevenpeakssoftware.muhammadshiraz.model.Car

class CarViewModel: BaseViewModel() {
    private val carImage = MutableLiveData<String>()
    private val carTitle = MutableLiveData<String>()
    private val carDate = MutableLiveData<String>()
    private val carDescription = MutableLiveData<String>()

    fun bind(Car: Car){
        carTitle.value = Car.title
        carDate.value = Car.dateTime
        carDescription.value = Car.description
        carImage.value = Car.image
    }

    fun getCarTitle():MutableLiveData<String>{
        return carTitle
    }


    fun getCarDate():MutableLiveData<String>{
        return carDate
    }
    fun getCarDescription():MutableLiveData<String>{
        return carDescription
    }
    fun getCarImage():MutableLiveData<String>{
        return carImage
    }
}