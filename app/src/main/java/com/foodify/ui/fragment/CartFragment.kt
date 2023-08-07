package com.foodify.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.foodify.R
import com.foodify.databinding.FragmentCartBinding
import com.foodify.databinding.FragmentMainpageBinding
import com.foodify.ui.adapter.CartAdapter
import com.foodify.ui.viewmodel.CartViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CartFragment : Fragment() {
    private lateinit var binding : FragmentCartBinding
    private lateinit var viewModel : CartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false)
        binding.cartToolbarTitle = "Checkout"
        binding.cartFragmentDataBindingVariable = this

        viewModel.cartItemsList.observe(viewLifecycleOwner){
            val adapter = it?.let {it -> CartAdapter(requireContext(),it,viewModel)}
            binding.cartAdapterDataBindingVariable = adapter
        }
        viewModel.totalOrderPrice.observe(viewLifecycleOwner){
            totalPrice -> binding.textViewCartCheckoutTotalCost.text = totalPrice.toString()
        }
        return binding.root
    }
    fun orderConfirmation(view:View){
        Log.e("Checkout", "OrderConfirmed")
        Snackbar.make(view, "Order confirmed. Thank you!", Snackbar.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onResume() {
        super.onResume()
        viewModel.loadCartItemsList()
    }
}