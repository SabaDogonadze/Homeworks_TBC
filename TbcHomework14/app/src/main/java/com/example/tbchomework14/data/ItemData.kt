package com.example.tbchomework14.data

import android.graphics.Color
import com.example.tbchomework14.R
import com.example.tbchomework14.model.ItemModel
import com.example.tbchomework14.model.ItemStatus

object ItemData {
    var itemData : MutableList<ItemModel> = mutableListOf(
        ItemModel(
            id = 1,
            image = R.drawable.chair_gold,
            name = "Red Chair",
            color = Color.RED,
            quantity = 2,
            status = ItemStatus.ACTIVE,
            price = 130
        ),
        ItemModel(
            id = 2,
            image = R.drawable.chair_gold,
            name = "RED Chair",
            color = Color.RED,
            quantity = 22,
            status = ItemStatus.COMPLETED,
            price = 140
        ),
        ItemModel(
            id = 3,
            image = R.drawable.chair_gold,
            name = "GREEN Chair",
            color = Color.GREEN,
            quantity = 12,
            status = ItemStatus.COMPLETED,
            price = 150
        ),
        ItemModel(
            id = 4,
            image = R.drawable.chair_gold,
            name = "BLUE Chair",
            color = Color.BLUE,
            quantity = 322,
            status = ItemStatus.COMPLETED,
            price = 160
        ),
        ItemModel(
            id = 5,
            image = R.drawable.chair_gold,
            name = "GREEN Chair",
            color = Color.GREEN,
            quantity = 32,
            status = ItemStatus.ACTIVE,
            price = 170
        ),
        ItemModel(
            id = 6,
            image = R.drawable.chair_gold,
            name = "GRAY Chair",
            color = Color.GRAY,
            quantity = 52,
            status = ItemStatus.COMPLETED,
            price = 180
        ),
        ItemModel(
            id = 7,
            image = R.drawable.chair_gold,
            name = "BLACK Chair",
            color = Color.BLACK,
            quantity = 3512,
            status = ItemStatus.COMPLETED,
            price = 190
        ),
        ItemModel(
            id = 8,
            image = R.drawable.chair_gold,
            name = "BLACK Chair",
            color = Color.BLACK,
            quantity = 122,
            status = ItemStatus.ACTIVE,
            price = 200
        )

    )
}