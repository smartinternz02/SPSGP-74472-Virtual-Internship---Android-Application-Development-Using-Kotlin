package com.example.grocerylist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.grocerylist.data.repository.ItemRepository

class ItemViewModelFactory(private val repository: ItemRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(repository) as T
    }
}