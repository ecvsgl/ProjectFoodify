package com.foodify.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.foodify.R
import com.foodify.databinding.FragmentItemDetailsBinding
import com.foodify.ui.viewmodel.ItemDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ItemDetailsFragment : Fragment() {
    private lateinit var binding : FragmentItemDetailsBinding
    private lateinit var viewModel: ItemDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_item_details,container,false)
        binding.itemDetailsFragmentDataBindingVariable = this
        binding.itemDetailsToolbarTitle = "Product Details"

        viewModel.itemQuantity.observe(viewLifecycleOwner){
            binding.itemQuantityDataBindingVariable = it
        }

        val bundle: ItemDetailsFragmentArgs by navArgs()
        val incomingItem = bundle.item
        binding.itemEntityDataBindingVariable = incomingItem

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${incomingItem.itemPicture}"
        Glide.with(this).load(url).override(100,100).into(binding.imageViewItemDetailsPicture)


        binding.buttonAddToCart.setOnClickListener {
            if(binding.itemQuantityDataBindingVariable.toString().toInt()==1){
                Snackbar.make(it, "${binding.itemQuantityDataBindingVariable.toString()} piece of ${incomingItem.itemName} added to cart.", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(it, "${binding.itemQuantityDataBindingVariable.toString()} pieces of ${incomingItem.itemName} added to cart.", Snackbar.LENGTH_SHORT).show()
            }
            addToCart(incomingItem.itemId,incomingItem.itemName,incomingItem.itemPicture,incomingItem.ItemPrice,binding.itemQuantityDataBindingVariable.toString())
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val tempViewModel: ItemDetailsViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun addToCart(itemId:Int, itemName:String,itemPicture:String,itemPrice:Int,itemQuantity:String){
        viewModel.addToCart(itemId,itemName,itemPicture,itemPrice,itemQuantity.toInt())
    }

    fun buttonIncrementClick(currentQuantity:String){
        viewModel.buttonIncrementClick(currentQuantity)
    }
    fun buttonDecrementClick(currentQuantity:String){
        viewModel.buttonDecrementClick(currentQuantity)
    }

}