package com.task.mina.musicapp.ui.mainscreen.presenation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.viewmodel.ViewModelFactory
import com.task.mina.musicapp.ui.mainscreen.presenation.viewmodel.MainScreenViewModel
import com.task.mina.musicapp.ui.searchscreen.presentation.view.SearchArtistActivity
import com.task.mina.musicapp.ui.topablums.data.local.mapToUI
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
        initFabSearchIcon()
        initAlbumsRecylerView()
        ObserveStoredAlbumsChange()
    }

    private fun initAlbumsRecylerView() {
        rclAlbums.setHasFixedSize(true)
        rclAlbums.layoutManager = manager
        rclAlbums.adapter = adapter
    }

    private fun ObserveStoredAlbumsChange() {
        mViewModel.getAllStoredLocalAlbums().observe(this, Observer {
            if (it != null) {
                if (it.isNotEmpty())
                    adapter.addMoreItemsFirst(it.map { it.mapToUI() }.toMutableList())
                else {
                    // clear items
                    adapter.getItems().clear()
                    adapter.notifyDataSetChanged()
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


}

