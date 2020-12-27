package com.sideki.vkfriends.presenters

import com.sideki.vkfriends.R
import com.sideki.vkfriends.views.LoginView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.logging.Handler

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun login(isSuccess: Boolean) {
        viewState.startLoading()
        android.os.Handler().postDelayed({
            viewState.endLoading()
            if (isSuccess) {
                viewState.openFriends()
            } else {
                viewState.showError(R.string.login_error_credentials)
            }
        }, 500)
    }

}