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
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.foodify.R
import com.foodify.data.entity.Item
import com.foodify.databinding.FragmentMainpageBinding
import com.foodify.ui.adapter.ItemAdapter

class MainpageFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentMainpageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainpageBinding.inflate(inflater,container,false)

        binding.toolbarMainpage.title = "Foodify"

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMainpage)

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        val itemsList = ArrayList<Item>()

        val itemEntity1 = Item(1,"Sütlaç", "ec", 3)
        val itemEntity2 = Item(2,"Dondurma", "ec", 17)
        val itemEntity3 = Item(3,"Baklava", "ec", 300)

        itemsList.add(itemEntity1)
        itemsList.add(itemEntity2)
        itemsList.add(itemEntity3)

        val adapter = ItemAdapter(requireContext(), itemsList)
        binding.recyclerView.adapter = adapter

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

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.routeMainpageToCart)
        }
        return binding.root
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        search(newText)
        return true
    }

    fun search(searchString: String){
        Log.e("Item Search", searchString)
    }

}