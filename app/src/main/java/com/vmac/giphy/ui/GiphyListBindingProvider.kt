package com.vmac.giphy.ui

import com.vmac.giphy.BR
import com.vmac.giphy.R
import com.vmac.giphy.ui.adapter.GiphyListItem
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class GiphyListBindingProvider {

    fun get(): OnItemBindClass<GiphyListItem> {
        return OnItemBindClass<GiphyListItem>()
            .map(GiphyListItem.DisplayGiphy::class.java) { itemBinding, _, _ ->
                itemBinding.set(BR.item, R.layout.item_gif)
            }
            .map(GiphyListItem.LoadMore::class.java) { itemBinding, _, _ ->
                itemBinding.set(ItemBinding.VAR_NONE, R.layout.item_progress)
            }
    }
}