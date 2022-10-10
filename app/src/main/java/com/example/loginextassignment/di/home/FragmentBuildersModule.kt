package com.example.loginextassignment.di.home

import com.example.loginextassignment.model.HomeModel
import com.example.loginextassignment.ui.account.AccountFragment
import com.example.loginextassignment.ui.home.HomeFragment
import com.example.loginextassignment.ui.order.OrderFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesOrdersFragment(): OrderFragment

    @ContributesAndroidInjector
    abstract fun contributesAccountFragment(): AccountFragment

}