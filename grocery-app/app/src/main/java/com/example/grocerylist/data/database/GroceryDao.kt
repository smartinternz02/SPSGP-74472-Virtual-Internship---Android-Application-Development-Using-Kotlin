package com.example.grocerylist.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.grocerylist.data.model.BucketItem

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BucketItem)

    @Delete
    suspend fun delete(item: BucketItem)

    @Query("SELECT * FROM Grocery_items")
    fun getAllGroceryItems(): LiveData<List<BucketItem>>


}