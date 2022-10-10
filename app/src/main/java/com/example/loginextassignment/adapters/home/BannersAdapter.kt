package com.example.loginextassignment.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.databinding.BannerListItemLayoutBinding
import com.example.loginextassignment.model.Banners

class BannersAdapter : ListAdapter<Banners, BannersAdapter.VHolder>(MDiffUtils()) {

    var width : Int ?= null

    private lateinit var binding : BannerListItemLayoutBinding

    lateinit var requestManager: RequestManager

    class VHolder(binding : BannerListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        var bannerCardView = binding.bannerCardView
        var bannerImageView = binding.bannerImageView
    }

    class MDiffUtils : DiffUtil.ItemCallback<Banners>() {
        override fun areItemsTheSame(oldItem: Banners, newItem: Banners): Boolean = newItem == oldItem

        override fun areContentsTheSame(oldItem: Banners, newItem: Banners): Boolean = newItem.id == oldItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        binding = BannerListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(binding)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        getItem(position)?.let { data ->
            width?.let { w ->
                holder.bannerCardView.layoutParams.width = w
            }

            data.image_url?.let { img ->
                requestManager.load(img).into(holder.bannerImageView)
            }
        }
    }
}