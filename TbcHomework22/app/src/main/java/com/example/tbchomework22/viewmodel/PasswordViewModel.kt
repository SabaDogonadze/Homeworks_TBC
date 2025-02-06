package com.example.tbchomework22.viewmodel

import android.util.Log.d
import androidx.lifecycle.ViewModel
import com.example.tbchomework22.model.OvalStatus
import com.example.tbchomework22.model.PasswordItemType
import com.example.tbchomework22.model.PasswordItems
import com.example.tbchomework22.model.PasswordOvals
import com.example.tbchomework22.R
import com.example.tbchomework22.UserPassword
import kotlinx.coroutines.flow.MutableStateFlow

class PasswordViewModel : ViewModel() {

    val passwordItems = listOf(
        PasswordItems(1, 1, PasswordItemType.NUMBER),
        PasswordItems(2, 2, PasswordItemType.NUMBER),
        PasswordItems(3, 3, PasswordItemType.NUMBER),
        PasswordItems(4, 4, PasswordItemType.NUMBER),
        PasswordItems(5, 5, PasswordItemType.NUMBER),
        PasswordItems(6, 6, PasswordItemType.NUMBER),
        PasswordItems(7, 7, PasswordItemType.NUMBER),
        PasswordItems(8, 8, PasswordItemType.NUMBER),
        PasswordItems(9, 9, PasswordItemType.NUMBER),
        PasswordItems(10, R.drawable.icon_touch, PasswordItemType.FINGER_PRINT),
        PasswordItems(11, 0, PasswordItemType.NUMBER),
        PasswordItems(12, R.drawable.icon_backspace, PasswordItemType.DELETE),
    )
    private val _ovals = MutableStateFlow(
        listOf(
            PasswordOvals(1, OvalStatus.UNCHECKED),
            PasswordOvals(2, OvalStatus.UNCHECKED),
            PasswordOvals(3, OvalStatus.UNCHECKED),
            PasswordOvals(4, OvalStatus.UNCHECKED),
        )
    )
    val ovals: MutableStateFlow<List<PasswordOvals>> = _ovals
    var userPassword = ""
    private var passwordInputCounter = 0

    fun updateOvals(pressedDelete: Boolean) {
        val currentList = _ovals.value.toMutableList()
        d("kkkk","$currentList")
        d("kkkk","${currentList.size}")
        d("kkkk","$passwordInputCounter")
        if (pressedDelete) {
            if (passwordInputCounter > 0) {
                passwordInputCounter--
            }
        }else {
            if (passwordInputCounter < currentList.size) {
                passwordInputCounter++
            }
        }

        val updatedList = _ovals.value.mapIndexed { index, passwordOvals ->
            passwordOvals.copy(ovalStatus = if (index < passwordInputCounter) OvalStatus.CHECKED else OvalStatus.UNCHECKED)
        }
        _ovals.value = updatedList
    }
    fun isPasswordCorrect(password:String):Boolean{
        if ( password == UserPassword.userPassword){
          return true
        }else{
           return false
        }
    }

    fun clearOvals(){
        passwordInputCounter = 0
        _ovals.value = _ovals.value.map { it.copy(ovalStatus = OvalStatus.UNCHECKED) }
    }

     fun removeDigitFromUserPassword(){
        userPassword = userPassword.dropLast(1)
    }

     fun makeUserPassword(number:Int){
        d("kkkk ","$userPassword")
        if(userPassword.length < UserPassword.userPassword.length){
            userPassword += number.toString()
        }
        d("kkkk","$userPassword")
    }

}