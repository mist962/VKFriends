package com.sideki.vkfriends.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sideki.vkfriends.R
import com.sideki.vkfriends.presenters.LoginPresenter
import com.sideki.vkfriends.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter


class LoginActivity : MvpAppCompatActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login_enter.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.FRIENDS))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!loginPresenter.loginVk(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    /** LoginView implementation*/
    override fun startLoading() {
        btn_login_enter.visibility = View.GONE
        progress_circular.visibility = View.VISIBLE
    }

    override fun endLoading() {
        btn_login_enter.visibility = View.VISIBLE
        progress_circular.visibility = View.GONE
    }

    override fun showError(textResources: Int) {
        Toast.makeText(this, textResources, Toast.LENGTH_LONG).show()
    }

    override fun openFriends() {
        startActivity(Intent(this, FriendsActivity::class.java))
    }
}