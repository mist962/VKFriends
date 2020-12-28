package com.sideki.vkfriends.presenters

import com.sideki.vkfriends.R
import com.sideki.vkfriends.models.VKUser
import com.sideki.vkfriends.providers.FriendsProvider
import com.sideki.vkfriends.views.FriendsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>() {

    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(this).loadFriends()
    }

    fun friendsLoaded(friendsList: List<VKUser>) {
        viewState.endLoading()
        if (friendsList.isEmpty()) {
            viewState.setupEmptyList()
            viewState.showError(R.string.friends_no_items)
        }else{
            viewState.setupFriendsList(friendsList)
        }
    }

    fun showError(){
        viewState.showError(R.string.friends_error_loading)
    }


}