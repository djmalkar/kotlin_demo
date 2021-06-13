package com.dipesh.kotlin.screens.common.toolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.dipesh.kotlin.R
import com.dipesh.kotlin.screens.common.views.BaseViewMvc

class ToolbarViewMvc(inflater: LayoutInflater, parent: ViewGroup?) : BaseViewMvc() {

    private val mTxtTitle: TextView

    init {
        rootView = inflater.inflate(R.layout.layout_toolbar, parent, false)
        mTxtTitle = findViewById(R.id.txt_toolbar_title)
    }

    fun setTitle(title: String) {
        mTxtTitle.text = title
    }

}