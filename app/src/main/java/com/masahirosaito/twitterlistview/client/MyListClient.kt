package com.masahirosaito.twitterlistview.client

import com.masahirosaito.twitterlistview.model.MyList
import retrofit2.http.GET
import rx.Observable

interface MyListClient {
    @GET
    fun getMyLists(): Observable<List<MyList>>
}