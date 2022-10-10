package com.example.loginextassignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.SortedList
import com.bumptech.glide.RequestManager
import com.example.loginextassignment.adapters.home.BannersAdapter
import com.example.loginextassignment.adapters.home.FoodCategoriesAdapter
import com.example.loginextassignment.adapters.home.OffersAdapter
import com.example.loginextassignment.adapters.home.RestaurantsAdapter
import com.example.loginextassignment.databinding.FragmentHomeBinding
import com.example.loginextassignment.di.home.Resource
import com.example.loginextassignment.model.HomeData
import com.example.loginextassignment.model.RestaurantsCollection
import com.example.loginextassignment.utils.Utils
import com.example.loginextassignment.utils.gone
import com.example.loginextassignment.utils.visible
import com.example.loginextassignment.viewmodels.CustomViewModelProviderFactory
import com.example.loginextassignment.viewmodels.home.HomeViewModel
import dagger.android.support.DaggerFragment
import java.util.Collections
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    @Inject
    lateinit var viewModelProviderFactory: CustomViewModelProviderFactory

    @Inject
    lateinit var bannersAdapter : BannersAdapter

    @Inject
    lateinit var foodCategoriesAdapter: FoodCategoriesAdapter

    @Inject
    lateinit var offersAdapter : OffersAdapter

    @Inject
    lateinit var restaurantsAdapter: RestaurantsAdapter

    @Inject
    lateinit var requestManager : RequestManager

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel = ViewModelProviders.of(this, viewModelProviderFactory)[HomeViewModel::class.java]
        getHomeData()
    }

    private fun getHomeData(){
        homeViewModel.getHomeData().removeObservers(viewLifecycleOwner)
        homeViewModel.getHomeData().observe(viewLifecycleOwner){
            it?.let { res ->
                when(res.status){
                    Resource.Status.LOADING ->{
                        showLoading()
                    }

                    Resource.Status.ERROR ->{
                        showError("Something Went Wrong!!! \nPlease Click and try again.")
                    }

                    Resource.Status.SUCCESS ->{
                        res.data?.let { data ->
                            data.data?.let {
                                setUpView(it)
                            }
                        }
                    }

                }
            }
        }
    }

    private fun setUpView(data: HomeData) {
        fragmentHomeBinding.progressBar.gone()
        fragmentHomeBinding.homeParentLayout.removeAllViews()
        data.banners?.let { banners ->
            val bannerSection = BannerSection(activity!!)
            bannerSection.bannersAdapter = bannersAdapter
            bannerSection.mWidth = getCalculatedWidth(banners.size, 1.2f)
            bannerSection.requestManager = requestManager
            bannerSection.bannersdata = banners
            fragmentHomeBinding.homeParentLayout.addView(bannerSection)
        }

        data.food_categories?.let { food_categories ->
            val foodCategoriesSection = FoodCategoriesSection(activity!!)
            foodCategoriesSection.foodCategoriesAdapter = foodCategoriesAdapter
            foodCategoriesSection.requestManager = requestManager
            foodCategoriesSection.foodCateegories = food_categories
            fragmentHomeBinding.homeParentLayout.addView(foodCategoriesSection)
        }

        data.number_of_active_vouchers?.let {
            val voucherCounntSection = VoucherCounntSection(activity!!)
            voucherCounntSection.voucherCount = it
            fragmentHomeBinding.homeParentLayout.addView(voucherCounntSection)
        }

        data.offer_collections?.let { offer_collections ->
            val offerSection = OffersSection(activity!!)
            offerSection.offersAdapter = offersAdapter
            offerSection.requestManager = requestManager
            offerSection.offersData = offer_collections
            fragmentHomeBinding.homeParentLayout.addView(offerSection)
        }

        data.restaurant_collections?.let { restaurant_collections ->
            var sortedList = arrayListOf<RestaurantsCollection>()
            sortedList.addAll(restaurant_collections)

            if (restaurant_collections.size > 1){
                var i = 0
                var j = 0
                while (i < sortedList.size){
                    if (sortedList[j].priority!! > sortedList[i].priority!!){
                        var temp = sortedList[i]
                        sortedList[i] = sortedList[j]
                        sortedList[j] = temp
                    }
                    if (j < sortedList.size - 1){
                        j++
                    }else{
                        j = 0
                        i++
                    }
                }

            }
            val restaurantsSection = RestaurantsSection(activity!!)
            restaurantsSection.requestManager = requestManager
            restaurantsSection.mWidth = getCalculatedWidth(restaurant_collections.size, 1.5f)
            restaurantsSection.restaurantsAdapter = restaurantsAdapter
            restaurantsSection.restaurants = sortedList
            fragmentHomeBinding.homeParentLayout.addView(restaurantsSection)
        }
    }

    private fun getCalculatedWidth(size : Int, divider : Float) : Int?{
        var calculatedWidth = 0f
        if (size > 1){
            calculatedWidth = Utils.getScreenWidth(activity!!) / divider
            return calculatedWidth.toInt()
        }
        return null
    }

    private fun showLoading(){
        fragmentHomeBinding.progressBar.visible()
    }

    private fun showError(msg : String){
        fragmentHomeBinding.progressBar.gone()
        fragmentHomeBinding.errorScreen.visible()
        fragmentHomeBinding.errorScreenTV.text = msg
        fragmentHomeBinding.errorScreen.setOnClickListener {
            getHomeData()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment()
    }
}