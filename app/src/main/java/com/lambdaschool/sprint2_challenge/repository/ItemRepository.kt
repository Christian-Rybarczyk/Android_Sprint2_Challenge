package com.lambdaschool.sprint2_challenge.repository

import com.lambdaschool.sprint2_challenge.ICON_IDS
import com.lambdaschool.sprint2_challenge.ITEM_NAMES_RAW
import com.lambdaschool.sprint2_challenge.model.ShoppingItem

class ItemRepository {
    companion object {
        //Create a list of ShoppingItems
        val itemList = mutableListOf<ShoppingItem>()

        fun createItemList() {
            for (i in 0 until ITEM_NAMES_RAW.size) {
                val itemName = ITEM_NAMES_RAW[i]
                        .replace("_", " ") //replace "_" in item name with a space
                itemList.add(ShoppingItem(itemName, ICON_IDS[i], false))
            }
        }
    }
}