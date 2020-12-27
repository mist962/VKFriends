package com.sideki.vkfriends.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.sideki.vkfriends.R
import com.sideki.vkfriends.adapters.FriendsAdapter
import com.sideki.vkfriends.models.FriendModel
import com.sideki.vkfriends.presenters.FriendsPresenter
import com.sideki.vkfriends.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    private val TAG: String =  "TAG"

    private lateinit var mAdapter: FriendsAdapter

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        friendsPresenter.loadFriends()
        mAdapter = FriendsAdapter(this)

        txt_friends_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mAdapter.filter(s.toString())
                Log.d(TAG, "onTextChanged")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        recycler_friends.adapter = mAdapter
        recycler_friends.setHasFixedSize(true)
    }

    /** FriendView implementation*/
    override fun showError(textResource: Int) {
        txt_friends_no_items.text = getString(textResource)
        Log.d(TAG, "showError")
    }

    override fun setupEmptyList() {
        recycler_friends.visibility = View.GONE
        txt_friends_no_items.visibility = View.VISIBLE
        Log.d(TAG, "setupEmptyList")
    }

    override fun setupFriendsList(friendsList: List<FriendModel>) {
        recycler_friends.visibility = View.VISIBLE
        txt_friends_no_items.visibility = View.GONE

        mAdapter.setupFriendsList(friendsList)

        Log.d(TAG, "setupFriendsList")
        Log.d(TAG, "${friendsList.size}")
    }

    override fun startLoading() {
        recycler_friends.visibility = View.GONE
        txt_friends_no_items.visibility = View.GONE
        cpv_friends.visibility = View.VISIBLE
        Log.d(TAG, "startLoading")
    }

    override fun endLoading() {
        recycler_friends.visibility = View.VISIBLE
        cpv_friends.visibility = View.GONE
        Log.d(TAG, "endLoading")
    }
}