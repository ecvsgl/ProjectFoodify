package com.foodify.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodify.R
import com.foodify.data.entity.Item
import com.foodify.databinding.CardItemDesignBinding
import com.foodify.ui.fragment.MainpageFragmentDirections
import com.foodify.ui.viewmodel.MainpageViewModel
import com.foodify.utils.doPageTransfer

class ItemAdapter (var mContext:Context, var itemsList:List<Item>, var viewModel: MainpageViewModel)
    : RecyclerView.Adapter<ItemAdapter.CardDesignHolder>(){
    inner class CardDesignHolder(var design:CardItemDesignBinding) : RecyclerView.ViewHolder(design.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding : CardItemDesignBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.card_item_design,parent,false)
        return CardDesignHolder(binding)
    }
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val item = itemsList.get(position)
        val d = holder.design

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${item.itemPicture}"
        Glide.with(mContext).load(url).override(140,140).into(d.imageViewItemPicture)

        d.itemEntityDataBindingVariable = item

        d.imageViewItemDetailsTransfer.setOnClickListener {
            val transfer = MainpageFragmentDirections.routeMainpageToDetails(item = item)
            Navigation.doPageTransfer(it,transfer)
        }
        d.imageViewItemPicture.setOnClickListener {
            val transfer = MainpageFragmentDirections.routeMainpageToDetails(item = item)
            Navigation.doPageTransfer(it,transfer)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
    fun updateItems(newItems : List<Item>){
        this.itemsList = newItems
        notifyDataSetChanged()
    }

}