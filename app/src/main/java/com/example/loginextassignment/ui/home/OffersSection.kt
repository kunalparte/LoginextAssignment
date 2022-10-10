package com.example.loginextassignment.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.R
import com.example.loginextassignment.adapters.home.OffersAdapter
import com.example.loginextassignment.databinding.OffersSectionLayoutBinding
import com.example.loginextassignment.model.OfferCollections
import javax.inject.Inject

class OffersSection (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr){

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var offersSectionLayoutBinding: OffersSectionLayoutBinding

    init {
        offersSectionLayoutBinding = OffersSectionLayoutBinding.inflate(LayoutInflater.from(context))
        addView(offersSectionLayoutBinding.root)
    }

    lateinit var requestManager: RequestManager

    lateinit var offersAdapter : OffersAdapter

    private val gridLayoutManager by lazy {
        GridLayoutManager(context,2, RecyclerView.VERTICAL, false)
    }

    var offersData : List<OfferCollections> ?= null
    set(value) {
        if (value == null)
            return

        field = value
        field.let { offers ->

            offersSectionLayoutBinding.offersTitleTV.text = context.getString(R.string.collections)

            if (offers!!.isNotEmpty()){
                offersSectionLayoutBinding.offersRecyclerView.apply {
                    layoutManager = gridLayoutManager
                    adapter = offersAdapter
                    offersAdapter.requestManager = requestManager
                    offersAdapter.submitList(offers)
                }
            }
        }
    }
}