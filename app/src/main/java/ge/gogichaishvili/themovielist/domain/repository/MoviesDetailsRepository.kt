package ge.gogichaishvili.themovielist.domain.repository

import ge.gogichaishvili.themovielist.network.data.MovieDetailsResponse
import ge.gogichaishvili.themovielist.network.helpers.RetrofitHelper

class MoviesDetailsRepository {

    suspend fun getMoviesDetails(movieId: Int, apiKey: String): MovieDetailsResponse {
        return RetrofitHelper.popularsApi.getMovieDetails(movieId, apiKey)
    }

    companion object {
        private var instance: MoviesDetailsRepository? = null
        fun getInstance(): MoviesDetailsRepository {
            return if (instance != null) {
                instance!!
            } else {
                instance = MoviesDetailsRepository()
                instance!!
            }
        }
    }
}