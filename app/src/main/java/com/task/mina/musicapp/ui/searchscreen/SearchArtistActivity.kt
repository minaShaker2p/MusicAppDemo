package com.task.mina.musicapp.ui.searchscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.viewmodel.ViewModelFactory
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
        intiSearchButton()
        initSearchResultObservable()
        initArtistRecylerView()
    }

    private fun initArtistRecylerView() {
        manager.orientation = LinearLayoutManager.VERTICAL
        rclArtist.layoutManager = manager
        rclArtist.adapter = adapter

    }

    private fun initSearchResultObservable() {
        mViewModel.mSearchObservable.observe(this, successObserver = Observer {
            it?.let {
                adapter.addMoreItems(it.toMutableList())

            }
        },
                loadingObserver = Observer { },
                commonErrorObserver = Observer { }
                , networkErrorConsumer = Observer { })
    }

    private fun intiSearchButton() {
        btnSearch.setOnClickListener {
            mViewModel.search(artistName = edtArtistName.text.toString())
        }
    }
}
