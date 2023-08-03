package com.foodify.data.entity

import java.io.Serializable

data class Item(var itemId:Int,
                var itemName:String,
                var itemPicture:String,
                var ItemPrice: Int) : Serializable {
}