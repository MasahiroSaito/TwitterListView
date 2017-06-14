package com.masahirosaito.twitterlistview.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.masahirosaito.twitterlistview.model.MyList
import com.masahirosaito.twitterlistview.view.MyListView

class MyListAdapter(private val context: Context) : BaseAdapter() {

    var myLists: List<MyList> = emptyList()

    override fun getCount(): Int = myLists.size

    override fun getItem(position: Int): Any = myLists[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?): View =
            ((convertView as? MyListView) ?: MyListView(context)).apply {
                setMyList(myLists[position])
            }
}
