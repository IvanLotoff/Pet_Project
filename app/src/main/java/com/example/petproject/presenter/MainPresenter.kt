package com.example.petproject.presenter

import com.example.petproject.data.UserRepository
import com.example.petproject.ui.UserAdapter
import com.example.petproject.view.MainMvpView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class MainPresenter(private val userRepository: UserRepository, private val adapter: UserAdapter) :
    MvpPresenter<MainMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        displayUsers()
    }

    fun displayUsers() {
        presenterScope.launch(Dispatchers.IO) {
            userRepository.getResultStream()
                .flowOn(Dispatchers.Main)
                .collect {
                    viewState.showUserList(it)
                }
        }
    }

    fun refresh() {
        presenterScope.launch(Dispatchers.Main) {
            viewState.hideContent()
            viewState.showLoadingIndicator()
            adapter.refresh()
            viewState.hideLoadingIndicator()
            viewState.showContent()
        }
    }
}