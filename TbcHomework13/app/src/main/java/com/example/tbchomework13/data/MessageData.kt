package com.example.tbchomework13.data

import com.example.tbchomework13.model.ChatMessage
import com.example.tbchomework13.model.MessageType

object MessageData {
    var messageList: MutableList<ChatMessage> = mutableListOf<ChatMessage>(
        ChatMessage(
            id = 2,
            message = "Hello",
            date = "13:30",
            type = MessageType.FRIEND
        ),
        ChatMessage(
            id = 3,
            message = "Hi",
            date = "13:31",
            type = MessageType.USER
        ),
        ChatMessage(
            id = 4,
            message = "How Are You?",
            date = "13:32",
            type = MessageType.FRIEND
        ),
        ChatMessage(
            id = 5,
            message = "Good. You?",
            date = "13:35",
            type = MessageType.USER
        ),

        )
}