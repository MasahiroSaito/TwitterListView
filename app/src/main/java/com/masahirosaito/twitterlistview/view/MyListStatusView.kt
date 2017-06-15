package com.masahirosaito.twitterlistview.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.masahirosaito.twitterlistview.R
import com.masahirosaito.twitterlistview.bindView
import com.masahirosaito.twitterlistview.model.MyListStatus

class MyListStatusView : ConstraintLayout {

    constructor(context: Context,
                attrs: AttributeSet,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context,
                attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    val userIconImageView: ImageView by bindView(R.id.user_icon)
    val userNameTextView: TextView by bindView(R.id.user_name)
    val textTextView: TextView by bindView(R.id.text)
    val mediaImageView: ImageView by bindView(R.id.media_image)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_myliststatus, this)
    }

    fun setMyListStatus(myListStatus: MyListStatus) {
        userNameTextView.text = myListStatus.user.name
        textTextView.text = myListStatus.text
        Glide.with(context).load(myListStatus.user.profile_image_url).into(userIconImageView)

        val medias = myListStatus.entities.media

        if (medias.isNotEmpty()) {
            if (medias.first().type == "photo") {
                Glide.with(context).load(medias.first().media_url).into(mediaImageView)
            }
        }
    }
}
