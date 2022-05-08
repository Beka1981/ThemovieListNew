package ge.gogichaishvili.themovielist.domain.repository

import ge.gogichaishvili.themovielist.network.data.GenresResponse
import ge.gogichaishvili.themovielist.network.helpers.RetrofitHelper

class MoviesGenresRepository {

    suspend fun getMoviesGenres(apiKey: String): GenresResponse {
        return RetrofitHelper.popularsApi.getMoviesGenres(apiKey)
    }

    companion object {
        private var instance: MoviesGenresRepository? = null
        fun getInstance(): MoviesGenresRepository {
            return if (instance != null) {
                instance!!
            } else {
                instance = MoviesGenresRepository()
                instance!!
            }
        }
    }
}