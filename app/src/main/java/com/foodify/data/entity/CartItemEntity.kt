package com.foodify.data.entity

data class CartItemEntity(var cartItemId:Int,
                          var cartItemName:String,
                          var cartItemPicture:String,
                          var cartItemPrice:Int,
                          var cartItemQuantity:Int,
                          var username:String) {
    val totalCostOfCartItemEntity : Int
        get() = cartItemPrice*cartItemQuantity
}