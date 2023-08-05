package com.foodify.data.repo

import android.view.View
import com.foodify.data.datasource.ItemsDataSource
import com.foodify.data.entity.CartItemEntity
import com.foodify.data.entity.Item

class ItemRepository (var itemsDataSource: ItemsDataSource){

    suspend fun buttonIncrementClick(currentQuantity:String) : String{
        return itemsDataSource.buttonIncrementClick(currentQuantity)
    }
    suspend fun buttonDecrementClick(currentQuantity:String) : String{
        return itemsDataSource.buttonDecrementClick(currentQuantity)
    }
    suspend fun addToCart(view: View,itemName:String, itemPrice:String, itemQuantity:String){
        itemsDataSource.addToCart(view,itemName,itemPrice,itemQuantity)
    }
    suspend fun removeCartItem(cartItemId:Int,username:String){
        itemsDataSource.removeCartItem(cartItemId,username)
    }
    suspend fun loadCartItemsList() : List<CartItemEntity>{
        return itemsDataSource.loadCartItemsList()
    }
    suspend fun loadMenuItems() : List<Item>{
        return itemsDataSource.loadMenuItems()
    }
    suspend fun search(query:String) : List<Item>{
        return itemsDataSource.search(query)
    }


}