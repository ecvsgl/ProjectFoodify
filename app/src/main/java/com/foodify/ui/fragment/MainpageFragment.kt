package com.foodify.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.foodify.R
import com.foodify.data.entity.Item
import com.foodify.databinding.FragmentMainpageBinding
import com.foodify.ui.adapter.ItemAdapter
import com.foodify.ui.viewmodel.MainpageViewModel
import com.foodify.utils.doPageTransfer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainpageFragment : Fragment() {
    private lateinit var binding: FragmentMainpageBinding
    private lateinit var viewModel : MainpageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_mainpage,container,false)
        binding.mainpageFragmentDataBindingVariable = this
        binding.mainpageToolbarTitle = "Foodify"

        viewModel.itemsList.observe(viewLifecycleOwner){
            val adapter = ItemAdapter(requireContext(),it,viewModel)
            binding.itemAdapterDataBindingVariable = adapter
        }
        return binding.root
    }
    fun floatingActionButtonClick(it:View){
        Navigation.doPageTransfer(it,R.id.routeMainpageToCart)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainpageViewModel by viewModels()
        viewModel = tempViewModel

    }
}