package com.foodify.data.datasource

import android.view.View
import com.foodify.data.entity.CartItemEntity
import com.foodify.data.entity.Item
import com.foodify.retrofit.ItemsDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsDataSource(var itemsDao: ItemsDao) {
    val hiddenUsername = "SU_ecvsgl"
    suspend fun buttonIncrementClick(currentQuantity:String) : String =
        withContext(Dispatchers.IO){
            val oldQuantity = currentQuantity.toInt()
            val newQuantity = oldQuantity+1
            return@withContext newQuantity.toString()
        }

    suspend fun buttonDecrementClick(currentQuantity:String) : String =
        withContext(Dispatchers.IO){
            val oldQuantity = currentQuantity.toInt()
            var newQuantity = oldQuantity
            if(oldQuantity>1){
                newQuantity -=1
            }
            return@withContext newQuantity.toString()
        }
    suspend fun addToCart(view: View, itemName:String, itemPrice:String, itemQuantity:String) {
        Snackbar.make(view, "$itemName added to cart!", Snackbar.LENGTH_SHORT).show()
        itemsDao.addToCart(view,itemName,itemPrice,itemQuantity,hiddenUsername)
    }
    suspend fun removeCartItem(cartItemId:Int,username:String){
        itemsDao.removeCartItem(cartItemId,username)
    }
    suspend fun loadCartItemsList() : List<CartItemEntity> =
        withContext(Dispatchers.IO){
            return@withContext itemsDataSource.loadCartItemsList(hiddenUsername)
    }
    suspend fun loadMenuItems() : List<Item> =
        withContext(Dispatchers.IO){
            return@withContext itemsDataSource.loadMenuItems()
    }
    suspend fun search(query:String) : List<Item> =
        withContext(Dispatchers.IO){
            return@withContext itemsDataSource.search(query) // buraya başka bir logic entegre etmeli.
    }
}