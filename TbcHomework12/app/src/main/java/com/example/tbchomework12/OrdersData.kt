package com.example.tbchomework12

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object OrdersData {
    var ordersList:MutableList<OrdersItem> = mutableListOf(
        OrdersItem(
            id = 1,
            orderId = 1000,
            trackingNumber = "LM123452",
            quantity = 2,
            date =reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 2,
            orderId = 1100,
            trackingNumber = "GM123452",
            quantity = 5,
            date =reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 3,
            orderId = 1200,
            trackingNumber = "GM123GS52",
            quantity = 12,
            date =reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 4,
            orderId = 1300,
            trackingNumber = "GM12FS3452",
            quantity = 9,
            date =reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 5,
            orderId = 1400,
            trackingNumber = "GM123HF452",
            quantity = 1,
            date = reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 6,
            orderId = 1500,
            trackingNumber = "GM123F452",
            quantity = 145,
            date = reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 7,
            orderId = 1600,
            trackingNumber = "GM123FF452",
            quantity = 10,
            date =reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 8,
            orderId = 1700,
            trackingNumber = "GM12BC3452",
            quantity = 4,
            date =reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 9,
            orderId = 1800,
            trackingNumber = "GM12DA3452",
            quantity = 27,
            date = reformatDate(123152525),
            price = 200
        ),
        OrdersItem(
            id = 10,
            orderId = 1900,
            trackingNumber = "GM12FS3452",
            quantity = 3,
            date = reformatDate(123152525),
            price = 200
        )

    )

    private fun reformatDate(time: Int): String {
        val timestamp = time.toLong()
        val date = Date(timestamp)
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return dateFormat.format(date)
    }
}