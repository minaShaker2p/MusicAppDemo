package com.task.mina.musicapp.ui.albumdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.view.extension.loadFromUrl
import com.task.mina.musicapp.ui.topablums.domain.entity.AlbumUI
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)
        getActvityBundle()
    }

    companion object {
        val EXTRA_ALBUM_OBJECT = "album"
    }

    private fun getActvityBundle() {
        val extras = intent.extras
        extras?.let {
            val album: AlbumUI? = it.getParcelable(EXTRA_ALBUM_OBJECT) as? AlbumUI
            album?.let {
                bindAlbumDetails(it)
            }
        }
    }

    private fun bindAlbumDetails(album: AlbumUI) {
        tvAlbumName.text = album.albumName
        tvAlbumArtistName.text = album.artistName
        tvAlbumTrack.text = album.albumUrl
        imgAlbumCover.loadFromUrl(album.albumImage)
    }

}
