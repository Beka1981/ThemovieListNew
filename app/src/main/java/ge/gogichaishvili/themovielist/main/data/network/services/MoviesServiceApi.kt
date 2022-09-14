package ge.gogichaishvili.themovielist.main.data.network.services

import ge.gogichaishvili.themovielist.app.network.ApiEndpoints
import ge.gogichaishvili.themovielist.main.data.network.dto.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesServiceApi {
    @Headers("platform: Web")
    @GET(ApiEndpoints.movies)
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String
    ): PopularMoviesResponse
}