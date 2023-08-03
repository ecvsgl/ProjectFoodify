package com.foodify.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodify.data.repo.ItemRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemDetailsViewModel : ViewModel() {
    var itemQuantity = MutableLiveData( "1")
    var itemRepository = ItemRepository()

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
        Snackbar.make(view, "$itemName added to cart!", Snackbar.LENGTH_SHORT).show()
        // ADD CART PERSISTENCE HERE
    }
}