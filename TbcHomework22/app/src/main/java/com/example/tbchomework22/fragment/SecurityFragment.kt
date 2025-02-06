package com.example.tbchomework22.fragment

import android.util.Log.d
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework22.adapter.PasswordInputRecyclerAdapter
import com.example.tbchomework22.model.PasswordItemType
import com.example.tbchomework22.adapter.PasswordOvalRecyclerAdapter
import com.example.tbchomework22.viewmodel.PasswordViewModel
import com.example.tbchomework22.databinding.FragmentSecurtiyBinding
import kotlinx.coroutines.launch


class SecurityFragment: BaseFragment<FragmentSecurtiyBinding>(FragmentSecurtiyBinding::inflate) {
    private lateinit var passwordAdapter: PasswordInputRecyclerAdapter
    private lateinit var passwordOvalAdapter: PasswordOvalRecyclerAdapter
    private val viewModel: PasswordViewModel by viewModels()
   /* private var userPassword = ""*/

    override fun setUp() {
        setUpPasswordInputRecyclerAdapter()
        setUpPasswordOvalRecyclerAdapter()
        clickListeners()
        observers()
    }

    override fun clickListeners() {
        passwordAdapter.setonItemClickedListener {
            when(it.passwordItemType){
                PasswordItemType.NUMBER, PasswordItemType.FINGER_PRINT ->{
                    d("123456","ITEM Clicked")
                    viewModel.updateOvals(false)
                    d("123456","notifyItemExecuted")
                    viewModel.makeUserPassword(it.item)
                    d("123456","${viewModel.userPassword}")
                    if (viewModel.userPassword.length == 4 && viewModel.isPasswordCorrect(viewModel.userPassword)) {
                        Toast.makeText(requireContext(), "Password is Correct", Toast.LENGTH_SHORT).show()
                        // toast will be shown many times but when password will be correct but in real projects new fragment will open so this will be not error
                    } else if (viewModel.userPassword.length == 4 && !viewModel.isPasswordCorrect(viewModel.userPassword)){
                        viewModel.userPassword = ""
                        viewModel.clearOvals()
                    }
                }
                PasswordItemType.DELETE ->{
                    d("123456","ITEM Clicked")
                    viewModel.removeDigitFromUserPassword()
                    viewModel.updateOvals(true)
                    d("123456","notifyItemExecuted")
                }
            }
        }
    }

    private fun setUpPasswordInputRecyclerAdapter(){
        passwordAdapter = PasswordInputRecyclerAdapter(viewModel.passwordItems)
        binding.apply {
            recyclerViewPasscode.layoutManager = GridLayoutManager(context,3)
            recyclerViewPasscode.adapter = passwordAdapter
        }
    }

    private fun setUpPasswordOvalRecyclerAdapter(){
        passwordOvalAdapter = PasswordOvalRecyclerAdapter(viewModel.ovals.value)
        binding.apply {
            recyclerViewInputOvals.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            recyclerViewInputOvals.adapter = passwordOvalAdapter
        }
    }

    private fun observers(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.ovals.collect{
                    passwordOvalAdapter.updateList(it)
                }
            }
        }
    }
    /*private fun makeUserPassword(number:Int){
        d("kkkk ","$userPassword")
        if(userPassword.length < UserPassword.userPassword.length){
            userPassword += number.toString()
        }
        d("kkkk","$userPassword")
    }*/
    /*private fun removeDigitFromUserPassword(){
        userPassword = userPassword.dropLast(1)
    }*/
}