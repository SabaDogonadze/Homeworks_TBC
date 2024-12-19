package com.example.tbchomework9

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.addTextChangedListener
import com.example.tbchomework9.databinding.FragmentHomeBinding
import com.example.tbchomework9.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private var binding: FragmentSignUpBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listeners()
    }

    private fun listeners() {
        binding!!.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding!!.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    setUpActiveEditTextsColors()
                    setUpActiveEditTextBackground()
                    setUpSignUpButtonWhileActive()

                } else {
                    setUpActiveEditTextsColors()
                    setUpActiveEditTextBackground()
                    setUpSignUpButtonWhileActive()

                }
            }
        })

        binding!!.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    setUpActiveEditTextsColors()
                    setUpActiveEditTextBackground()
                    setUpSignUpButtonWhileActive()

                } else {
                    setUpActiveEditTextsColors()
                    setUpActiveEditTextBackground()
                    setUpSignUpButtonWhileActive()

                }
            }
        })
    }

    private fun getTintedDrawable(drawableResId: Int, color: Int) =   // this function loads drawables from resources and applies tint color to it.
        ContextCompat.getDrawable(requireContext(), drawableResId)?.apply {  // ContextCompat.getDrawable() is helper method to safely retrieve drawable resource
            DrawableCompat.setTint(DrawableCompat.wrap(this), color)   // requireContext() returns current context ( activity or framgment) , drawableResId Is drawable which we want to return
        } //  we use apply to make sure that drawable exists and it is not null, Drawable.wrap is used to wrap Drawable IN DrawableCompat. works older versions too

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setUpActiveEditTextsColors() {
        val email = binding!!.etEmail.text.toString()
        val password = binding!!.etPassword.text.toString()
        if(email.isNotEmpty()){
            binding!!.etEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding!!.etEmail.setCompoundDrawablesWithIntrinsicBounds(
                getTintedDrawable(
                    R.drawable.ic_email_black,
                    ContextCompat.getColor(requireContext(), R.color.black)
                ),
                null, null, null
            )
        }else{
            binding!!.etEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            binding!!.etEmail.setCompoundDrawablesWithIntrinsicBounds(
                getTintedDrawable(
                    R.drawable.icon_email,
                    ContextCompat.getColor(requireContext(), R.color.grey)
                ),
                null, null, null
            )
        }
        if (password.isNotEmpty()) {

            binding!!.etPassword.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
                      // CHANGING DRAWABLES IN EDIT TEXT.

         /*   binding!!.etPassword.setCompoundDrawablesWithIntrinsicBounds(
                getTintedDrawable(
                    R.drawable.ic_email_black,
                    ContextCompat.getColor(requireContext(), R.color.black)
                ),
                null, null, null
            )*/
            binding!!.textInputPassword.setStartIconDrawable(R.drawable.ic_lock_black)  // icon is not changing colors

            binding!!.textInputPassword.startIconDrawable = getTintedDrawable(
                R.drawable.icon_password,
                ContextCompat.getColor(requireContext(), R.color.black)
            )



        } else {
            binding!!.etPassword.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
            // CHANGING DRAWABLES IN EDIT TEXT.

           /* binding!!.etPassword.setCompoundDrawablesWithIntrinsicBounds(
                getTintedDrawable(
                    R.drawable.icon_email,
                    ContextCompat.getColor(requireContext(), R.color.grey)
                ),
                null, null, null
            )*/

            binding!!.textInputPassword.startIconDrawable = getTintedDrawable(
                R.drawable.icon_password,
                ContextCompat.getColor(requireContext(), R.color.grey)
            )

        }
    }

    private fun setUpActiveEditTextBackground(){
        val email = binding!!.etEmail.text.toString()
        val password = binding!!.etPassword.text.toString()
        if(email.isNotEmpty()){
            binding!!.etEmail.background = ContextCompat.getDrawable(requireContext(), R.drawable.input_value_background)
        }else{
            binding!!.etEmail.background = null
        }
        if (password.isNotEmpty()) {
            binding!!.textInputPassword.background = ContextCompat.getDrawable(requireContext(), R.drawable.input_value_background)

        }else{
            binding!!.textInputPassword.background = null
        }
    }

    private fun setUpSignUpButtonWhileActive(){
        val email = binding!!.etEmail.text.toString()
        val password = binding!!.etPassword.text.toString()
        if(email.isNotEmpty()){
            binding!!.btnSignUp.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_sign_up_black_background)
        }else{
            binding!!.btnSignUp.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_sign_up_background)
        }
        if (password.isNotEmpty()) {
            binding!!.btnSignUp.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_sign_up_black_background)
        }else{
            binding!!.btnSignUp.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_sign_up_background)
        }
    }


}