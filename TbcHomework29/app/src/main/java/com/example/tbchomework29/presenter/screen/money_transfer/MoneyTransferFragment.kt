package com.example.tbchomework29.presenter.screen.money_transfer

import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tbchomework29.R
import com.example.tbchomework29.databinding.FragmentMoneyTransferBinding
import com.example.tbchomework29.presenter.base.BaseFragment
import com.example.tbchomework29.presenter.event.money_transfer.MoneyTransferEvent
import com.example.tbchomework29.presenter.event.money_transfer.MoneyTransferSideEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoneyTransferFragment : BaseFragment<FragmentMoneyTransferBinding>(FragmentMoneyTransferBinding::inflate){
    private val moneyTransferViewModel: MoneyTransferViewModel by viewModels()
    private val args: MoneyTransferFragmentArgs by navArgs()
    override fun setUp() {
        stateObserver()
        moneyTransferViewModel.event(MoneyTransferEvent.LoadUserCards(args.id.toString()))
       // moneyTransferViewModel.event(MoneyTransferEvent.LoadUserCardsByCardNumber(args.cardNumber.toString()))
        clickListeners()
        eventObserver()
    }

    private fun clickListeners(){
        binding.fromPayment.setOnClickListener{
            findNavController().navigate(MoneyTransferFragmentDirections.actionMoneyTransferFragmentToFromAccountBottomSheetFragment())
        }
        binding.toPayment.setOnClickListener{
            findNavController().navigate(MoneyTransferFragmentDirections.actionMoneyTransferFragmentToToAccountBottomSheetFragment())
        }
    }
   private fun stateObserver() {
       viewLifecycleOwner.lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED) {
               moneyTransferViewModel.state.collect { state ->
                   binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                   val card = state.userCards.find { it.id.toString() == args.id }
                   if (card != null) {
                       binding.tvPaymentValute.text = card.valuteType
                       binding.tvPaymentType.text = card.accountName
                       binding.tvCardLastFourNumber.text = card.accountNumber.takeLast(4)
                   } else {
                       binding.tvPaymentValute.text = ""
                       binding.tvPaymentType.text = ""
                       binding.tvCardLastFourNumber.text = ""
                   }
                   Glide.with(requireActivity())
                       .load(card?.cardLogo)
                       .placeholder(R.drawable.ic_launcher_foreground)
                       .error(R.drawable.ic_launcher_foreground)
                       .into(binding.ivCard)


               /*    val transferCard = state.userCardsFilteredByCardNumber.find { it.id.toString() == args.cardNumber }
                   if (transferCard != null) {
                       binding.toTvPaymentValute.text = transferCard.valuteType
                       binding.tvPaymentType.text = transferCard.accountName
                       binding.toTvCardLastFourNumber.text = transferCard.accountNumber.takeLast(4)
                   } else {
                       binding.toTvPaymentValute.text = ""
                       binding.tvPaymentType.text = ""
                       binding.toTvCardLastFourNumber.text = ""
                   }
                   Glide.with(requireActivity())
                       .load(transferCard?.cardLogo)
                       .placeholder(R.drawable.ic_launcher_foreground)
                       .error(R.drawable.ic_launcher_foreground)
                       .into(binding.toIvCard)
*/

               }
           }
       }
   }


    private fun eventObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                moneyTransferViewModel.uiEvents.collect { event ->
                    when (event) {
                        // HomeSideEffect.NavigateToDetail -> navigateToMovieDetailFragment()
                       // is HomeSideEffect.ShowError -> getString(R.string.unknown_error)
                        is MoneyTransferSideEvent.ShowError -> TODO()
                        MoneyTransferSideEvent.OpenAccountBottomSheet -> TODO()
                    }
                }
            }
        }
    }


}