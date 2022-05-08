package ge.gogichaishvili.themovielist.domain.repository

import ge.gogichaishvili.themovielist.network.data.GenresResponse
import ge.gogichaishvili.themovielist.network.helpers.RetrofitHelper

class TvShowsGenresRepository {

    suspend fun getTvShowsGenres(apiKey: String): GenresResponse {
        return RetrofitHelper.popularsApi.getTvShowsGenres(apiKey)
    }

    companion object {
        private var instance: TvShowsGenresRepository? = null
        fun getInstance(): TvShowsGenresRepository {
            return if (instance != null) {
                instance!!
            } else {
                instance = TvShowsGenresRepository()
                instance!!
            }
        }
    }
}