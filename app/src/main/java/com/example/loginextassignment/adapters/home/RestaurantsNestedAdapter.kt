package com.example.loginextassignment.adapters.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.databinding.RestaurantsNestedItemLayoutBinding
import com.example.loginextassignment.model.RestaurantsObject
import com.example.loginextassignment.utils.Utils
import com.example.loginextassignment.utils.gone
import com.example.loginextassignment.utils.visible
import javax.inject.Inject

class RestaurantsNestedAdapter : ListAdapter<RestaurantsObject, RestaurantsNestedAdapter.VHolder>(MDiffUtils()) {

    var width : Int ?= null

    class VHolder(binding : RestaurantsNestedItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        val restaurantItemImageCardView = binding.restaurantItemImageCardView
        var restaurantItemImageView = binding.restaurantItemImageView
        var restaurantItemTitleTV = binding.restaurantItemTitleTV
        var restaurantItemRatingCardView = binding.restaurantItemRatingCardView
        var restaurantItemRatingTV = binding.restaurantItemRatingTV
        var restaurantItemDurationTV = binding.restaurantItemDurationTV
        var restaurantItemOffersTV = binding.restaurantItemOffersTV
        var restaurantItemCardView = binding.restaurantItemCardView
    }

    class MDiffUtils : DiffUtil.ItemCallback<RestaurantsObject>() {
        override fun areItemsTheSame(oldItem: RestaurantsObject, newItem: RestaurantsObject): Boolean = newItem == oldItem

        override fun areContentsTheSame(oldItem: RestaurantsObject, newItem: RestaurantsObject): Boolean = newItem.id == oldItem.id
    }

    lateinit var requestManager : RequestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val restaurantsNestedItemLayoutBinding = RestaurantsNestedItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(restaurantsNestedItemLayoutBinding)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        getItem(position)?.let { data ->
            width?.let { w ->
                holder.restaurantItemCardView.layoutParams.width = w
            }
            holder.restaurantItemImageCardView.gone()
            data.image_url?.let { image ->
                requestManager.load(image).into(holder.restaurantItemImageView)
                holder.restaurantItemImageCardView.visible()
            }

            holder.restaurantItemTitleTV.gone()
            data.name?.let { name ->
                holder.restaurantItemTitleTV.text = name
                holder.restaurantItemTitleTV.visible()
            }

            holder.restaurantItemRatingCardView.gone()
            data.rating?.let { rating ->
                holder.restaurantItemRatingTV.setBackgroundColor(Utils.returnnColor(rating.toFloat()))
                holder.restaurantItemRatingTV.text = rating.toString()
                holder.restaurantItemRatingCardView.visible()
            }

            holder.restaurantItemDurationTV.gone()
            data.display_distance?.let { duration ->
                holder.restaurantItemDurationTV.text = duration
                holder.restaurantItemDurationTV.visible()
            }

            data.offers?.let { offers ->
                if (offers.isNotEmpty()){
                    var offerText = ""
                    for (i in offers){
                        i.name?.let { name ->
                            if (offerText.isEmpty()){
                                offerText = name
                            }
                            offerText = "$offerText | $name"
                        }
                    }
                    if (offerText.isNotEmpty()){
                        holder.restaurantItemOffersTV.text = offerText
                    }
                }
            }
        }
    }

}