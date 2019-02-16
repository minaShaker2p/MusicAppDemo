package com.task.mina.musicapp.ui.searchscreen.presentation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View

import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.view.extension.afterTextChanged
import com.task.mina.musicapp.base.presentation.view.extension.setVisible
import com.task.mina.musicapp.base.presentation.view.extension.showSnack
import com.task.mina.musicapp.base.presentation.viewmodel.ViewModelFactory
import com.task.mina.musicapp.ui.searchscreen.presentation.viewmodel.SearchArtistViewmodel
import com.task.mina.musicapp.ui.topablums.presetation.view.TopArtistAlbumsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_artist.*
import javax.inject.Inject

class SearchArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchArtistViewmodel>


    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchArtistViewmodel::class.java)
    }
    @Inject
    lateinit var manager: LinearLayoutManager
    @Inject
    lateinit var adapter: ArtistListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_search_artist)
        initToolbar()
        intiSearchButton()
        initArtistEditText()
        initSearchResultObservable()
        initArtistRecylerView()
        subscribleOnArtistRecylerClickEvent()
    }

    private fun initToolbar() {
        supportActionBar?.title = getString(R.string.toobar_search)
    }

    private fun initArtistEditText() {
        edtArtistName.afterTextChanged {
            btnSearch.isEnabled = it.isNotEmpty()
        }
    }


    private fun initArtistRecylerView() {
        manager.orientation = LinearLayoutManager.VERTICAL
        rclArtist.layoutManager = manager
        rclArtist.adapter = adapter

    }

    private fun initSearchResultObservable() {
        mViewModel.mSearchObservable.observe(this, successObserver = Observer {
            it?.let {
                // first  clear items
                adapter.getItems().clear()
                adapter.notifyDataSetChanged()
                adapter.addMoreItemsFirst(it.toMutableList())

            }
        },
                loadingObserver = Observer {
                    it?.let {
                        progress.setVisible(it)
                    }
                },
                commonErrorObserver = Observer {

                }
                , networkErrorConsumer = Observer {
            rootSearch.showSnack(getString(R.string.no_internet_message), Snackbar.LENGTH_LONG)
        })
    }

    private fun intiSearchButton() {
        btnSearch.isEnabled = false
        btnSearch.setOnClickListener {
            mViewModel.search(artistName = edtArtistName.text.toString())
        }
    }

    private fun subscribleOnArtistRecylerClickEvent() {
        adapter.getViewClickedObservable().subscribe {
            navigateToArtistTopAlbumsActivity(it)
        }
    }

    private fun navigateToArtistTopAlbumsActivity(artistName: String) {
        val intent = Intent(this, TopArtistAlbumsActivity::class.java)
        intent.putExtra(TopArtistAlbumsActivity.EXTRA_ARTIST_NAME, artistName)
        startActivity(intent)
    }

}
