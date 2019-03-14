package com.task.mina.musicapp.ui.searchscreen.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.view.adapter.BaseRecyclerAdapter
import com.task.mina.musicapp.base.presentation.view.extension.afterTextChanged
import com.task.mina.musicapp.base.presentation.view.extension.setVisible
import com.task.mina.musicapp.base.presentation.view.extension.showSnack
import com.task.mina.musicapp.base.presentation.viewmodel.ViewModelFactory
import com.task.mina.musicapp.ui.searchscreen.presentation.view.adapter.ArtistListAdapter
import com.task.mina.musicapp.ui.searchscreen.presentation.viewmodel.SearchArtistViewmodel
import com.task.mina.musicapp.ui.topablums.presetation.view.activity.TopArtistAlbumsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_artist.*
import kotlinx.android.synthetic.main.view_search.*
import javax.inject.Inject

class SearchArtistActivity : AppCompatActivity(), BaseRecyclerAdapter.OnLoadMoreListener {

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
        initArtistEditText()
        initSearchResultObservable()
        configArtistRecylerView()
        subscribleOnArtistRecylerClickEvent()
    }


    private fun initArtistEditText() {
        search_input_text.afterTextChanged {
            execute_search_button.isEnabled = it.isNotEmpty()
        }
    }


    private fun configArtistRecylerView() {
        manager.orientation = LinearLayoutManager.VERTICAL
        adapter.addOnLoadMoreListener(this, rclArtist, manager)
        rclArtist.layoutManager = manager
        rclArtist.adapter = adapter

    }

    private fun initSearchResultObservable() {
        mViewModel.mArtistList.observe(this, Observer {
            it?.let {
                // first  clear items
                adapter.getItems().clear()
                adapter.notifyDataSetChanged()
                adapter.addMoreItemsFirst(it.toMutableList())
                layoutNoData.setVisible(it.isEmpty())
            }
        })
        mViewModel.search().observe(this, successObserver = Observer {
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
        execute_search_button.isEnabled = false
        execute_search_button.setOnClickListener {
            mViewModel.search(artistName = search_input_text.text.toString())
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

    override fun onLoadMore() {
        Toast.makeText(this, "LoafMore", Toast.LENGTH_SHORT).show()
    }


}
