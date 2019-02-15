package com.task.mina.musicapp.ui.albumdetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.view.extension.loadFromUrl
import com.task.mina.musicapp.data.remote.network.response.Album
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
            val album: Album? = it.getParcelable(EXTRA_ALBUM_OBJECT) as? Album
            album?.let {
                bindAlbumDetails(it)
            }
        }
    }

    private fun bindAlbumDetails(album: Album) {
        tvAlbumName.text = album.name
        tvAlbumArtistName.text = album.artist.name
        val image = album.image.filter {
            it.size == "extralarge"
        }.map {
            it.text
        }.first()
        imgAlbumCover.loadFromUrl(image)
    }

}
