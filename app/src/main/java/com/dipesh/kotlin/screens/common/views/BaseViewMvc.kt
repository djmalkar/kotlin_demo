package com.dipesh.kotlin.screens.common.views

import android.content.Context
import android.view.View
import androidx.annotation.StringRes

abstract class BaseViewMvc : ViewMvc {
    override var rootView: View? = null
        protected set

    protected fun <T : View> findViewById(id: Int): T {
        return rootView!!.findViewById(id)
    }

    protected val context: Context
        get() = rootView!!.context

    protected fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }
}