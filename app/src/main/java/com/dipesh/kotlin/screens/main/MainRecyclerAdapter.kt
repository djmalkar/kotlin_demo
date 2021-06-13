package com.dipesh.kotlin.screens.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dipesh.kotlin.dependencyinjection.ViewMvcFactory
import com.dipesh.kotlin.model.tables.MemberTable
import com.dipesh.kotlin.screens.main.MainRecyclerAdapter.MemberViewHolder
import java.util.*

class MainRecyclerAdapter(private val mListener: Listener, private val mViewMvcFactory: ViewMvcFactory)
    : RecyclerView.Adapter<MemberViewHolder>(), MemberItemViewMvc.Listener {
    interface Listener {
        fun onAcceptClicked(memberId: Int)
        fun onRejectClicked(memberId: Int)
    }

    class MemberViewHolder(val mViewMvc: MemberItemViewMvc) : RecyclerView.ViewHolder(mViewMvc.rootView!!)

    private var mMembers: List<MemberTable> = ArrayList()
    fun bindData(memberTables: List<MemberTable>) {
        mMembers = ArrayList(memberTables)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val viewMvc = mViewMvcFactory.getMemberItemViewMvc(parent)
        viewMvc.registerListener(this)
        return MemberViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.mViewMvc.bindData(mMembers[position])
    }

    override fun getItemCount(): Int {
        return mMembers.size
    }

    override fun onAcceptClicked(memberId: Int) {
        mListener.onAcceptClicked(memberId)
    }

    override fun onRejectClicked(memberId: Int) {
        mListener.onRejectClicked(memberId)
    }

}