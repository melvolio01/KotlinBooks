package com.example.kotlinbooks.Interfaces

import com.example.kotlinbooks.BuildConfig
import com.example.kotlinbooks.Models.RetrofitBook
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookFinderAPI {
    @GET("volumes")
    fun getBook(
        @Query("q") bookTitle: String,
        @Query("key") key: String = BuildConfig.GOOGLE_BOOKS_KEY,
        @Query("maxResults") maxResults: Int = 1
    ):
            Call<RetrofitBook>
}