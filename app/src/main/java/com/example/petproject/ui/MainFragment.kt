package com.example.petproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.User
import com.example.petproject.data.UserRepository
import com.example.petproject.databinding.FragmentMainBinding
import com.example.petproject.presenter.MainPresenter
import com.example.petproject.view.MainMvpView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect;
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import retrofit2.HttpException
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : MvpAppCompatFragment(), MainMvpView {
    private lateinit var binding: FragmentMainBinding
    private val adapter = UserAdapter()

    @Inject
    lateinit var userRepository: UserRepository

    private val presenter by moxyPresenter {
        MainPresenter(userRepository, adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        binding.swipeToRefresh.setOnRefreshListener {
            presenter.refresh()
        }
        return binding.root
    }

    private fun setUpRecyclerView() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter.withLoadStateFooter(
                footer = LoadingAdapter {
                    adapter.retry()
                }
            )
            lifecycleScope.launch {
                adapter.loadStateFlow.collect { loadState ->
                    val refreshState = loadState.refresh

                    if (refreshState is LoadState.Error) {
                        when (refreshState.error as Exception) {
                            is HttpException -> Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun showUserList(userList: PagingData<User>) {
        lifecycleScope.launchWhenStarted {
            adapter.submitData(userList)
        }
    }

    override fun hideContent() {
        binding.recyclerView.visibility = View.GONE
    }

    override fun showContent() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    override fun showLoadingIndicator() {
        binding.swipeToRefresh.isRefreshing = true
    }

    override fun hideLoadingIndicator() {
        binding.swipeToRefresh.isRefreshing = false
    }
}