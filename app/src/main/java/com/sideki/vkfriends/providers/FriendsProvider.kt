package com.sideki.vkfriends.providers

import android.os.Handler
import com.sideki.vkfriends.models.FriendModel
import com.sideki.vkfriends.presenters.FriendsPresenter

class FriendsProvider(var presenter: FriendsPresenter) {

    fun testLoadFriends(hasFriends: Boolean) {
        Handler().postDelayed({
            val friendsList = mutableListOf(
                FriendModel(
                    "Ivan",
                    "Petrov",
                    null,
                    "https://sun9-64.userapi.com/CJOek4O-XjYjHj0tUAUbcmLXvz4w2Vc2vmvKcQ/BRlodbal6Io.jpg",
                    true
                ),
                FriendModel(
                    "Alex",
                    "Ignatyev",
                    "Simferopol",
                    "https://ic.pics.livejournal.com/flor_iz/84079206/396338/396338_original.jpg",
                    false
                ),
                FriendModel(
                    "Sergei",
                    "Stasuk",
                    "Moscow",
                    "https://g.forsal.pl/p/_wspolne/pliki/4293000/4293634-wladimir-putin.jpg",
                    true
                )
            )
                presenter.friendsLoaded(friendsList)
        }, 2000)
    }
}