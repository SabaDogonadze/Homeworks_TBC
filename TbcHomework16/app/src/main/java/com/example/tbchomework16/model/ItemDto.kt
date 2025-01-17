package com.example.tbchomework16.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Data(
    @SerialName("field_id")
    val fieldId :Int,
    @SerialName("hint")
    val hint : String,
    @SerialName("field_type")
    val fieldType:String,
    @SerialName("keyboard")
    val keyboard : String?= null,
    @SerialName("required")
    val required : Boolean,
    @SerialName("is_active")
    val isActive : Boolean,
    @SerialName("icon")
    val icon : String
)

@Serializable
data class Response(
   /* val id:Int,*/
    val items: List<Data>
)
