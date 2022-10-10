package com.example.loginextassignment.di

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.loginextassignment.BaseApplication
import com.example.loginextassignment.R
import com.example.loginextassignment.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRequestOptions() : RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
    }


    /*application object is binded to AppComponent while its construction and so is avaiable use
    requestionn option dependency is available as its dependency is defined above in this Module
    With this accessing the glide instance anywhere in the application bbecomes easy
     as we can inject it anywhere in the application now*/
    @Singleton
    @Provides
    fun providesGlideInstance(application: BaseApplication, requestOptions: RequestOptions) : RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }


    @Singleton
    @Provides
    fun providesRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}