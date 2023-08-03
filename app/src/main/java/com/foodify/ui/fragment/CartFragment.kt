package com.foodify.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foodify.R
import com.foodify.databinding.FragmentCartBinding
import com.foodify.databinding.FragmentMainpageBinding
import com.google.android.material.snackbar.Snackbar

class CartFragment : Fragment() {
    private lateinit var binding : FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)

        binding.toolbarCartpage.title = "Checkout"

        binding.buttonCartApprove.setOnClickListener {
            Log.e("Checkout", "OrderConfirmed")
            Snackbar.make(it, "Order confirmed. Thank you!", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }
}