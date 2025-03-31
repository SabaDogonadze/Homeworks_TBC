package com.example.tbchomework29.presenter.screen.bottom_sheet.to_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbchomework29.databinding.FragmentToAccountBottomSheetBinding
import com.example.tbchomework29.presenter.event.to_account_bottom_sheet.ToAccountBottomSheetEvent
import com.example.tbchomework29.presenter.event.to_account_bottom_sheet.ToAccountBottomSheetUiEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToAccountBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentToAccountBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val toAccountViewmodel: ToAccountBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentToAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        collectUiEvents()
    }

    private fun setUp() {
        stateObserver()
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnPersonalIdButton.setOnClickListener {
            toAccountViewmodel.event(ToAccountBottomSheetEvent.PersonalIdButtonClicked(binding.etPersonalId.text.toString()))
        }
        binding.btnPhoneNumberButton.setOnClickListener {
            toAccountViewmodel.event(ToAccountBottomSheetEvent.PhoneNumberButtonClicked(binding.etPhoneNumber.text.toString()))
        }
        binding.btnPersonalNumberButton.setOnClickListener {
            toAccountViewmodel.event(ToAccountBottomSheetEvent.CardNumberButtonClicked(binding.etPersonalNumber.text.toString()))
        }
    }

    private fun stateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                toAccountViewmodel.state.collect { state ->

                }
            }
        }
    }

    private fun collectUiEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                toAccountViewmodel.uiEvents.collect { event ->
                    when (event) {
                        is ToAccountBottomSheetUiEvent.OpenMoneyTransferFragment -> {
                            findNavController().navigate(
                                ToAccountBottomSheetFragmentDirections.actionToAccountBottomSheetFragmentToMoneyTransferFragment(
                                    id = null,
                                    cardNumber = event.cardNumber
                                )
                            )
                        }

                        is ToAccountBottomSheetUiEvent.ShowError -> {
                            Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}