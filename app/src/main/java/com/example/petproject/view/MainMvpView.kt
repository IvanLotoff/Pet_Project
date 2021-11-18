package com.example.petproject.view

import androidx.paging.PagingData
import com.example.data.User
import kotlinx.coroutines.flow.Flow
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainMvpView : MvpView {
    fun showUserList(userList: PagingData<User>)
    fun hideContent()
    fun showContent()
    fun showLoadingIndicator()
    fun hideLoadingIndicator()
}
