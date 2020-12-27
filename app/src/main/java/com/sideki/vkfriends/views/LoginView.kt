package com.sideki.vkfriends.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface LoginView : MvpView {

    fun startLoading()
    fun endLoading()
    fun showError(textResources: Int)
    fun openFriends()
}