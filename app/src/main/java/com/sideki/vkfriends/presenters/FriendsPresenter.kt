package com.sideki.vkfriends.presenters

import com.sideki.vkfriends.R
import com.sideki.vkfriends.models.FriendModel
import com.sideki.vkfriends.providers.FriendsProvider
import com.sideki.vkfriends.views.FriendsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>() {

    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(this).testLoadFriends(true)
    }

    fun friendsLoaded(friendsList: List<FriendModel>) {
        viewState.endLoading()
        if (friendsList.isEmpty()) {
            viewState.setupEmptyList()
            viewState.showError(R.string.friends_no_items)
        }else{
            viewState.setupFriendsList(friendsList)
        }
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource = textResource)
    }

}