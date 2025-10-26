package com.example.vijayiproject.domain

import com.example.vijayiproject.data.dto.movieData
import com.example.vijayiproject.data.remote.ApiService
import com.example.vijayiproject.utils.Constants
import com.example.vijayiproject.utils.Resource
import io.reactivex.rxjava3.core.Observable

class MovieRepository(
    private val api: ApiService
) {
//    suspend fun getMoviesUpComingList(): List<movieData>{
//        return api.getMoviesAndTvSeriesList(
//            type = "movie",
//            category = "upcoming",
//            page = 1,
//            apiKey = Constants.API_KEY
//        ).results
//    }

    fun getMoviesUpComingList(): Observable<Resource<List<movieData>>> {
        return api.getMoviesAndTvSeriesList(
            type = "movie",
            category = "upcoming",
            page = 1,
            apiKey = Constants.API_KEY
        ).map<Resource<List<movieData>>> { response ->
            Resource.Success(response.results)
        }
            .onErrorReturn { throwable ->
                Resource.Error(throwable.message ?: "An unexpected error occurred")
            }
            //.toObservable()
            .startWithItem(Resource.Loading(isLoading = true))

    }

    fun getMoviesPopularList(): Observable<Resource<List<movieData>>> {
        return api.getMoviesAndTvSeriesList(
            type = "movie",
            category = "popular",
            page = 1,
            apiKey = Constants.API_KEY
        )
            .map<Resource<List<movieData>>> { response ->
                Resource.Success(response.results)
            }
            .onErrorReturn { throwable ->
                Resource.Error(throwable.message ?: "An unexpected error occurred")
            }
            //.toObservable()
            .startWithItem(Resource.Loading(true))


    }
}