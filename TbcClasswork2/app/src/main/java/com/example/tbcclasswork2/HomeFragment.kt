package com.example.tbcclasswork2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.tbcclasswork2.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private var userData: List<User> = mutableListOf()
    private var filteredUserData: MutableList<User> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpUserData()
        arguments?.let {
            userData = it.getParcelableArrayList<User>("userList") ?: mutableListOf()
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        listeners()
    }

    private fun listeners() {
        binding!!.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                filterUserData(text)
                return true
            }

        }
        )
    }


    private fun filterUserData(text: String?) {
        filteredUserData = if (text.isNullOrEmpty()) {
            userData.toMutableList()
        } else {
            userData.filter { user ->
                user.firstName.contains(text, ignoreCase = true) ||
                        user.lastName.contains(text, ignoreCase = true) ||
                        user.email.contains(text, ignoreCase = true) ||
                        user.id.toString().contains(text)
                        || user.birthDay.contains(text)

            }.toMutableList()
        }
        if (filteredUserData.isEmpty()) {
            Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show()
            binding!!.btnAddUser.setOnClickListener {
                addAnotherFragment(SaveUserDataFragment(), "SaveUserFragment")
            }
        }
        val userNames = filteredUserData.joinToString("\n") { "${it.firstName} ${it.lastName}" }
        binding!!.tvUserInfo.text = userNames

        if (filteredUserData.isNotEmpty()) {
            val user = filteredUserData.first()
            binding!!.tvUserId.text = user.id.toString()
            binding!!.tvFirstName.text = user.firstName
            binding!!.tvLastName.text = user.lastName
            val birthDay = reformatBirthday(user.birthDay)
            binding!!.tvBirthday.text = birthDay
            binding!!.tvAddress.text = user.address
            binding!!.tvEmail.text = user.email
            binding!!.tvDesc.text = user.desc ?: "" // not working
        }

    }

    private fun addAnotherFragment(fragment: Fragment, name: String) {

        val bundle = Bundle()
        bundle.putParcelableArrayList("userList", ArrayList(filteredUserData))
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()    // adding new fragment.
            .replace(R.id.fragmentContainer, fragment).addToBackStack(name).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun reformatBirthday(birthday: String): String {
        val timestamp = birthday.toLong()
        val date = Date(timestamp)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }


    private fun setUpUserData() {
        userData = mutableListOf(
            User(
                id = 1,
                firstName = "გრიშა",
                lastName = "ონიანი",
                birthDay = "1724647601641",
                address = "სტალინის სახლმუზეუმი",
                email = "grisha@mail.ru"
            ),
            User(
                id = 2,
                firstName = "Jemal",
                lastName = "Kakauridze",
                birthDay = "1714647601641",
                address = "თბილისი, ლილოს მიტოვებული ქარხანა",
                email = "jemal@gmail.com"
            ),
            User(
                id = 2,
                firstName = "Omger",
                lastName = "Kakauridze",
                birthDay = "1724647701641",
                address = "თბილისი, ასათიანი 18",
                email = "omger@gmail.com"
            ),
            User(
                id = 32,
                firstName = "ბორის",
                lastName = "გარუჩავა",
                birthDay = "1714947701641",
                address = "თბილისი, იაშვილი 14",
                email = ""
            ),
            User(
                id = 34,
                firstName = "აბთო",
                lastName = "სიხარულიძე",
                birthDay = "1711947701641",
                address = "ფოთი",
                email = "tebzi@gmail.com",
            )
        )

    }


}

