package com.example.loginextassignment.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.adapters.home.BannersAdapter
import com.example.loginextassignment.databinding.BannerSectionLayoutBinding
import com.example.loginextassignment.model.Banners

class BannerSection  (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr){

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var binding : BannerSectionLayoutBinding

    init {
        binding = BannerSectionLayoutBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    lateinit var bannersAdapter : BannersAdapter

    var mWidth : Int ?= null

    lateinit var requestManager: RequestManager

    private val linearLayoutManager by lazy {
        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }


    var bannersdata : List<Banners> ?= null
    set(value) {
        if (value == null)
            return
        field = value
        field.let { banners ->

            if (banners!!.isNotEmpty()){
                binding.bannerRecyclerView.apply {
                    layoutManager = linearLayoutManager
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(this)
                    bannersAdapter.width = mWidth
                    bannersAdapter.requestManager = requestManager
                    adapter = bannersAdapter
                    bannersAdapter.submitList(banners   )
                }
            }
        }
    }
}