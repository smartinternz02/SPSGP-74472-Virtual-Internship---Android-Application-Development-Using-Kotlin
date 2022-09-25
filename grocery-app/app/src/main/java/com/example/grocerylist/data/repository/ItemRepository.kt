package com.example.grocerylist.data.repository

import com.example.grocerylist.data.database.GroceryDatabase
import com.example.grocerylist.data.model.BucketItem

class ItemRepository(private val db: GroceryDatabase) {
    suspend fun insert(items: BucketItem) = db.getGroceryDao().insert(items)
    suspend fun delete(items: BucketItem) = db.getGroceryDao().delete(items)

    fun getAllItems() = db.getGroceryDao().getAllGroceryItems()
}