package com.task.mina.musicapp.ui.mainscreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.task.mina.musicapp.R
import com.task.mina.musicapp.ui.searchscreen.SearchArtistActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFabSearchIcon()
    }

    private fun initFabSearchIcon() {
        fabSearch.setOnClickListener {
            navigateToSearchScreen()
        }
    }


    private fun navigateToSearchScreen() {
        startActivity(Intent(this, SearchArtistActivity::class.java))
    }
}
