package com.example.loginextassignment.di

import androidx.lifecycle.ViewModelProvider
import com.example.loginextassignment.viewmodels.CustomViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun providesViewModelFactory(factory : CustomViewModelProviderFactory  ): ViewModelProvider.Factory
}