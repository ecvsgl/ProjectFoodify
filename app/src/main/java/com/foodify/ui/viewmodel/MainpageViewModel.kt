package com.foodify.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodify.data.entity.Item
import com.foodify.data.repo.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainpageViewModel @Inject constructor (var itemRepository: ItemRepository): ViewModel() {
    var itemsList = MutableLiveData<List<Item>>()
    private var allItems : List<Item> = listOf()

    init {
        loadMenuItems()
    }
    fun loadMenuItems(){
        CoroutineScope(Dispatchers.Main).launch {
            allItems = itemRepository.loadMenuItems()
            itemsList.value = allItems
        }
    }
    fun filterList(query:String){
        CoroutineScope(Dispatchers.Main).launch{
            val filteredItems = if (query.isEmpty()) {
                allItems
            } else {
                allItems.filter { item -> item.itemName.contains(query, ignoreCase = true) }
            }
            // log the result
            Log.d("MainpageViewModel", "Filtered items count: ${filteredItems.size}")
            itemsList.value = filteredItems
        }
    }
}