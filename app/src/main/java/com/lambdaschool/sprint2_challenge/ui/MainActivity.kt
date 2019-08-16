package com.lambdaschool.sprint2_challenge.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lambdaschool.sprint2_challenge.repository.ItemRepository
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.adapter.ShoppingListAdapter
import com.lambdaschool.sprint2_challenge.util.Notification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Generates item list
        ItemRepository.createItemList()

        //Sets properties of RecyclerView
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ShoppingListAdapter(ItemRepository.itemList)

            //Sends items selected list in plain text
            button_send_list.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, getItemsSelected())
                intent.setType("text/plain")
                startActivity(intent)
                //Sends notification confirming the order has been placed
                Notification.createNotification(applicationContext)
            }
        }
    }

    //Adds item name to the list if the item is selected
    private fun getItemsSelected() : String {
        var itemsSelectedString = "Please place this order for me: "
        for (item in ItemRepository.itemList) {
            if (item.isSelected) {
                itemsSelectedString += "${item.itemName}, "
            }
        }
        return itemsSelectedString
    }
}
