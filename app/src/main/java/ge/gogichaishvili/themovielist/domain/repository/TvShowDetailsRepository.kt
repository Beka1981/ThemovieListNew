package ge.gogichaishvili.themovielist.domain.repository

import ge.gogichaishvili.themovielist.network.data.TvShowDetailsResponse
import ge.gogichaishvili.themovielist.network.helpers.RetrofitHelper

class TvShowDetailsRepository {

    suspend fun getShowsDetails(tvShowId: Int, apiKey: String): TvShowDetailsResponse {
        return RetrofitHelper.popularsApi.getTvShowDetails(tvShowId, apiKey)
    }

    companion object {
        private var instance: TvShowDetailsRepository? = null
        fun getInstance(): TvShowDetailsRepository {
            return if (instance != null) {
                instance!!
            } else {
                instance = TvShowDetailsRepository()
                instance!!
            }
        }
    }
}