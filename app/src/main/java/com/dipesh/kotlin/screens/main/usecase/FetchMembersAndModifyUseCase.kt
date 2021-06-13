package com.dipesh.kotlin.screens.main.usecase

import android.util.Log
import com.dipesh.kotlin.common.BaseObservable
import com.dipesh.kotlin.data.local.DbHelper
import com.dipesh.kotlin.data.remote.ApiRetrofit
import com.dipesh.kotlin.model.networkschemas.MembersListResult
import com.dipesh.kotlin.model.tables.MemberTable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class FetchMembersAndModifyUseCase
@Inject constructor(private val mDbHelper: DbHelper, private val mApiRetrofit: ApiRetrofit,
                    private val mParseMembersToModelUseCase: ParseMembersToModelUseCase) : BaseObservable<FetchMembersAndModifyUseCase.Listener>() {

    interface Listener {
        fun onMembersFetched(memberTables: List<MemberTable>)
        val isNetworkConnected: Boolean
        fun onFetchingFailed(errorMessage: String)
        fun memberStatusUpdated()
    }

    companion object {
        private const val TAG = "FetchCategoriesRanking"
    }

    private var job: Job? = null
    private var disposable: Disposable? = null

    fun fetchCategoriesAndNotify() {

        job = CoroutineScope(Dispatchers.Main).launch {
            val memberTableList = mDbHelper.getAllMembers()
            processMembersList(memberTableList);
        }
    }

    private fun processMembersList(memberTableList: List<MemberTable>) {
        if (memberTableList.isEmpty() && listener != null && listener!!.isNetworkConnected) {
            fetchFromRemoteServer()
        } else if (listener != null && !memberTableList.isEmpty()) {
            listener?.onMembersFetched(memberTableList)
        } else if (listener != null && !listener!!.isNetworkConnected) {
            listener?.onFetchingFailed("Please connect to valid internet connection")
        }
    }

    private fun fetchFromRemoteServer() {
        disposable = mApiRetrofit.getListOfMembersApi(10)
                .map { membersListResult: MembersListResult -> mParseMembersToModelUseCase.parseSchema(membersListResult) }
                .flatMap { memberTableList: List<MemberTable> -> mDbHelper.insertMembers(memberTableList) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Long>>() {
                    override fun onSuccess(memberTables: List<Long>) {
                        Log.d(TAG, "onSuccess: ")
                        fetchCategoriesAndNotify()
                    }

                    override fun onError(e: Throwable) {
                        listener?.onFetchingFailed("Error fetching members list")
                        Log.d(TAG, "onError: " + e.localizedMessage)
                    }
                })
    }

    fun updateDbWithMemberStatus(memberId: Int, status: String) {
        disposable = mDbHelper.updateMemberStatus(memberId, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Int?>() {
                    override fun onSuccess(id: Int) {
                        Log.d(TAG, "onSuccess: ")
                        listener?.memberStatusUpdated()
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: " + e.localizedMessage)
                    }
                })
    }

    fun dispose() {
        job?.apply { cancel() }
        disposable?.apply { dispose() }
    }

}