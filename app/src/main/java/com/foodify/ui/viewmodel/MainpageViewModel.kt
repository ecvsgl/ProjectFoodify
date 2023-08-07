package com.foodify.ui.viewmodel

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

    init {
        loadMenuItems()
    }
    fun loadMenuItems(){
        CoroutineScope(Dispatchers.Main).launch {
            itemsList.value = itemRepository.loadMenuItems()
        }
    }
}