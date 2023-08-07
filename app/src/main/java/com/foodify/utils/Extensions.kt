package com.foodify.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation



fun Navigation.doPageTransfer(it:View, id:Int){
    Navigation.findNavController(it).navigate(id)
}

fun Navigation.doPageTransfer(it:View, id:NavDirections){
    Navigation.findNavController(it).navigate(id)
}