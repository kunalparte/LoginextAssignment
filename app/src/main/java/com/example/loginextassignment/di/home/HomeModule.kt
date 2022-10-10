package com.example.loginextassignment.di.home

import com.example.loginextassignment.MainActivity
import com.example.loginextassignment.adapters.home.BannersAdapter
import com.example.loginextassignment.adapters.home.FoodCategoriesAdapter
import com.example.loginextassignment.adapters.home.OffersAdapter
import com.example.loginextassignment.adapters.home.RestaurantsAdapter
import com.example.loginextassignment.api.HomeApi
import com.example.loginextassignment.ui.home.FoodCategoriesSection
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HomeModule {

    @Provides
    fun providesHomeApi(retrofit: Retrofit) : HomeApi{
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    fun providesBannerAdapter(): BannersAdapter {
        return BannersAdapter()
    }

    @Provides
    fun providesFoodCategoriesAdapter(): FoodCategoriesAdapter {
        return FoodCategoriesAdapter()
    }

    @Provides
    fun providesOffersAdapter(): OffersAdapter {
        return OffersAdapter()
    }

    @Provides
    fun providesRestaurantAdapter(): RestaurantsAdapter {
        return RestaurantsAdapter()
    }
}