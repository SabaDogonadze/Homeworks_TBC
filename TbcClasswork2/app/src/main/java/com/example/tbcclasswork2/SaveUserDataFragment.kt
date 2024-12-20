package com.example.tbcclasswork2

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tbcclasswork2.databinding.FragmentHomeBinding
import com.example.tbcclasswork2.databinding.FragmentSaveUserDataBinding


class SaveUserDataFragment : Fragment() {
    private var binding: FragmentSaveUserDataBinding? = null
    private var userList: MutableList<User> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveUserDataBinding.inflate(inflater, container, false)
        arguments?.let {
            userList = it.getParcelableArrayList<User>("userList") ?: mutableListOf()
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        listeners()
    }

    private fun listeners() {
        binding!!.btnAddUser.setOnClickListener {
            addNewUser()
        }
    }

    private fun addNewUser() {
        val firstName = binding!!.tvFirstName.text.toString()
        val lastName = binding!!.tvLastName.text.toString()
        val id = binding!!.tvUserId.text.toString().toIntOrNull() ?: 0
        val email = binding!!.tvEmail.text.toString()
        val birtDay = binding!!.tvBirthday.text.toString()
        val desc = binding!!.tvBirthday.text.toString()

        val newUser = User(id, firstName, lastName, birtDay, "", email,desc)
        userList.add(newUser)

        d("12345", "$userList")

        val bundle = Bundle()
        bundle.putParcelableArrayList("userList", ArrayList(userList))

        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, homeFragment)
            .addToBackStack(null)
            .commit()
    }


}