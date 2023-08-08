# Project Foodify - A Basic Food-Ordering Android App 

## Introduction

This is an Android App project based on Kotlin, using MVVM architecture. Project is created as a final capstone project for Sabancı University - Mobile Programming with Kotlin course. 

## Under the Hood

The project uses API endpoints through Retrofit2 library, that provide several functionalities; getting all goods, adding to cart, get all cart items, remove a cart item. Glide is used to bind the good pictures into the fragments/adapters. Dagger-Hilt is used for dependancy injection of Singleton objects (Repository/Datasource/DAO). MVVM architecture is followed through the project (with a few exceptions for the sake of ease of development). Moreover, the fragments and adapter designs are developed with adherance to use Databinding (with a few exceptions). At the end, a searchView object is used for filtering the mainpage adapter items in the recyclerView.

## UI Screenshots

