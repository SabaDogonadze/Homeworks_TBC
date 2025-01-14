package com.example.tbchomework15.fragment

import androidx.navigation.fragment.findNavController
import com.example.tbchomework15.data.CardData
import com.example.tbchomework15.databinding.FragmentMainBinding


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var viewPager2Adapter: ViewPagerAdapter

    override fun setUp() {
        setUpViewPager2()
    }

    override fun clickListeners() {
        binding.tvAddNew.setOnClickListener {
            navigateDetailsFragment()
        }
        viewPager2Adapter.setonItemClickedListener {
            navigateBottomSheetFragment(it.id)
        }
    }
    private fun navigateDetailsFragment(){
        findNavController().navigate(MainFragmentDirections.actionItemFragmentToDetailCardFragment())
    }
    private fun navigateBottomSheetFragment(id:String){
        findNavController().navigate(MainFragmentDirections.actionItemFragmentToBottomSheetFragment(id))
    }

    private fun setUpViewPager2() {
        viewPager2Adapter = ViewPagerAdapter()
        binding.viewPager.adapter = viewPager2Adapter
        viewPager2Adapter.submitList(CardData.cardData)

    }
}