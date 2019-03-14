package com.task.mina.musicapp.ui.searchscreen.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.view.adapter.BaseRecyclerAdapter
import com.task.mina.musicapp.base.presentation.view.extension.getInflatedView
import com.task.mina.musicapp.base.presentation.view.extension.loadFromUrl
import com.task.mina.musicapp.data.remote.network.response.Artist
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_artist_list.view.*
import io.reactivex.subjects.PublishSubject


class ArtistListAdapter : BaseRecyclerAdapter<Artist>() {

    private val mViewClickSubject = PublishSubject.create<String>()

    fun getViewClickedObservable(): Observable<String> {
        return mViewClickSubject
    }

    override fun getAdapterPageSize(): Int {
        return PAGE_SIZE
    }

    override fun mainItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.item_artist_list)
    }


    override fun mainItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ArtistViewHolder(view)
    }

    override fun onBindMainViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArtistViewHolder) {
            holder.bind(getItems()[position])
            holder.itemView.setOnClickListener {
                mViewClickSubject.onNext(getItems()[position].name)
            }
        }
    }


    override fun footerItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.view_footer_progress_bar)
    }

    private class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Artist) = with(itemView) {
            tvArtistName.text = item.name
            tvArtistUrl.text = item.url
            item.image.forEach {
                if (it.size == "large")
                    imgArtist.loadFromUrl(it.text,isRounded = true)
            }
            tvListenersNumber.text = item.listeners
            itemView.setOnClickListener {

            }


        }
    }


    companion object {
        private const val PAGE_SIZE = 30

    }
}