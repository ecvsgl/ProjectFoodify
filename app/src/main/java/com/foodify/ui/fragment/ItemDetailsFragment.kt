package com.foodify.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.foodify.databinding.FragmentItemDetailsBinding

class ItemDetailsFragment : Fragment() {
    private lateinit var binding : FragmentItemDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetailsBinding.inflate(inflater,container,false)

        binding.toolbarDetailspage.title = "Select Order Amount"

        val bundle: ItemDetailsFragmentArgs by navArgs()
        val incomingItem = bundle.item

        //binding.itemImage.set(...nasıl bilmiyorum recheck...)
        //binding.itemName.setText(incomingItem.itemName)
        //binding.itemPrice.setText(incomingItem.itemPrice)

        binding.buttonAddToCart.setOnClickListener {
            /*
            val itemCount = binding.itemOrderAmount.text.toString()
            val itemId = binding.itemId.text.toString()
            addToCart()
            */

        }

        return binding.root
    }

    fun addToCart(itemCount:String, itemId:String){
        Log.e("Item persistance to Cart Performed", "$itemCount - $itemId")

    }

}