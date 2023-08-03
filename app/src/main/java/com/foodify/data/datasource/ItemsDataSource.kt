package com.foodify.data.datasource

import android.view.View
import com.foodify.retrofit.ItemsDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsDataSource() {
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
    suspend fun addToCart(view: View, itemName:String, itemPrice:String, itemQuantity:String){
        Snackbar.make(view, "$itemName added to cart!", Snackbar.LENGTH_SHORT).show()
        // ADD CART PERSISTENCE HERE
    }

}