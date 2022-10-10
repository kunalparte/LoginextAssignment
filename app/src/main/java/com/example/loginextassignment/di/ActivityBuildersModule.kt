package com.example.loginextassignment.di

import com.example.loginextassignment.MainActivity
import com.example.loginextassignment.di.home.FragmentBuildersModule
import com.example.loginextassignment.di.home.HomeModule
import com.example.loginextassignment.di.home.HomeViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [
        HomeViewModelModule::class,
        FragmentBuildersModule::class,
        HomeModule::class])
    abstract fun contributesMainActivity() : MainActivity
}