package com.dipesh.kotlin.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dipesh.kotlin.R
import com.dipesh.kotlin.model.tables.MemberTable
import com.dipesh.kotlin.screens.common.views.BaseObservableViewMvc

class MemberItemViewMvc(inflater: LayoutInflater, parent: ViewGroup?)
    : BaseObservableViewMvc<MemberItemViewMvc.Listener>() {

    interface Listener {
        fun onAcceptClicked(memberId: Int)
        fun onRejectClicked(memberId: Int)
    }

    private val mProfileImage: ImageView
    private val mName: TextView
    private val mAgeHeight: TextView
    private val mAcceptRejectStatus: TextView
    private val mAccept: Button
    private val mReject: Button
    private var mMember: MemberTable? = null

    init {
        rootView = inflater.inflate(R.layout.item_member_view, parent, false)
        mProfileImage = findViewById(R.id.profile)
        mName = findViewById(R.id.name)
        mAgeHeight = findViewById(R.id.age_height)
        mAcceptRejectStatus = findViewById(R.id.accept_reject_status)
        mAccept = findViewById(R.id.accept)
        mReject = findViewById(R.id.reject)
        mAccept.setOnClickListener { view: View? ->
            if (listeners != null) {
                listeners?.onAcceptClicked(mMember!!.id)
                setAcceptRejectStatus("You have Accepted this profile")
                setGoneVisibilityButtons()
            }
        }
        mReject.setOnClickListener{ view: View? ->
            if (listeners != null) {
                listeners?.onRejectClicked(mMember!!.id)
                setAcceptRejectStatus("You have Rejected this profile")
                setGoneVisibilityButtons()
            }
        }
    }

    private fun setAcceptRejectStatus(message: String) {
        mMember!!.memberInterested = message
        mAcceptRejectStatus.visibility = View.VISIBLE
        mAcceptRejectStatus.text = message
    }

    private fun setGoneVisibilityButtons() {
        mAccept.visibility = View.GONE
        mReject.visibility = View.GONE
    }

    private fun setVisibleVisibilityButtons() {
        mAccept.visibility = View.VISIBLE
        mReject.visibility = View.VISIBLE
    }

    fun bindData(memberTable: MemberTable) {
        mMember = memberTable
        Glide.with(context).load(mMember?.picture).placeholder(R.drawable.placeholder).into(mProfileImage)
        mName.text = mMember?.name
        mAgeHeight.text = mMember?.age.toString() + "," + mMember?.address
        if (mMember?.memberInterested.isNullOrEmpty()) {
            setVisibleVisibilityButtons()
            mAcceptRejectStatus.visibility = View.GONE
        } else {
            setAcceptRejectStatus(mMember!!.memberInterested)
            setGoneVisibilityButtons()
        }
    }

}