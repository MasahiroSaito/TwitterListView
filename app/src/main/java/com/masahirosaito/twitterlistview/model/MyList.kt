package com.masahirosaito.twitterlistview.model

import android.os.Parcel
import android.os.Parcelable

data class MyList(val slug: String,
                  val name: String,
                  val created_at: String,
                  val uri: String,
                  val subscriber_count: Long,
                  val id_str: String,
                  val member_count: Long,
                  val mode: String,
                  val id: Long,
                  val full_name: String,
                  val description: String,
                  val user: User,
                  val following: Boolean) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MyList> = object : Parcelable.Creator<MyList> {
            override fun createFromParcel(source: Parcel): MyList = source.run {
                MyList(
                        readString(),
                        readString(),
                        readString(),
                        readString(),
                        readLong(),
                        readString(),
                        readLong(),
                        readString(),
                        readLong(),
                        readString(),
                        readString(),
                        readParcelable(User::class.java.classLoader),
                        readValue(Boolean::class.java.classLoader) as Boolean
                )
            }

            override fun newArray(size: Int): Array<MyList?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(slug)
            writeString(name)
            writeString(created_at)
            writeString(uri)
            writeLong(subscriber_count)
            writeString(id_str)
            writeLong(member_count)
            writeString(mode)
            writeLong(id)
            writeString(full_name)
            writeString(description)
            writeParcelable(user, flags)
            writeValue(following)
        }
    }
}