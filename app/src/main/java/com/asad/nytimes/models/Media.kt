package com.asad.nytimes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Media(
    val type: String,
    @SerializedName("media-subtype")
    val subType: String,
    val caption: String,
    val copyright: String,
    @SerializedName("media-approved_for_syndication")
    val approvedForSyndication: Int,
    @SerializedName("media-metadata")
    val mediaMetaData: ArrayList<MediaMetaData>
) : Serializable