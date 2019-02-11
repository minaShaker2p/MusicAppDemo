package com.task.mina.musicapp.ui.searchscreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.task.mina.musicapp.R
import kotlinx.android.synthetic.main.activity_search_artist.*

class SearchArtistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_artist)
        intiSearchButton()
    }

    private fun intiSearchButton() {
        btnSearch.setOnClickListener {
            Toast.makeText(it.context, edtArtistName.text.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
