package com.masahirosaito.twitterlistview.model

import android.os.Parcel
import android.os.Parcelable

data class Entities(val media: MutableList<Media> = mutableListOf()) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Entities> = object : Parcelable.Creator<Entities> {
            override fun createFromParcel(source: Parcel): Entities = source.run {
                Entities(createTypedArrayList(Media.CREATOR))
            }
            override fun newArray(size: Int): Array<Entities?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeTypedList(media)
        }
    }
}
