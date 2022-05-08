package ge.gogichaishvili.themovielist.domain.repository

import ge.gogichaishvili.themovielist.network.data.PopularTvShowsResponse
import ge.gogichaishvili.themovielist.network.helpers.RetrofitHelper

class TvShowRepository {

    suspend fun getTvShows(apiKey: String): PopularTvShowsResponse {
        return RetrofitHelper.popularsApi.getPopularTvShow(apiKey)
    }

    companion object {
        private var instance: TvShowRepository? = null
        fun getInstance(): TvShowRepository {
            return if (instance != null) {
                instance!!
            } else {
                instance = TvShowRepository()
                instance!!
            }
        }
    }
}