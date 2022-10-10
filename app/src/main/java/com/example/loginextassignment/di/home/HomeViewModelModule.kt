package com.example.loginextassignment.di.home

import androidx.lifecycle.ViewModel
import com.example.loginextassignment.di.ViewModelKey
import com.example.loginextassignment.viewmodels.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun providesHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}