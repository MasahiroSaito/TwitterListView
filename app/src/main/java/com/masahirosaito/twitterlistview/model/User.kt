package com.masahirosaito.twitterlistview.model

import android.os.Parcel
import android.os.Parcelable

data class User(val profile_background_tile: String = "",
                val profile_sidebar_border_color: String = "",
                val name: String = "",
                val profile_sidebar_fill_color: String = "",
                val location: String = "",
                val created_at: String = "",
                val profile_image_url: String = "",
                val is_translator: Boolean = false,
                val id_str: String = "",
                val profile_link_color: String = "",
                val follow_request_sent: Boolean = false,
                val favourites_count: Long = 0,
                val contributors_enabled: Boolean = false,
                val url: String = "",
                val default_profile: Boolean = false,
                val profile_image_url_https: String = "",
                val utc_offset: Long = 0,
                val profile_banner_url: String = "",
                val id: Long = 0,
                val profile_use_background_image: Boolean = false,
                val listed_count: Long = 0,
                val profile_text_color: String = "",
                val protected: Boolean = false,
                val lang: String = "",
                val followers_count: Long = 0,
                val description: String = "",
                val notifications: Boolean = false,
                val geo_enabled: Boolean = false,
                val verified: Boolean = false,
                val time_zone: String = "",
                val profile_background_color: String = "",
                val profile_background_image_url_https: String = "",
                val statuses_count: Long = 0,
                val friends_count: Long = 0,
                val default_profile_image: Boolean = false,
                val profile_background_image_url: String = "",
                val following: Boolean = false,
                val screen_name: String = "") : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = source.run {
                User(
                        readString(),
                        readString(),
                        readString(),
                        readString(),
                        readString(),
                        readString(),
                        readString(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readString(),
                        readString(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readLong(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readString(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readString(),
                        readLong(),
                        readString(),
                        readLong(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readLong(),
                        readString(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readString(),
                        readLong(),
                        readString(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readString(),
                        readString(),
                        readString(),
                        readLong(),
                        readLong(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readString(),
                        readValue(Boolean::class.java.classLoader) as Boolean,
                        readString()
                )
            }

            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(profile_background_tile)
            writeString(profile_sidebar_border_color)
            writeString(name)
            writeString(profile_sidebar_fill_color)
            writeString(location)
            writeString(created_at)
            writeString(profile_image_url)
            writeValue(is_translator)
            writeString(id_str)
            writeString(profile_link_color)
            writeValue(follow_request_sent)
            writeLong(favourites_count)
            writeValue(contributors_enabled)
            writeString(url)
            writeValue(default_profile)
            writeString(profile_image_url_https)
            writeLong(utc_offset)
            writeString(profile_banner_url)
            writeLong(id)
            writeValue(profile_use_background_image)
            writeLong(listed_count)
            writeString(profile_text_color)
            writeValue(protected)
            writeString(lang)
            writeLong(followers_count)
            writeString(description)
            writeValue(notifications)
            writeValue(geo_enabled)
            writeValue(verified)
            writeString(time_zone)
            writeString(profile_background_color)
            writeString(profile_background_image_url_https)
            writeLong(statuses_count)
            writeLong(friends_count)
            writeValue(default_profile_image)
            writeString(profile_background_image_url)
            writeValue(following)
            writeString(screen_name)
        }
    }
}