package com.sideki.vkfriends.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sideki.vkfriends.R
import com.sideki.vkfriends.models.VKUser
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendsAdapter(private val context: Context) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    private val TAG:String = "TAG"

    private var mFriendsList = mutableListOf<VKUser>()
    private var mSourceList = mutableListOf<VKUser>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false))

    override fun getItemCount(): Int = mFriendsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = mFriendsList[position]

        Glide.with(context).load(curItem.photo).centerCrop()
            .into(holder.itemView.friend_civ_avatar)

        val name = curItem.firstName
        val surname = curItem.lastName
        val username = "$name $surname"
        holder.itemView.friend_txt_username.text = username

        //holder.itemView.friend_txt_city.text = curItem.city

        if (curItem.isOnline == 1) {
            holder.itemView.friend_img_online.visibility = View.VISIBLE
        }else{
            holder.itemView.friend_img_online.visibility = View.GONE
        }

        Log.d(TAG, " Онлайн - ${curItem.isOnline}")
    }

    fun setupFriendsList(friendList: List<VKUser>) {
        this.mFriendsList.clear()
        this.mSourceList.addAll(friendList)
        filter("")
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.firstName.contains(query, ignoreCase = true) || it.lastName.contains(
                    query,
                    ignoreCase = true
                )
            ) {
                mFriendsList.add(it)
            } else {
/*                it.city?.let { city ->
                    if (city.contains(query, ignoreCase = true)) {
                        mFriendsList.add(it)
                    }
                }*/
            }
        }
        notifyDataSetChanged()
    }
}

