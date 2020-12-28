package com.sideki.vkfriends.views

import com.sideki.vkfriends.models.VKUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView : MvpView {
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupFriendsList(friendsList: List<VKUser>)
    fun startLoading()
    fun endLoading()
}