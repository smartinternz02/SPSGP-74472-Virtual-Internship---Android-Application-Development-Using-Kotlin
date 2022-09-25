package com.example.grocerylist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.MainActivity
import com.example.grocerylist.R
import com.example.grocerylist.data.model.BucketItem

class ItemAdapter(
    var list: List<BucketItem>,
    val groceryItemClickInterface: MainActivity
)
    : RecyclerView.Adapter<ItemAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idtvitemname)
        val quantityTV = itemView.findViewById<TextView>(R.id.idtvquantity)
        val rateTV = itemView.findViewById<TextView>(R.id.idtvrate)
        val totalTV = itemView.findViewById<TextView>(R.id.idtvtotalamount)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idivdelete)
    }


    interface GroceryItemClickInterface{
        fun onItemClick(groceryItems: BucketItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "₹: "+list.get(position).itemPrice.toString()
        val itemTotal :Double = list.get(position).itemQuantity * list.get(position).itemPrice
        holder.totalTV.text = "₹: "+itemTotal.toString()
        holder.deleteIV.setOnClickListener {
            groceryItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}