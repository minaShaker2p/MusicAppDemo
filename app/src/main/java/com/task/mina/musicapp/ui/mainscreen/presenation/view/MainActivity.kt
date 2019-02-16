package com.task.mina.musicapp.ui.mainscreen.presenation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.viewmodel.ViewModelFactory
import com.task.mina.musicapp.ui.albumdetails.AlbumDetailsActivity
import com.task.mina.musicapp.ui.mainscreen.presenation.viewmodel.MainScreenViewModel
import com.task.mina.musicapp.ui.searchscreen.presentation.view.SearchArtistActivity
import com.task.mina.musicapp.ui.topablums.data.local.mapToUI
import com.task.mina.musicapp.ui.topablums.domain.entity.AlbumUI
import com.task.mina.musicapp.ui.topablums.presetation.view.ArtistTopAlbumAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainScreenViewModel>


    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainScreenViewModel::class.java)
    }

    @Inject
    lateinit var manager: GridLayoutManager
    @Inject
    lateinit var adapter: ArtistTopAlbumAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        initToolbar()
        initFabSearchIcon()
        initAlbumsRecylerView()
        ObserveStoredAlbumsChange()
        subscribleOnArtistAlbumItemClickEvent()
    }

    private fun initToolbar() {
        supportActionBar?.title = getString(R.string.toobar_main_screen)
    }

    private fun initAlbumsRecylerView() {
        rclAlbums.setHasFixedSize(true)
        rclAlbums.layoutManager = manager
        rclAlbums.adapter = adapter
    }

    private fun ObserveStoredAlbumsChange() {
        mViewModel.getAllStoredLocalAlbums().observe(this, Observer {
            if (it != null) {
                if (it.isNotEmpty()) {
                    layoutNoData.visibility = View.GONE
                    adapter.addMoreItemsFirst(it.map { it.mapToUI() }.toMutableList())
                } else {
                    // clear items
                    adapter.getItems().clear()
                    adapter.notifyDataSetChanged()
                    layoutNoData.visibility = View.VISIBLE
                }
            }

        })

    }

    private fun initFabSearchIcon() {
        fabSearch.setOnClickListener {
            navigateToSearchScreen()
        }
    }


    private fun navigateToSearchScreen() {
        startActivity(Intent(this, SearchArtistActivity::class.java))
    }

    private fun subscribleOnArtistAlbumItemClickEvent() {
        adapter.getViewClickedObservable().subscribe {
            it?.let {
                navigatetoAlbumDetailsActivity(it)

            }
        }
    }

    private fun navigatetoAlbumDetailsActivity(album: AlbumUI) {
        val intent = Intent(this, AlbumDetailsActivity::class.java)
        intent.putExtra(AlbumDetailsActivity.EXTRA_ALBUM_OBJECT, album)
        startActivity(intent)
    }


}

