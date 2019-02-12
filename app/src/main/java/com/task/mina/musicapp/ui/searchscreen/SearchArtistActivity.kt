package com.task.mina.musicapp.ui.searchscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_search_artist)
        intiSearchButton()
        initSearchResultObservable()
    }

    private fun initSearchResultObservable() {
        mViewModel.mSearchObservable.observe(this, successObserver = Observer {
            it?.let {
                if (it.size > 0)
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
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
