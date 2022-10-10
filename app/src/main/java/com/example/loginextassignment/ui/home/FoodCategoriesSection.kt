package com.example.loginextassignment.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.R
import com.example.loginextassignment.adapters.home.FoodCategoriesAdapter
import com.example.loginextassignment.databinding.FoodCategoriesSectionLayoutBinding
import com.example.loginextassignment.model.FoodCategories

class FoodCategoriesSection (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr){

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var binding: FoodCategoriesSectionLayoutBinding


    lateinit var requestManager: RequestManager

    lateinit var foodCategoriesAdapter: FoodCategoriesAdapter

        private val linearLayoutManager by lazy {
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

    init {
        binding = FoodCategoriesSectionLayoutBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    var foodCateegories : List<FoodCategories> ?= null
    set(value) {
        if (value == null)
            return

        field = value
        field.let { data ->

            if (data!!.isNotEmpty()) {
                binding.foodCategoriesRecyclerView.apply {
                    layoutManager = linearLayoutManager
                    adapter = foodCategoriesAdapter
                    foodCategoriesAdapter.requestManager = requestManager
                    foodCategoriesAdapter.submitList(data)
                }
            }
        }
    }
}