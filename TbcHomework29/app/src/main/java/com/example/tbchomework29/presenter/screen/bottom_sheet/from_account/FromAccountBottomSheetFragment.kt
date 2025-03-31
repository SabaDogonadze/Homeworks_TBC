package com.example.tbchomework29.presenter.screen.bottom_sheet.from_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework29.databinding.FragmentFromAccountBottomSheetBinding
import com.example.tbchomework29.presenter.event.from_account_bottom_sheet.FromAccountBottomSheetEvent
import com.example.tbchomework29.presenter.screen.adapter.FromAccountRecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FromAccountBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFromAccountBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val fromAccountViewmodel: FromAccountBottomSheetViewModel by viewModels()
    private lateinit var fromAccountAdapter: FromAccountRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFromAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        fromAccountViewmodel.event(FromAccountBottomSheetEvent.LoadUserCards)
        stateObserver()
        setUpRecycler()
        clickListeners()
    }

    private fun clickListeners(){
        fromAccountAdapter.setonItemClickedListener { card->
           // fromAccountViewmodel.event(FromAccountBottomSheetEvent.OpenMoneyTransferFragment(card.id.toString()))
            val action = FromAccountBottomSheetFragmentDirections.actionFromAccountBottomSheetFragmentToMoneyTransferFragment(card.id.toString())
            findNavController().navigate(action)
        }

    }

    private fun setUpRecycler() {
        fromAccountAdapter = FromAccountRecyclerAdapter()
        binding.fromAccountRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = fromAccountAdapter
        }
    }

    private fun stateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fromAccountViewmodel.state.collect { state ->
                    fromAccountAdapter.submitList(state.userCards)
                }
            }
        }
    }

 /*   private fun eventObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fromAccountViewmodel.uiEvents.collect { event ->
                    when (event) {
                        is FromAccountBottomSheetSideEvent.ShowError -> TODO()
                        FromAccountBottomSheetSideEvent.OpenMoneyTransferFragment -> clickListeners()
                    }
                }
            }
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   /* private fun openMoneyTransferFragment(){
        findNavController().navigate(FromAccountBottomSheetFragmentDirections.actionFromAccountBottomSheetFragmentToMoneyTransferFragment())
    }*/
}