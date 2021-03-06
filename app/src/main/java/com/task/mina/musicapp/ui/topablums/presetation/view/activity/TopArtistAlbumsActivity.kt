package com.task.mina.musicapp.ui.topablums.presetation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.view.extension.setVisible
import com.task.mina.musicapp.base.presentation.view.extension.showSnack
import com.task.mina.musicapp.base.presentation.viewmodel.ViewModelFactory
import com.task.mina.musicapp.ui.albumdetails.AlbumDetailsActivity
import com.task.mina.musicapp.ui.albumdetails.AlbumDetailsActivity.Companion.EXTRA_ALBUM_OBJECT
import com.task.mina.musicapp.ui.topablums.domain.entity.AlbumUI
import com.task.mina.musicapp.ui.topablums.presetation.view.adapter.ArtistTopAlbumAdapter
import com.task.mina.musicapp.ui.topablums.presetation.viewmodel.ArtistTopAlbumsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_top_artist_albums.*
import javax.inject.Inject


class TopArtistAlbumsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ArtistTopAlbumsViewModel>


    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ArtistTopAlbumsViewModel::class.java)
    }

    @Inject
    lateinit var manager: GridLayoutManager
    @Inject
    lateinit var adapter: ArtistTopAlbumAdapter

    companion object {
        val EXTRA_ARTIST_NAME: String = "artist name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_top_artist_albums)
        initToolbar()
        initArtistAlbumsRecyclerView()
        subscribeOnArtistAlbumsObservable()
        subscribleOnArtistAlbumItemClickEvent()

    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.miStore) {
                mViewModel.storeAristAlbum()
            } else {

                mViewModel.deleteAristAlbum()
            }
            false
        }
    }

    override fun onStart() {
        super.onStart()
        getActivityBundle()
    }
    /**
     * method which get all intent
     * extras from previous Activity
     */
    private fun getActivityBundle() {
        val extras: Bundle? = intent.extras
        extras?.let {
            val artistName = it.getString(EXTRA_ARTIST_NAME)
            (artistName.isNotEmpty()).let {
                supportActionBar?.title = artistName
                mViewModel.getArtistTopAlbums(artistName = artistName)
            }

        }
    }


    /**
     * method which initialize recycler view
     * and configure its adapter and manager
     */
    private fun initArtistAlbumsRecyclerView() {
        rclArtistTopAlbums.setHasFixedSize(true)
        rclArtistTopAlbums.layoutManager = manager
        rclArtistTopAlbums.adapter = adapter
    }


    private fun subscribeOnArtistAlbumsObservable() {
        mViewModel.mArtistAlbums.observe(this, Observer { albums ->
            albums?.let {
                adapter.addMoreItemsFirst(it.toMutableList())
            }
        })
        mViewModel.mTopAlbumsObservable.observe(this,
                successObserver = Observer {
                    it?.let {
                        rootTopAlbums.showSnack(it)

                    }
                }, commonErrorObserver = Observer {
        }, loadingObserver = Observer {
            it?.let {
                progress.setVisible(it)
            }
        }, networkErrorConsumer = Observer {
            rootTopAlbums.showSnack(getString(R.string.no_internet_message), Snackbar.LENGTH_LONG)
        })

    }

    private fun subscribleOnArtistAlbumItemClickEvent() {
        adapter.getViewClickedObservable().subscribe {
            it?.let {
                navigatetoAlbumDetailsActivity(it)

            }
        }
    }

    private fun navigatetoAlbumDetailsActivity(artistAlbum: AlbumUI) {
        val intent = Intent(this, AlbumDetailsActivity::class.java)
        intent.putExtra(EXTRA_ALBUM_OBJECT, artistAlbum)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

}
