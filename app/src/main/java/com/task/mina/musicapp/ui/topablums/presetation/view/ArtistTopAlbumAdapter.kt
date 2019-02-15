package com.task.mina.musicapp.ui.topablums.presetation.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.task.mina.musicapp.R
import com.task.mina.musicapp.base.presentation.view.adapter.BaseRecyclerAdapter
import com.task.mina.musicapp.base.presentation.view.extension.getInflatedView
import com.task.mina.musicapp.base.presentation.view.extension.loadFromUrl
import com.task.mina.musicapp.data.remote.network.response.Album
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

import kotlinx.android.synthetic.main.item_artist_album.view.*


class ArtistTopAlbumAdapter : BaseRecyclerAdapter<Album>() {


    private val mViewClickSubject = PublishSubject.create<Album>()

    fun getViewClickedObservable(): Observable<Album> {
        return mViewClickSubject
    }

    override fun getAdapterPageSize(): Int {
        return PAGE_SIZE
    }

    override fun mainItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.item_artist_album)
    }


    override fun mainItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ArtistAlbumViewHolder(view)
    }

    override fun onBindMainViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArtistAlbumViewHolder) {
            holder.bind(getItems()[position])
            holder.itemView.setOnClickListener {
                mViewClickSubject.onNext(getItems()[position])
            }
        }
    }


    override fun footerItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.view_footer_progress_bar)
    }

    private class ArtistAlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Album) = with(itemView) {
            tvAlbumName.text = item.name
            tvArtistName.text = item.artist.name
            val image: String? = item.image.filter {
                it.size == "large"
            }.map {
                it.text
            }.first()
            image?.let {
                imageArtistAlbum.loadFromUrl(it)
            }

        }
    }


    companion object {
        private const val PAGE_SIZE = 30

    }
}