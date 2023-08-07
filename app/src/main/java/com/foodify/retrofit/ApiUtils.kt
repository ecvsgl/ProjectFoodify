package com.foodify.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getItemsDao() : ItemsDao {
            return RetrofitClient.getClient(BASE_URL).create(ItemsDao::class.java)
        }
    }
}