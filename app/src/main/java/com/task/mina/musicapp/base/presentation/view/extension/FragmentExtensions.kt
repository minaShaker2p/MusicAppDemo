package com.task.mina.musicapp.base.presentation.view.extension

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

/*
 *  replace container layout with fragment and attached it to the activity
 *
 *@Param activity - the activity that @Param fragment will be attached to it
 *@Param frameId - the layout container that will be replaced with @Param fragment
 */
fun FragmentManager.replaceFragment(activity: AppCompatActivity, fragment: Fragment,
                                    frameId: Int, addToBackStack: Boolean = false, tag: String = "") {
    activity.supportFragmentManager?.inTransaction {
        replace(frameId, fragment)
        takeIf { addToBackStack }?.apply { addToBackStack(tag) }
    }
}