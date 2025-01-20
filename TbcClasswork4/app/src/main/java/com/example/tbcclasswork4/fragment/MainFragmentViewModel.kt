package com.example.tbcclasswork4.fragment

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcclasswork4.model.MessageModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainFragmentViewModel: ViewModel() {
    private val _userRegisterResponse = MutableStateFlow<List<MessageModel>?>(null)
    val userRegisterResponse = _userRegisterResponse.asStateFlow()

    val data = """[
        {
            "id":1,
            "image":"https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg",
            "owner":"გრიშა ონიანი",
            "last_message":"თავის ტერიტორიას ბომბავდა",
            "last_active":"4:20 PM",
            "unread_messages":3,
            "is_typing":false,
            "laste_message_type":"text"
        },
        {
            "id":2,
            "image":null,
            "owner":"ჯემალ კაკაურიძე",
            "last_message":"შემოგევლე",
            "last_active":"3:00 AM",
            "unread_messages":0,
            "is_typing":true,
            "laste_message_type":"voice"
        },
        {
            "id":3,
            "image":"https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg",
            "owner":"გურამ ჯინორია",
            "last_message":"ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა",
            "last_active":"1:00 ",
            "unread_messages":0,
            "is_typing":false,
            "laste_message_type":"file"
        },
        {
            "id":4,
            "image":"",
            "owner":"კაკო წენგუაშვილი",
            "last_message":"ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი",
            "last_active":"1:00 PM",
            "unread_messages":0,
            "is_typing":false,
            "laste_message_type":"text"
        }
    ]"""
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val type = Types.newParameterizedType(List::class.java, MessageModel::class.java)
    val jsonAdapter: JsonAdapter<List<MessageModel>> = moshi.adapter(type)

    val deserializedData:List<MessageModel>? = jsonAdapter.fromJson(data)

    init {
        val deserializedData: List<MessageModel>? = jsonAdapter.fromJson(data)
        _userRegisterResponse.value = deserializedData
    }


    fun filterMessage(userName:String){
        viewModelScope.launch(Dispatchers.IO) {
             val filteredList = deserializedData?.filter { it.owner == userName }
            _userRegisterResponse.value = filteredList
            d("12345", "$deserializedData")
        }
    }


}