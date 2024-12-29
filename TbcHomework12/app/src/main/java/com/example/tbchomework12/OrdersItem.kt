package com.example.tbchomework12

data class OrdersItem(
    val id:Int,
    val orderId:Int,
    val trackingNumber: String,
    val quantity : Int,
    val date : String,
    val price : Int,
    var status: Status =Status.PENDING

)
enum class Status{
    PENDING,
    DELIVERED,
    CANCELLED
}
