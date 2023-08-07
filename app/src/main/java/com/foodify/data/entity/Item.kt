package com.foodify.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(@SerializedName("yemek_id") var itemId:Int,
                @SerializedName("yemek_adi") var itemName:String,
                @SerializedName("yemek_resim_adi") var itemPicture:String,
                @SerializedName("yemek_fiyat") var ItemPrice: Int) : Serializable {
}