package com.foodify.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodify.R
import com.foodify.data.entity.CartItemEntity
import com.foodify.databinding.CartOrderItemDesignBinding
import com.foodify.ui.viewmodel.CartViewModel
import com.google.android.material.snackbar.Snackbar

class CartAdapter (var mContext:Context, var cartItemsList:List<CartItemEntity>, var cartViewModel: CartViewModel)
    : RecyclerView.Adapter<CartAdapter.CardDesignHolder>(){
    inner class CardDesignHolder(var design:CartOrderItemDesignBinding) : RecyclerView.ViewHolder(design.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding : CartOrderItemDesignBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.cart_order_item_design,parent,false)
        return CardDesignHolder(binding)
    }
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val cartItem = cartItemsList.get(position)
        val d = holder.design
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartItem.cartItemPicture}"
        Glide.with(mContext).load(url).override(100,100).into(d.imageViewItemPicture)

        d.cartItemEntityDataBindingVariable = cartItem

        d.imageViewCartItemRemoveButton.setOnClickListener {
            Snackbar.make(it,"${cartItem.cartItemName} removed from cart.",Snackbar.LENGTH_SHORT).show()
            removeCartItem(cartItem.cartItemId,cartItem.username)
        }
    }
    fun removeCartItem(cartItemId:Int,username:String){
        cartViewModel.removeCartItem(cartItemId,username)
    }
    override fun getItemCount(): Int {
        return cartItemsList.size
    }
}