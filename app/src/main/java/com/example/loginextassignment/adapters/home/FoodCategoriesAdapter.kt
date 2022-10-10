package com.example.loginextassignment.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.databinding.FoodCategoriesItemLayoutBinding
import com.example.loginextassignment.model.FoodCategories
import com.example.loginextassignment.utils.gone
import com.example.loginextassignment.utils.visible

class FoodCategoriesAdapter : ListAdapter<FoodCategories, FoodCategoriesAdapter.VHolder>(MDiffUtils()) {


    class VHolder(binding : FoodCategoriesItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        var foodCategoriesItemImageView = binding.foodCategoriesItemImageView
        var foodCategoriesItemTitleTV = binding.foodCategoriesItemTitleTV
    }

    class MDiffUtils : DiffUtil.ItemCallback<FoodCategories>() {
        override fun areItemsTheSame(oldItem: FoodCategories, newItem: FoodCategories): Boolean = newItem == oldItem

        override fun areContentsTheSame(oldItem: FoodCategories, newItem: FoodCategories): Boolean = newItem.id == oldItem.id
    }

    lateinit var requestManager : RequestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val binding = FoodCategoriesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(binding)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        getItem(position)?.let { data ->

            holder.foodCategoriesItemImageView.gone()
            data.icon?.let { icon ->
                requestManager .load(icon).into(holder. foodCategoriesItemImageView)
                holder.foodCategoriesItemImageView.visible()
            }

            holder.foodCategoriesItemTitleTV.gone()
            data.name?.let { name ->
                holder.foodCategoriesItemTitleTV.text = name
                holder.foodCategoriesItemTitleTV.visible()
            }

        }
    }
}

