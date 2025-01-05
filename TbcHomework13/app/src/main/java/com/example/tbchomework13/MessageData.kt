package com.example.tbchomework13

object MessageData {
    var messageList: MutableList<ChatMessage> = mutableListOf<ChatMessage>(
        ChatMessage(
            id = 1,
            message = "Hello",
            date = "Today,13:30 pm",
            type = MessageType.FRIEND
        ),
        ChatMessage(
            id = 2,
            message = "Hi",
            date = "Today,13:31 pm",
            type = MessageType.USER
        ),
        ChatMessage(
            id = 3,
            message = "How Are You?",
            date = "Today,13:32 pm",
            type = MessageType.FRIEND
        ),
        ChatMessage(
            id = 4,
            message = "Good. You?",
            date = "Today,13:35 pm",
            type = MessageType.USER
        ),

        )
}