package com.lambdaschool.sprint2_challenge.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lambdaschool.sprint2_challenge.ItemRepository
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.adapter.ShoppingListAdapter
import com.lambdaschool.sprint2_challenge.util.Notification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ItemRepository.createItemList()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ShoppingListAdapter(ItemRepository.itemList)

            button_send_list.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, getItemsSelected())
                intent.setType("text/plain")
                startActivity(intent)
                Notification.createNotification(applicationContext)
            }
        }
    }

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
