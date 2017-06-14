package com.masahirosaito.twitterlistview.client

import com.masahirosaito.twitterlistview.model.MyListStatus
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface MyListStatusesClient {
    @GET
    fun getMyListStatues(@Query("list_id") listId: Long) : Observable<List<MyListStatus>>
}