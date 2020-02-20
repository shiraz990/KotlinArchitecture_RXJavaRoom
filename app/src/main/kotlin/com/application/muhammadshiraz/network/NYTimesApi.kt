package com.application.muhammadshiraz.network

import com.application.muhammadshiraz.model.ArticleResponse
 import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface NYTimesApi {
    /**
     * Get the list of the post from the API
     */
    @GET("{part}.json")
    fun getArticles(@Path( value ="part") part :String , @Query("api-key") apiKey: String): Observable<ArticleResponse>


}