package com.example.tbchomework13

data class ChatMessage(
    val id:Int,
    val message:String,
    val date:String,
    val type: MessageType = MessageType.USER
)

enum class MessageType{
    USER,
    FRIEND
}