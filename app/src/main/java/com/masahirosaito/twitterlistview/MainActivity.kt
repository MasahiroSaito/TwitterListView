package com.masahirosaito.twitterlistview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.masahirosaito.twitterlistview.Value.accessToken
import com.masahirosaito.twitterlistview.Value.accessTokenSecret
import com.masahirosaito.twitterlistview.Value.consumerKey
import com.masahirosaito.twitterlistview.Value.consumerSecret
import com.masahirosaito.twitterlistview.adapter.MyListAdapter
import com.masahirosaito.twitterlistview.client.MyListClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class MainActivity : AppCompatActivity() {

    val myListAdapter: MyListAdapter by lazy {
        MyListAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = (findViewById(R.id.list_view) as ListView).apply {
            adapter = myListAdapter
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            startActivity(MyListStatusActivity.intent(this, myListAdapter.myLists[position]))
        }
    }

    override fun onResume() {
        super.onResume()

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

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val myListClient = retrofit.create(MyListClient::class.java)

        myListClient.getMyLists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("list", it.toString())
                    myListAdapter.myLists = it
                    myListAdapter.notifyDataSetChanged()
                }, {
                    toast("エラー: $it")
                })
    }
}
