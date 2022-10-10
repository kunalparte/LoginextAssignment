package com.example.loginextassignment.adapters.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.databinding.RestaurantsItemLayoutBinding
import com.example.loginextassignment.model.RestaurantsCollection
import com.example.loginextassignment.utils.gone
import com.example.loginextassignment.utils.visible

class RestaurantsAdapter : ListAdapter<RestaurantsCollection, RestaurantsAdapter.VHolder>(MDiffUtils()) {

    class VHolder(binding : RestaurantsItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        var restaurantsMainTitleTV = binding.restaurantsMainTitleTV
        var restaurantsNestedRV = binding.restaurantsNestedRV
    }

    class MDiffUtils : DiffUtil.ItemCallback<RestaurantsCollection>() {
        override fun areItemsTheSame(oldItem: RestaurantsCollection, newItem: RestaurantsCollection): Boolean = newItem == oldItem

        override fun areContentsTheSame(oldItem: RestaurantsCollection, newItem: RestaurantsCollection): Boolean = newItem.name == oldItem.name
    }

    private lateinit var restaurantsNNestedAdaoter : RestaurantsNestedAdapter

    private lateinit var context : Context

    lateinit var requestManager : RequestManager

    var mWidth : Int ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        context = parent.context
        val binding = RestaurantsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(binding)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        getItem(position)?.let { data ->

            holder.restaurantsMainTitleTV.gone()
            data.name?.let { name ->
                holder.restaurantsMainTitleTV.text = name
                holder.restaurantsMainTitleTV.visible()
            }

            holder.restaurantsNestedRV.apply {
                gone()
                data.restaurants?.let { restaurants ->
                    if (restaurants.isNotEmpty()){
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        val restaurantsAdapter = RestaurantsNestedAdapter()
                        restaurantsAdapter.width = mWidth
                        adapter = restaurantsAdapter
                        restaurantsAdapter.requestManager = requestManager
                        restaurantsAdapter.submitList(restaurants)
                        visible()
                    }
                }
            }
        }
    }

}