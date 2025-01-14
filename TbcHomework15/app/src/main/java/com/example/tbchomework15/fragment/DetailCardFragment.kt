package com.example.tbchomework15.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbchomework15.R
import com.example.tbchomework15.databinding.FragmentDetailCardBinding
import com.example.tbchomework15.model.CardType

class DetailCardFragment :
    BaseFragment<FragmentDetailCardBinding>(FragmentDetailCardBinding::inflate) {
    private val viewModel: DetailCardViewModel by viewModels()
    override fun setUp() {
            // i should not have used base fragment in this case, i am overriding setUp function but not implementing it
    }
    override fun clickListeners() {
        binding.btnAddCard.setOnClickListener {
            if(getUserData()){
            navigateToMainFragment()
            }else{
                showToast(getString(R.string.inputs_are_wrong))
            }
        }
        binding.btnBack.setOnClickListener {
            navigateToMainFragment()
        }
    }

    private fun navigateToMainFragment(){
        findNavController().navigate(DetailCardFragmentDirections.actionDetailCardFragmentToItemFragment())
    }

    private fun getUserData():Boolean {
        var isValid = false
        val userName = binding.etUserName.text.toString()
        val cardNumber = binding.etUserCardNumber.text.toString()
        val expirationDate = binding.etCardExpires.text.toString()
        val cardCvv = binding.etCardCvv.text.toString()

        val cardType = getDetectCardType()
        val cardBackground = if (cardType == CardType.VISA) R.color.blue else R.color.gold
        val cardLogo = if (cardType == CardType.VISA) R.drawable.ic_visa else R.drawable.ic_mastercard


        if (areInputsValid(userName, cardNumber, expirationDate, cardCvv)) {
            isValid = true
            viewModel.saveUserData(
                userName,
                cardNumber,
                expirationDate,
                cardCvv,
                cardType,
                cardBackground,
                cardLogo
            )
        }
        return  isValid
    }

    private fun areInputsValid(
        userName: String,
        cardNumber: String,
        expirationDate: String,
        cardCvv: String
    ): Boolean {
        var isValid = true


        if (userName.isEmpty()) {
            showToast(getString(R.string.user_name_cannot_be_empty))
            isValid = false
        }

        if (!viewModel.isCardNumberValid(cardNumber)) {
            showToast(getString(R.string.card_number_must_be_16_digits))
            isValid = false
        }

        if (!viewModel.isCardExpirationValid(expirationDate)) {
            showToast(getString(R.string.expiration_date_must_be_4_digits))
            isValid = false
        }

        if (!viewModel.isCardCvvValid(cardCvv)) {
            showToast(getString(R.string.cvv_must_be_3_digits))
            isValid = false
        }

        return isValid
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun getDetectCardType(): CardType {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        return when (selectedId) {
            R.id.radio_button_visa -> CardType.VISA
            R.id.radio_button_mastercard -> CardType.MASTERCARD
            else -> CardType.MASTERCARD
        }
    }

}