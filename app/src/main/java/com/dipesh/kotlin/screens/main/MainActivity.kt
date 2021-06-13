package com.dipesh.kotlin.screens.main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import com.dipesh.kotlin.dependencyinjection.ViewMvcFactory
import com.dipesh.kotlin.model.tables.MemberTable
import com.dipesh.kotlin.screens.base.BaseActivity
import com.dipesh.kotlin.screens.main.usecase.FetchMembersAndModifyUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), MainViewMvc.Listener, FetchMembersAndModifyUseCase.Listener {
    private lateinit var mViewMvc: MainViewMvc

    @Inject
    lateinit var mFetchMembersAndModifyUseCase: FetchMembersAndModifyUseCase
    @Inject
    lateinit var viewFactory: ViewMvcFactory
    private var mIsFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvc = viewFactory.getMainViewMvc(null)
        mViewMvc.setTitle("Shaadi Test")
        setContentView(mViewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mFetchMembersAndModifyUseCase.registerListener(this)
        if (mIsFirstTime) {
            mIsFirstTime = false
            startDataFetching()
        }
    }

    private fun startDataFetching() {
        mViewMvc.showProgress()
        mFetchMembersAndModifyUseCase.fetchCategoriesAndNotify()
    }

    override val isNetworkConnected: Boolean
        get() {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
        }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
        mFetchMembersAndModifyUseCase.removeListener(this)
        mFetchMembersAndModifyUseCase.dispose()
    }

    override fun onAcceptClicked(memberId: Int) {
        mFetchMembersAndModifyUseCase.updateDbWithMemberStatus(memberId, "You have Accepted this profile")
    }

    override fun onRejectClicked(memberId: Int) {
        mFetchMembersAndModifyUseCase.updateDbWithMemberStatus(memberId, "You have Rejected this profile")
    }

    override fun onMembersFetched(memberTables: List<MemberTable>) {
        mViewMvc.hideProgress()
        mViewMvc.populateAdapter(memberTables)
    }

    override fun onFetchingFailed(message: String) {
        mViewMvc.hideProgress()
        mViewMvc.displayMessage(message)
    }

    override fun memberStatusUpdated() {
        //mViewMvc.displayMessage("Member Status Updated Successfully")
    }
}