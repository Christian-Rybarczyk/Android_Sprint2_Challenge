package com.lambdaschool.sprint2_challenge.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.model.ShoppingItem
import kotlinx.android.synthetic.main.shopping_list_item.view.*
import kotlin.contracts.contract

class ShoppingListAdapter(val shoppingList: MutableList<ShoppingItem>) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.shopping_list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = shoppingList[p1]

        p0.bindModel(item)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.item_image
        val name: TextView = view.item_name
        val parentLayout: LinearLayout = view.item_parent_layout

        fun bindModel(item: ShoppingItem) {
            image.setImageResource(item.imageId)
            name.text = item.itemName
            if (item.isSelected) {
                parentLayout.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            } else {
                parentLayout.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.white))
            }
        }
    }
}