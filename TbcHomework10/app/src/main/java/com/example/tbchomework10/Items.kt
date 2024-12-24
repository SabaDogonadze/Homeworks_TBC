package com.example.tbchomework10

data class Items(
    val id : Int,
    val image : Int,
    val title : String,
    val price : Int,
    val categoryType : List<String>,
    val buttonType: ButtonType
)

