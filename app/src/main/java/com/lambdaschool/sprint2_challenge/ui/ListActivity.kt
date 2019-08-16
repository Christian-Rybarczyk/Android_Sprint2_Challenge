package com.lambdaschool.sprint2_challenge.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.lambdaschool.sprint2_challenge.repository.ItemRepository
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.adapter.ShoppingListAdapter
import com.lambdaschool.sprint2_challenge.util.Notification
import kotlinx.android.synthetic.main.activity_main.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Shopping List"

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

    //Override to inflate our own menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            showInfo()
        }
        return true
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.about_title)
        val dialogMessage = getString(R.string.about_message)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
                .setMessage(dialogMessage)
                .create()
                .show()
    }
}
