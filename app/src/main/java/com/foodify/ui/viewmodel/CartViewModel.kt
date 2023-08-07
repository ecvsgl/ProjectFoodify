package com.foodify.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodify.data.entity.CartItemEntity
import com.foodify.data.repo.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor (var itemRepository: ItemRepository): ViewModel() {
    var cartItemsList = MutableLiveData<List<CartItemEntity>?>()
    var totalOrderPrice = MutableLiveData<Int>(0)
    init {
        loadCartItemsList()
    }
    fun loadCartItemsList(){
        CoroutineScope(Dispatchers.Main).launch(){
            val updatedCartItemsList = itemRepository.loadCartItemsList() ?: emptyList()
            cartItemsList.value = updatedCartItemsList
            var updatedTotalOrderPrice = 0
            if(updatedCartItemsList.isNotEmpty()){
                for(x: CartItemEntity in updatedCartItemsList){
                    updatedTotalOrderPrice += x.totalCostOfCartItemEntity
                }
            }
            totalOrderPrice.value = updatedTotalOrderPrice
        }
    }

    fun removeCartItem(cartItemId:Int,username:String){
        CoroutineScope(Dispatchers.Main).launch {
            itemRepository.removeCartItem(cartItemId,username)
            loadCartItemsList()
        }
    }
}