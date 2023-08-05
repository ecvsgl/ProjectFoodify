package com.foodify.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodify.data.repo.ItemRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemDetailsViewModel (var itemRepository: ItemRepository) : ViewModel() {
    var itemQuantity = MutableLiveData( "1")

    fun buttonIncrementClick(currentQuantity:String){
        CoroutineScope(Dispatchers.Main).launch{
            itemQuantity.value = itemRepository.buttonIncrementClick(currentQuantity)
        }
    }
    fun buttonDecrementClick(currentQuantity:String){
        CoroutineScope(Dispatchers.Main).launch{
            itemQuantity.value = itemRepository.buttonDecrementClick(currentQuantity)
        }
    }

    fun addToCart(view: View, itemName:String, itemPrice:String, itemQuantity:String){
        CoroutineScope(Dispatchers.Main).launch {
            itemRepository.addToCart(view,itemName,itemPrice,itemQuantity)
        }
    }
}