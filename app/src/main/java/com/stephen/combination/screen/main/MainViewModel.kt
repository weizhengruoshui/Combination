package com.stephen.combination.screen.main

import com.stephen.combination.common.manager.fragment.FragmentNavigator
import com.stephen.combination.common.viewmodel.BaseViewModel

class MainViewModel : BaseViewModel<String>() {

    override fun loadData() {
        data.value = FragmentNavigator.FragmentTag.NEWS
    }
}