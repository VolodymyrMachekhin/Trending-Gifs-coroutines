package com.vmac.giphy.ui

import com.vmac.giphy.R
import com.vmac.giphy.common.utils.ui.BaseFragment
import com.vmac.giphy.databinding.FragmentTrendingBinding
import com.vmac.giphy.ui.adapter.GiphyListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingGifsFragment : BaseFragment<FragmentTrendingBinding>(
    layoutIdRes = R.layout.fragment_trending
) {

    val trendingViewModel: TrendingViewModel by viewModel()

    val adapter: GiphyListAdapter by inject()

    val bindingProvider: GiphyListBindingProvider by inject()

    override fun bindingCreated(binding: FragmentTrendingBinding) {
        binding.viewModel = trendingViewModel
        binding.adapter = adapter
        binding.itemBinding = bindingProvider.get()
    }
}