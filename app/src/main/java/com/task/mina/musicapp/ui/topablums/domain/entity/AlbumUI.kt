package com.task.mina.musicapp.ui.topablums.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mina Alfy on 2/15/2019.
 */
@Parcelize
data class AlbumUI(
        var albumName: String,
        var albumPlayCount: Long,
        var albumUrl: String,
        var artistName: String,
        var albumImage: String) : Parcelable