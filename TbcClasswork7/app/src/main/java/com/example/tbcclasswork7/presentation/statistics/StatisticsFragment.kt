package com.example.tbcclasswork7.presentation.statistics

import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbcclasswork7.data.common.Resource
import com.example.tbcclasswork7.databinding.FragmentStatisticsBinding
import com.example.tbcclasswork7.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StatisticsFragment :
    BaseFragment<FragmentStatisticsBinding>(FragmentStatisticsBinding::inflate) {
    private lateinit var viewPager2Adapter: ViewPagerAdapter
    private val viewModel: StatisticsViewModel by viewModels()

    override fun setUp() {
        viewModel.getItemsData()
        observers()
        setUpViewPager2()
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemsDataResponseFlow.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            d("12345", "${it.data}")
                            viewPager2Adapter.submitList(it.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun setUpViewPager2() {
        viewPager2Adapter = ViewPagerAdapter()
        binding.viewPager.adapter = viewPager2Adapter
    }
}


