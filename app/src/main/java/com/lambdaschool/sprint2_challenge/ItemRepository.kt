package com.lambdaschool.sprint2_challenge

import com.lambdaschool.sprint2_challenge.model.ShoppingItem

class ItemRepository {
    companion object {
        val itemList = mutableListOf<ShoppingItem>()

        fun createItemList() {
            for (i in 0 until ITEM_NAMES_RAW.size) {
                itemList.add(ShoppingItem(ITEM_NAMES_RAW[i], ICON_IDS[i], false))
            }
        }
    }
}