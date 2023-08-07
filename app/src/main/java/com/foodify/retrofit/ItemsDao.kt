package com.foodify.retrofit

import android.view.View
import com.foodify.data.entity.CRUDResponse
import com.foodify.data.entity.CartItemResponse
import com.foodify.data.entity.ItemResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field


interface ItemsDao {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadMenuItems() : ItemResponse
    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(@Field("yemek_adi") itemName:String,
                          @Field("yemek_resim_adi") itemPicture:String,
                          @Field("yemek_fiyat") itemPrice:Int,
                          @Field("yemek_siparis_adet") itemQuantity:Int,
                          @Field("kullanici_adi") username:String) : CRUDResponse
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun loadCartItemsList(@Field("kullanici_adi") username: String) : CartItemResponse
    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun removeCartItem(@Field("sepet_yemek_id") cartItemId:Int,
                               @Field("kullanici_adi") username:String) : CRUDResponse
}