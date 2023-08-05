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
import com.foodify.R
import com.foodify.databinding.FragmentItemDetailsBinding
import com.foodify.ui.viewmodel.ItemDetailsViewModel
import com.google.android.material.snackbar.Snackbar


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

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val tempViewModel: ItemDetailsViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun addToCart(view: View,itemName:String, itemPrice:String, itemQuantity:String){
        viewModel.addToCart(view,itemName,itemPrice,itemQuantity)
    }

    fun buttonIncrementClick(currentQuantity:String){
        viewModel.buttonIncrementClick(currentQuantity)
    }
    fun buttonDecrementClick(currentQuantity:String){
        viewModel.buttonDecrementClick(currentQuantity)
    }

}