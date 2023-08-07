package com.foodify.di

import com.foodify.data.datasource.ItemsDataSource
import com.foodify.data.repo.ItemRepository
import com.foodify.retrofit.ApiUtils
import com.foodify.retrofit.ItemsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesItemRepository(itemsDataSource: ItemsDataSource) : ItemRepository{
        return ItemRepository(itemsDataSource)

    }
    @Provides
    @Singleton
    fun providesItemsDataSource(itemsDao: ItemsDao) : ItemsDataSource{
        return ItemsDataSource(itemsDao)
    }
    @Provides
    @Singleton
    fun providesItemsDao() : ItemsDao{
        return ApiUtils.getItemsDao()
    }

}