package com.example.loginextassignment.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.loginextassignment.api.HomeApi
import com.example.loginextassignment.di.home.Resource
import com.example.loginextassignment.model.HomeModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(homeApi: HomeApi) : ViewModel() {

    private var homeLiveData : MediatorLiveData<Resource<HomeModel>> ?= null

    private val homeApi : HomeApi

    init {
        this.homeApi = homeApi
    }

    fun getHomeData() : LiveData<Resource<HomeModel>>{
        if (homeLiveData == null){
            homeLiveData = MediatorLiveData()
            homeLiveData!!.value = Resource.loading(null)
        }


        val source: LiveData<Resource<HomeModel>> = LiveDataReactiveStreams.fromPublisher(
            homeApi.gwtData().onErrorReturn {
                val homeData = HomeModel()
                homeData.status = 400
                homeData
            }.map(object : io.reactivex.functions.Function<HomeModel, Resource<HomeModel>>{
                override fun apply(t: HomeModel): Resource<HomeModel> {
                    if (t.status != 200){
                        return Resource.error("Something Went Wrong", null)
                    }
                    return Resource.success(t)
                }

            }).subscribeOn(Schedulers.io())
        )

        homeLiveData!!.addSource(source){
            homeLiveData!!.value = it
            homeLiveData!!.removeSource(source)
        }
        return homeLiveData!!
    }
}