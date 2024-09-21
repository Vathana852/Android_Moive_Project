package com.example.ifood.The_Moive_db

import com.example.ifood.fakeproduct_model.FakeProduct
import com.example.ifood.fakeproduct_model.FakeProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.themoviedb.org"

interface MovieService{
    @GET("3/movie/now_playing")

    suspend fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "a8d05436293e69ae2c9eeb592f0237dc"
    ): TheMovieDbModel


    companion object {
        var service : MovieService? = null
        fun getInstance(): MovieService {
            if(service == null){
                service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieService::class.java)
            }
            return service!!
        }
    }
}
