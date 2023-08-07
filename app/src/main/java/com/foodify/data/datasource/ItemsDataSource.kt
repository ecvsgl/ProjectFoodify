package com.foodify.data.datasource

import android.util.Log
import android.view.View
import com.foodify.data.entity.CartItemEntity
import com.foodify.data.entity.Item
import com.foodify.retrofit.ItemsDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.log

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
    suspend fun addToCart(itemId:Int,itemName:String,itemPicture:String,itemPrice:Int,itemQuantity:Int) {
        Log.e("addToCartLogicSequence", "Add to cart begin")
        Log.e("addToCartLogicSequence", "Loading existing cartItems prior to addition.")
        val oldCartItemList = loadCartItemsList() ?: emptyList()
        Log.e("addToCartLogicSequence", "Old list;")
        Log.e("addToCartLogicSequence", oldCartItemList.toString())
        var additionDoesExist = false
        var oldCartItemQuantity = 0
        var oldCartItemId = 0

        if (oldCartItemList.isNotEmpty()){
            Log.e("addToCartLogicSequence", "Old list is not empty and checking if addition exists")
            for(x:CartItemEntity in oldCartItemList){
                Log.e("addToCartLogicSequence", "For loop initiated.")
                if (x.cartItemName == itemName && x.cartItemPicture == itemPicture && x.cartItemPrice == itemPrice){
                    Log.e("addToCartLogicSequence", "Addition exists. Loading its oldquantity to memory.")
                    oldCartItemQuantity = x.cartItemQuantity
                    oldCartItemId = x.cartItemId
                    additionDoesExist = true
                    break
                }
            }
        }
        if(additionDoesExist){
            Log.e("addToCartLogicSequence", "Addition exists. Removing old item from cart.")
            removeCartItem(oldCartItemId,hiddenUsername)
            val newQuantity = itemQuantity + oldCartItemQuantity
            Log.e("addToCartLogicSequence", "Loading old + new added quantity amount of the item.")
            itemsDao.addToCart(itemName,itemPicture,itemPrice,newQuantity,hiddenUsername)
        }else{
            Log.e("addToCartLogicSequence", "Brand new item addition.")
            itemsDao.addToCart(itemName,itemPicture,itemPrice,itemQuantity,hiddenUsername)
        }
    }
    suspend fun removeCartItem(cartItemId:Int,username:String){
        itemsDao.removeCartItem(cartItemId,username)
    }
    suspend fun loadCartItemsList() : List<CartItemEntity> =
        withContext(Dispatchers.IO){
            try {
                Log.e("cartItemChecker","preItemLoad")
                val loadCartItemsListResponse = itemsDao.loadCartItemsList(hiddenUsername)
                Log.e("cartItemChecker","postItemLoad")
                Log.e("cartItemChecker", loadCartItemsListResponse.toString())
                return@withContext loadCartItemsListResponse?.sepet_yemekler ?: emptyList()
            } catch (e: IOException) {
                Log.e("cartItemChecker","IOException during loadCartItemsList", e)
                return@withContext emptyList<CartItemEntity>()
            } catch (e: HttpException) {
                Log.e("cartItemChecker","HttpException during loadCartItemsList", e)
                return@withContext emptyList<CartItemEntity>()
            }
    }
    suspend fun loadMenuItems() : List<Item> =
        withContext(Dispatchers.IO){
            return@withContext itemsDao.loadMenuItems().yemekler
    }
}