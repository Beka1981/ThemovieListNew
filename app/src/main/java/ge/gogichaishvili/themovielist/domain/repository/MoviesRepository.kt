package ge.gogichaishvili.themovielist.domain.repository

import ge.gogichaishvili.themovielist.network.data.PopularMoviesResponse
import ge.gogichaishvili.themovielist.network.helpers.RetrofitHelper

class MoviesRepository {

    suspend fun getMovies(apiKey: String): PopularMoviesResponse {
        return RetrofitHelper.popularsApi.getPopularMovies(apiKey)
    }

    companion object {
        private var instance: MoviesRepository? = null
        fun getInstance(): MoviesRepository {
            return if (instance != null) {
                instance!!
            } else {
                instance = MoviesRepository()
                instance!!
            }
        }
    }
}