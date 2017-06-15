package com.masahirosaito.twitterlistview.model

import android.os.Parcel
import android.os.Parcelable

data class MyListStatus(val id: Long,
                        val text: String,
                        val user: User,
                        val entities: Entities) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MyListStatus> = object : Parcelable.Creator<MyListStatus> {
            override fun createFromParcel(source: Parcel): MyListStatus = source.run {
                MyListStatus(
                        readLong(),
                        readString(),
                        readParcelable(User::class.java.classLoader),
                        readParcelable(Entities::class.java.classLoader)
                )
            }
            override fun newArray(size: Int): Array<MyListStatus?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeLong(id)
            writeString(text)
            writeParcelable(user, flags)
            writeParcelable(entities, flags)
        }
    }
}
