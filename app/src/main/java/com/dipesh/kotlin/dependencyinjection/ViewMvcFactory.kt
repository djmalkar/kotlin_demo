package com.dipesh.kotlin.dependencyinjection

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dipesh.kotlin.screens.common.toolbar.ToolbarViewMvc
import com.dipesh.kotlin.screens.main.MainViewMvc
import com.dipesh.kotlin.screens.main.MemberItemViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val mLayoutInflater: LayoutInflater) {
    fun getMainViewMvc(parent: ViewGroup?): MainViewMvc {
        return MainViewMvc(mLayoutInflater, null, this)
    }

    fun getMemberItemViewMvc(parent: ViewGroup?): MemberItemViewMvc {
        return MemberItemViewMvc(mLayoutInflater, parent)
    }

    fun getToolbarViewMvc(parent: ViewGroup?): ToolbarViewMvc {
        return ToolbarViewMvc(mLayoutInflater, parent)
    }
}