package com.example.loginextassignment.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.databinding.OfferItemLayoutBinding
import com.example.loginextassignment.model.OfferCollections
import com.example.loginextassignment.utils.gone
import com.example.loginextassignment.utils.visible
import javax.inject.Inject

class OffersAdapter : ListAdapter<OfferCollections, OffersAdapter.VHolder>(MDiffUtils()) {

    class VHolder(binding : OfferItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        var offersItemImageView = binding.offersItemImageView
        var offersItemTitleTV = binding.offersItemTitleTV
    }

    class MDiffUtils : DiffUtil.ItemCallback<OfferCollections>() {
        override fun areItemsTheSame(oldItem: OfferCollections, newItem: OfferCollections): Boolean = newItem == oldItem

        override fun areContentsTheSame(oldItem: OfferCollections, newItem: OfferCollections): Boolean = newItem.id == oldItem.id
    }

    lateinit var requestManager : RequestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val offerItemLayoutBinding = OfferItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(offerItemLayoutBinding)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        getItem(position)?.let { data ->

            holder.offersItemImageView.gone()
            data.icon?.let { icon ->
               requestManager.load(icon).into(holder.offersItemImageView)
                holder.offersItemImageView.visible()
            }

            holder.offersItemTitleTV.gone()
            data.name?.let { name ->
                holder.offersItemTitleTV.text = name
                holder.offersItemTitleTV.visible()
            }

        }
    }
}