package com.example.grocerylist

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.data.database.GroceryDatabase
import com.example.grocerylist.data.model.BucketItem
import com.example.grocerylist.data.repository.ItemRepository
import com.example.grocerylist.ui.ItemAdapter
import com.example.grocerylist.ui.ItemViewModel
import com.example.grocerylist.ui.ItemViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var itemRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<BucketItem>
    lateinit var groceryRVAdapter: ItemAdapter
    lateinit var groceryViewModel: ItemViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemRV = findViewById(R.id.rvitems)
        addFAB = findViewById(R.id.fabAdd)
        list = ArrayList()
        groceryRVAdapter = ItemAdapter(list,this)
        itemRV.layoutManager = LinearLayoutManager(this)
        itemRV.adapter = groceryRVAdapter
        val groceryRepository = ItemRepository(GroceryDatabase(this))
        val factory = ItemViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this,factory).get(ItemViewModel::class.java)
        groceryViewModel.getAllGroceryItems().observe(this, Observer {
            groceryRVAdapter.list = it
            groceryRVAdapter.notifyDataSetChanged()
        })
        addFAB.setOnClickListener{
            openDialog()
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_box)
        val cancelbtn = dialog.findViewById<AppCompatButton>(R.id.idbtncancel)
        val addbtn = dialog.findViewById<AppCompatButton>(R.id.idbtnadd)
        val itemEdt = dialog.findViewById<EditText>(R.id.idEdtitemname)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.idEdtitemprice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.idEdtitemquantity)
        cancelbtn.setOnClickListener {
            dialog.dismiss()
        }
        addbtn.setOnClickListener {
            val itemname:String = itemEdt.text.toString()
            val itemprice:String = itemPriceEdt.text.toString()
            val itemquantity:String = itemQuantityEdt.text.toString()
            val qty : Double = itemquantity.toDouble()
            val pr : Double = itemprice.toDouble()
            if (itemname.isNotEmpty() && itemprice.isNotEmpty() && itemquantity.isNotEmpty()){
                val items = BucketItem(itemname,qty,pr)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Added",Toast.LENGTH_SHORT).show()
                groceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext,"Please fill all details",Toast.LENGTH_SHORT).show()
            }

        }
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onItemClick(groceryItems: BucketItem) {
        groceryViewModel.delete(groceryItems)
        groceryRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted Successfully..",Toast.LENGTH_SHORT).show()
    }
}