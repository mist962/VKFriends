package com.sideki.vkfriends.models

import android.graphics.drawable.Drawable

data class FriendModel(
    var name: String,
    var surname: String,
    var city: String?,
    var avatar: String?,
    var isOnline: Boolean
) {

}