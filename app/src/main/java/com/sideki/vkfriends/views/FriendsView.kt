package com.sideki.vkfriends.views

import com.sideki.vkfriends.models.FriendModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView : MvpView {
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupFriendsList(friendsList: List<FriendModel>)
    fun startLoading()
    fun endLoading()
}