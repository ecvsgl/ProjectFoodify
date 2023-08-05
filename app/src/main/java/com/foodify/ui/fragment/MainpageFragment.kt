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

class MainpageFragment : Fragment(), SearchView.OnQueryTextListener {
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
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMainpage)

        viewModel.itemsList.observe(viewLifecycleOwner){
            val adapter = ItemAdapter(requireContext(),it,viewModel)
            binding.itemAdapterDataBindingVariable = adapter
        }

        requireActivity().addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu,menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@MainpageFragment)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }
    fun floatingActionButtonClick(it:View){
        Navigation.findNavController(it).navigate(R.id.routeMainpageToCart)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainpageViewModel by viewModels()
        viewModel = tempViewModel

    }
    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadMenuItems()
    }
}