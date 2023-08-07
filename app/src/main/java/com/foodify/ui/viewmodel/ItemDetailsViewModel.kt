package com.foodify.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodify.data.repo.ItemRepository
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor (var itemRepository: ItemRepository) : ViewModel() {
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

    fun addToCart(itemId:Int,itemName:String,itemPicture:String,itemPrice:Int,itemQuantity:Int){
        CoroutineScope(Dispatchers.Main).launch {
            itemRepository.addToCart(itemId,itemName,itemPicture,itemPrice,itemQuantity)
        }
    }
}