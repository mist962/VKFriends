package com.sideki.vkfriends.providers

import com.sideki.vkfriends.models.VKFriendsRequest
import com.sideki.vkfriends.models.VKUser
import com.sideki.vkfriends.presenters.FriendsPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback

class FriendsProvider(var presenter: FriendsPresenter) {

    fun loadFriends(){
        VK.execute(VKFriendsRequest(), object :VKApiCallback<List<VKUser>>{
            override fun fail(error: Exception) {
                presenter.showError()
            }

            override fun success(result: List<VKUser>) {
                presenter.friendsLoaded(result)
            }

        })
    }
}