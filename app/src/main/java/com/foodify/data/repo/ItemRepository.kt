package com.foodify.data.repo

import com.foodify.data.datasource.ItemsDataSource

class ItemRepository {
    var itemsDataSource = ItemsDataSource()

    suspend fun buttonIncrementClick(currentQuantity:String) : String{
        return itemsDataSource.buttonIncrementClick(currentQuantity)
    }
    suspend fun buttonDecrementClick(currentQuantity:String) : String{
        return itemsDataSource.buttonDecrementClick(currentQuantity)
    }

}