package com.masahirosaito.twitterlistview.model

import android.os.Parcel
import android.os.Parcelable

data class MyListStatus(val created_at: String,
                        val id: Long,
                        val id_str: String,
                        val text: String,
                        val user: User) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MyListStatus> = object : Parcelable.Creator<MyListStatus> {
            override fun createFromParcel(source: Parcel): MyListStatus = source.run {
                MyListStatus(
                        readString(),
                        readLong(),
                        readString(),
                        readString(),
                        readParcelable(User::class.java.classLoader)
                )
            }
            override fun newArray(size: Int): Array<MyListStatus?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(created_at)
            writeLong(id)
            writeString(id_str)
            writeString(text)
            writeParcelable(user, flags)
        }
    }
}