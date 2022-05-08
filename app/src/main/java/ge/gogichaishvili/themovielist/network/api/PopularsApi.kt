package ge.gogichaishvili.themovielist.network.api

import ge.gogichaishvili.themovielist.network.data.*
import retrofit2.http.*

interface PopularsApi {

    @Headers("platform: Web")
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String
    ): PopularMoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id")
        movieId: Int,
        @Query("api_key")
        apiKey: String
    ): MovieDetailsResponse

    @Headers("platform: Web")
    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("api_key")
        apiKey: String
    ): PopularTvShowsResponse

    @GET("tv/{id}")
    suspend fun getTvShowDetails(
        @Path("id")
        tvShowId: Int,
        @Query("api_key")
        apiKey: String
    ): TvShowDetailsResponse

    @Headers("platform: Web")
    @GET("genre/movie/list")
    suspend fun getMoviesGenres(
        @Query("api_key")
        apiKey: String
    ): GenresResponse

    @Headers("platform: Web")
    @GET("genre/tv/list")
    suspend fun getTvShowsGenres(
        @Query("api_key")
        apiKey: String
    ): GenresResponse

}