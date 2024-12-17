package com.example.tbchomework8

import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tbchomework8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userList: MutableList<User> = mutableListOf()
    private var deletedUserNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        listeners()
    }

    private fun listeners() {
        binding.btnAddUser.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString().toIntOrNull()
            val email = binding.etEmail.text.toString()
            if (userList.none{it.email == email}){
                addNewUser(User(firstName, lastName, age?:0, email))
            }else
            { Toast.makeText(this, getString(R.string.user_already_exists), Toast.LENGTH_LONG).show()
            }
        }
        binding.btnDeleteUser.setOnClickListener {
            val email = binding.etEmail.text.toString()
            deleteUser(email)
        }
        binding.btnUpdateUser.setOnClickListener {
            val email = binding.etEmail.text.toString()
            updateUserInfo(email)
        }
    }

    private fun addNewUser(user: User) {
        if (checkInputs()) {
            if (user !in userList) {
                userList.add(user)
                activeUserCounter()
                Toast.makeText(this, getString(R.string.user_added_successfully), Toast.LENGTH_LONG).show()
                resetInputStatus()
                d("12345", "{$userList}")
            } else {
                Toast.makeText(this, getString(R.string.user_already_exists), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deleteUser(email: String) {

        val user = userList.find { it.email == email }
        if (user in userList) {
            deletedUserCounter()
            userList.remove(user)
            Toast.makeText(this, getString(R.string.user_deleted_successfully), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, getString(R.string.user_does_not_exists), Toast.LENGTH_LONG).show()
        }
        activeUserCounter()
    }

    private fun deletedUserCounter() {
        deletedUserNumber++
        binding.tvCounterDeleteUser.text = deletedUserNumber.toString()
    }

    private fun activeUserCounter() {
        binding.tvCounterActiveUser.text = userList.size.toString()
    }

    private fun checkInputs(): Boolean {
        val firstName = InputsValidation(binding).isFirstNameValid(binding.etFirstName.text.toString())
        val lastName = InputsValidation(binding).isLastNameValid(binding.etLastName.text.toString())
        val age = InputsValidation(binding).isAgeValid(binding.etAge.text.toString())
        val email = InputsValidation(binding).isEmailValid(binding.etEmail.text.toString())
        val emailPattern = EmailValidation(binding).emailValidation(binding.etEmail.text.toString())

        return firstName && lastName && age && email &&  emailPattern
    }

    private fun updateUserInfo(email:String){
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()
        val newEmail = "$firstName$lastName$age@gmail.com"
        val user = userList.find { it.email == email }
        if(user in userList){
            user?.email = newEmail
            d("12345", "{$userList}")
        }
    }

    private fun resetInputStatus(){
        binding.tvFirstNameStatus.visibility = View.GONE
        binding.tvLastNameStatus.visibility = View.GONE
        binding.tvAgeStatus.visibility = View.GONE
        binding.tvEmailStatus.visibility = View.GONE
    }
}