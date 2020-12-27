package com.sideki.vkfriends.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sideki.vkfriends.R
import com.sideki.vkfriends.models.FriendModel
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendsAdapter(private val context: Context) : RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    private var mFriendsList = mutableListOf<FriendModel>()
    private var mSourceList = mutableListOf<FriendModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false))

    override fun getItemCount(): Int = mFriendsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = mFriendsList[position]

        Glide.with(context).load(curItem.avatar).centerCrop()
            .into(holder.itemView.friend_civ_avatar)

        val name = curItem.name
        val surname = curItem.surname
        val username = "$name $surname"
        holder.itemView.friend_txt_username.text = username

        when (curItem.city) {
            null -> holder.itemView.friend_txt_city.text = "Город не указан"
            else -> holder.itemView.friend_txt_city.text = curItem.city
        }

        when (curItem.isOnline) {
            true -> holder.itemView.friend_img_online.visibility = View.VISIBLE
            false -> holder.itemView.friend_img_online.visibility = View.GONE
        }
    }

    fun setupFriendsList(friendList: List<FriendModel>) {
        this.mFriendsList.clear()
        this.mSourceList.addAll(friendList)
        filter("")
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.name.contains(query, ignoreCase = true) || it.surname.contains(query, ignoreCase = true)) {
                mFriendsList.add(it)
            } else {
                it.city?.let { city ->
                    if (city.contains(query, ignoreCase = true)) {
                        mFriendsList.add(it)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }
}

