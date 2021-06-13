package com.dipesh.kotlin.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dipesh.kotlin.R
import com.dipesh.kotlin.dependencyinjection.ViewMvcFactory
import com.dipesh.kotlin.model.tables.MemberTable
import com.dipesh.kotlin.screens.common.toolbar.ToolbarViewMvc
import com.dipesh.kotlin.screens.common.views.BaseObservableViewMvc

class MainViewMvc(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory)
    : BaseObservableViewMvc<MainViewMvc.Listener>(), MainRecyclerAdapter.Listener {

    interface Listener {
        fun onAcceptClicked(memberId: Int)
        fun onRejectClicked(memberId: Int)
    }

    private val mToolbarViewMvc: ToolbarViewMvc
    private val mToolbar: Toolbar
    private val mMembersList: RecyclerView
    private val mProgressBar: FrameLayout
    private val mAdapter: MainRecyclerAdapter

    init {
        rootView = inflater.inflate(R.layout.activity_main, parent, false)
        mMembersList = findViewById(R.id.members_list)
        mProgressBar = findViewById(R.id.progress_bar)

        val layoutManager = LinearLayoutManager(context)
        mMembersList.layoutManager = layoutManager
        mAdapter = MainRecyclerAdapter(this, viewMvcFactory)
        mMembersList.adapter = mAdapter

        mToolbar = findViewById(R.id.toolbar)
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar)
        mToolbar.addView(mToolbarViewMvc.rootView)
    }

    fun populateAdapter(members: List<MemberTable>) {
        mAdapter.bindData(members)
    }

    override fun onAcceptClicked(memberId: Int) {
        listeners?.onAcceptClicked(memberId)
    }

    override fun onRejectClicked(memberId: Int) {
        listeners?.onRejectClicked(memberId)
    }

    fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        mProgressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        mProgressBar.visibility = View.GONE
    }

    fun setTitle(title: String) {
        mToolbarViewMvc.setTitle(title)
    }


}