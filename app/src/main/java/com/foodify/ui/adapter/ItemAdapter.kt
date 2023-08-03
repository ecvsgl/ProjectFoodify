package com.foodify.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.foodify.data.entity.Item
import com.foodify.databinding.CardItemDesignBinding
import com.foodify.ui.fragment.MainpageFragmentDirections
import com.foodify.utils.doPageTransfer

class ItemAdapter (var mContext:Context, var itemsList:List<Item>)
    : RecyclerView.Adapter<ItemAdapter.CardDesignHolder>(){

    inner class CardDesignHolder(var design:CardItemDesignBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardItemDesignBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val item = itemsList.get(position)
        val d = holder.design

        d.textViewItemName.text = item.itemName
        d.textViewItemPrice.text = item.ItemPrice.toString()

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
}