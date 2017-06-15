package com.masahirosaito.twitterlistview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.masahirosaito.twitterlistview.Value.accessToken
import com.masahirosaito.twitterlistview.Value.accessTokenSecret
import com.masahirosaito.twitterlistview.Value.consumerKey
import com.masahirosaito.twitterlistview.Value.consumerSecret
import com.masahirosaito.twitterlistview.adapter.MyListStatusAdapter
import com.masahirosaito.twitterlistview.client.MyListStatusesClient
import com.masahirosaito.twitterlistview.model.MyList
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class MyListStatusActivity : RxAppCompatActivity() {

    val myListStatusAdapter: MyListStatusAdapter by lazy {
        MyListStatusAdapter(applicationContext)
    }

    companion object {
        private const val MY_LIST_EXTRA = "my_list"

        fun intent(context: Context, myList: MyList): Intent =
                Intent(context, MyListStatusActivity::class.java)
                        .putExtra(MY_LIST_EXTRA, myList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list_status)

        (findViewById(R.id.my_list_status_view) as ListView).apply {
            adapter = myListStatusAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        val progressBar = (findViewById(R.id.progress_bar) as ProgressBar).apply {
            visibility = View.VISIBLE
        }

        val consumer = OkHttpOAuthConsumer(consumerKey, consumerSecret).apply {
            setTokenWithSecret(accessToken, accessTokenSecret)
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
                .addInterceptor(SigningInterceptor(consumer))
                .addInterceptor(logging)
                .build()

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val myListStatuesClient = Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MyListStatusesClient::class.java)

        myListStatuesClient
                .getMyListStatues(intent.getParcelableExtra<MyList>(MY_LIST_EXTRA).id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { progressBar.visibility = View.GONE }
                .bindToLifecycle(this)
                .subscribe({
                    myListStatusAdapter.myListStatuses = it
                    myListStatusAdapter.notifyDataSetChanged()
                }, {
                    toast("ERROR: $it")
                    Log.d("MyListStatus", it.message)
                    it.printStackTrace()
                })

    }
}
