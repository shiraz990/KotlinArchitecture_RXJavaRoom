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
import com.application.muhammadshiraz.model.Car
import com.application.muhammadshiraz.model.CarDao
import com.application.muhammadshiraz.network.CarApi
import javax.inject.Inject
import java.text.SimpleDateFormat


class CarListViewModel(private val carDao: CarDao): BaseViewModel(){
    @Inject
    lateinit var carApi: CarApi
    val  carListAdapter = CarListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCars() }

    private lateinit var subscription: Disposable

    init{
        loadCars()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadCars(){
        subscription = Observable.fromCallable { carDao.all }
                .concatMap {
                    dbCarList ->
                        if(dbCarList.isEmpty())

                            //Adding data in database
                            carApi.getCars().concatMap { apiCarList ->

                                    val  calList = ArrayList<Car>()
                                    for (content in  apiCarList.content){

                                        for (item in  content.content){
                                            val car = Car(content.id,content.title,convertDateFormat(content.dateTime),item.subject,content.image,item.description)

                                            carDao.insertAll(car)
                                            calList.add(car)

                                        }
                                    }
                                Observable.just(calList)

                        }
                        else  Observable.just(dbCarList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCarListStart() }
                .doOnTerminate { onRetrieveCarListFinish() }
                .subscribe(
                        { result ->
                        //observing data
                                onRetrieveCarListSuccess(result as List<Car>)

                        },
                        { onRetrieveCarListError() }
                )
    }

    //Method to convert date format
    @SuppressLint("SimpleDateFormat")
    private fun convertDateFormat(dateTime: String): String {

        var spf = SimpleDateFormat("dd.MM.yyyy hh:mm")
        val newDate = spf.parse(dateTime)
        spf = SimpleDateFormat("dd MMMM yyyy , hh:mm")
       return spf.format(newDate)
    }

    private fun onRetrieveCarListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCarListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCarListSuccess(carList:List<Car>){
        carListAdapter.updateCarList(carList)
    }

    private fun onRetrieveCarListError(){
        errorMessage.value = R.string.Car_error
    }
}