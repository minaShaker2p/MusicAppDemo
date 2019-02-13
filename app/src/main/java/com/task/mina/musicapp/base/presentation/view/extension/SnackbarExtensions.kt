package com.task.mina.musicapp.base.presentation.view.extension

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import android.view.ViewGroup

inline fun View.showSnack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG,
                          doAction: Snackbar.() -> Unit) {
    showSnack(resources.getString(messageRes), length, doAction)
}

inline fun View.showSnack(message: String, length: Int = Snackbar.LENGTH_LONG,
                          doAction: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.doAction()
    snack.view.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}


fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}