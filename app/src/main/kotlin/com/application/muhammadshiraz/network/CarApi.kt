package com.sevenpeakssoftware.muhammadshiraz.network

import com.sevenpeakssoftware.muhammadshiraz.model.ArticlesResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface CarApi {
    /**
     * Get the list of the post from the API
     */
    @GET("article/get_articles_list")
    fun getCars(): Observable<ArticlesResponse>
}