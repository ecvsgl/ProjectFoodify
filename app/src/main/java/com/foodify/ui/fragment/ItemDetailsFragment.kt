package com.foodify.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.foodify.R
import com.foodify.databinding.FragmentItemDetailsBinding
import com.google.android.material.snackbar.Snackbar


class ItemDetailsFragment : Fragment() {
    private lateinit var binding : FragmentItemDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_item_details,container,false)
        binding.itemDetailsFragmentDataBindingVariable = this
        binding.toolbarDetailspage.title = "Product Details"
        binding.itemQuantityDataBindingVariable = "1"

        val bundle: ItemDetailsFragmentArgs by navArgs()
        val incomingItem = bundle.item
        binding.itemEntityDataBindingVariable = incomingItem

        //binding.imageViewItemDetailsPicture.setImageResource() --> TBD

        binding.textViewDetailsItemName.text = incomingItem.itemName
        binding.textViewDetailsItemUnitPrice.text = "${incomingItem.ItemPrice} ₺"

        return binding.root
    }

    fun addToCart(view: View,itemName:String, itemPrice:String, itemQuantity:String){
        Snackbar.make(view, "$itemName added to cart!",Snackbar.LENGTH_SHORT).show()
        // ADD CART PERSISTENCE HERE
    }

    fun buttonIncrementClick(currentQuantity:String){
        val oldQuantity = currentQuantity.toInt()
        val newQuantity = oldQuantity+1
        binding.itemQuantityDataBindingVariable = newQuantity.toString()
    }
    fun buttonDecrementClick(currentQuantity:String){
        val oldQuantity = currentQuantity.toInt()
        if(oldQuantity>1){
            val newQuantity = oldQuantity-1
            binding.itemQuantityDataBindingVariable = newQuantity.toString()
        }
    }

}