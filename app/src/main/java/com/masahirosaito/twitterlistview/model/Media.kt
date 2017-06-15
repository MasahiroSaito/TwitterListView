package com.masahirosaito.twitterlistview.model

import android.os.Parcel
import android.os.Parcelable

data class Media(val id: Long = 0,
                 val media_url: String = "",
                 val type: String = "") : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Media> = object : Parcelable.Creator<Media> {
            override fun createFromParcel(source: Parcel): Media = source.run {
                Media(
                        readLong(),
                        readString(),
                        readString()
                )
            }
            override fun newArray(size: Int): Array<Media?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeLong(id)
            writeString(media_url)
            writeString(type)
        }
    }
}
