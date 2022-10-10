package com.example.loginextassignment.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.adapters.home.RestaurantsAdapter
import com.example.loginextassignment.databinding.RestaurantsSectionLayoutBinding
import com.example.loginextassignment.model.RestaurantsCollection
import javax.inject.Inject

class RestaurantsSection (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr){

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var restaurantsSectionLayoutBinding: RestaurantsSectionLayoutBinding

    init {
        restaurantsSectionLayoutBinding = RestaurantsSectionLayoutBinding.inflate(LayoutInflater.from(context))
        addView(restaurantsSectionLayoutBinding.root)
    }

    lateinit var requestManager: RequestManager

    lateinit var restaurantsAdapter: RestaurantsAdapter

    var mWidth : Int ?= null

    private val linearLayoutManager by lazy {
        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    var restaurants : List<RestaurantsCollection> ?= null
    set(value) {
        if (value == null)
            return

        field = value
        field.let { data ->

            if (data!!.isNotEmpty()){
                restaurantsSectionLayoutBinding.restaurantsNestedRV.apply {
                    restaurantsAdapter.requestManager = requestManager
                    restaurantsAdapter.mWidth = mWidth
                    layoutManager = linearLayoutManager
                    adapter = restaurantsAdapter
                    restaurantsAdapter.submitList(data)
                }
            }
        }
    }
}