package com.example.grocerylist.ui

import androidx.lifecycle.ViewModel
import com.example.grocerylist.data.model.BucketItem
import com.example.grocerylist.data.repository.ItemRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository): ViewModel() {
    fun insert(items: BucketItem) = GlobalScope.launch {
        repository.insert(items)
    }
    fun delete(items: BucketItem) = GlobalScope.launch {
        repository.delete(items)
    }
    fun getAllGroceryItems() = repository.getAllItems()
}