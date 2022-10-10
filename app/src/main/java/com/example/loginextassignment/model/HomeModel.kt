package com.example.loginextassignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeModel(

    @field:SerializedName("status")
    var status : Int ?= null,

    @field:SerializedName("data")
    var data : HomeData ?= null

) : Parcelable


/*Home Data Start region*/
@Parcelize
data class HomeData(

    @field:SerializedName("banners")
    var banners : List<Banners> ?= null,

    @field:SerializedName("food_categories")
    var food_categories : List<FoodCategories> ?= null,

    @field:SerializedName("number_of_active_vouchers")
    var number_of_active_vouchers : Int ?= null,

    @field:SerializedName("offer_collections")
    var offer_collections : List<OfferCollections> ?= null,

    @field:SerializedName("restaurant_collections")
    var restaurant_collections : List<RestaurantsCollection> ?= null
): Parcelable
/*End Region*/


/*Banners Start region*/
@Parcelize
data class Banners(

    @field:SerializedName("id")
    var id : Int ?= null,

    @field:SerializedName("image_url")
    var image_url : String ?= null

) : Parcelable
/*End Region*/


/*Food categories Start region*/
@Parcelize
data class FoodCategories(

    @field:SerializedName("id")
    var id : Int ?= null,

    @field:SerializedName("name")
    var name : String ?= null,

    @field:SerializedName("icon")
    var icon : String ?= null,

) : Parcelable
/*End Region*/


/*Offers Collection start region*/
@Parcelize
data class OfferCollections(

    @field:SerializedName("id")
    var id : Int ?= null,

    @field:SerializedName("name")
    var name : String ?= null,

    @field:SerializedName("image")
    var icon : String ?= null,

    @field:SerializedName("textcolor")
    var textcolor : String ?= null,

    @field:SerializedName("background")
    var background : String ?= null

) : Parcelable
/*End region*/


/*Restaurants Collections Start region*/
@Parcelize
data class RestaurantsCollection(

    @field:SerializedName("name")
    var name : String ?= null,

    @field:SerializedName("priority")
    var priority : Int ?= null,

    @field:SerializedName("restaurants")
    var restaurants : List<RestaurantsObject> ?= null

) : Parcelable


@Parcelize
data class RestaurantsObject(

    @field:SerializedName("name")
    var name : String ?= null,

    @field:SerializedName("id")
    var id : Int ?= null,

    @field:SerializedName("display_distance")
    var display_distance : String ?= null,

    @field:SerializedName("rating")
    var rating : Double ?= null,

    @field:SerializedName("image_url")
    var image_url : String ?= null,

    @field:SerializedName("offers")
    var offers : List<RestaurantOffersList> ?= null


) : Parcelable


@Parcelize
data class RestaurantOffersList(

    @field:SerializedName("id")
    var id : Int ?= null,

    @field:SerializedName("name")
    var name : String ?= null,

    @field:SerializedName("textcolor")
    var textcolor : String ?= null,

    @field:SerializedName("background")
    var background : String ?= null

): Parcelable
/*End Region*/
