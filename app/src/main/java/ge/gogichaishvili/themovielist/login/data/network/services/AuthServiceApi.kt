package ge.gogichaishvili.themovielist.login.data.network.services

import ge.gogichaishvili.themovielist.login.data.network.dto.AuthResponseDto
import ge.gogichaishvili.themovielist.app.network.ApiEndpoints
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AuthServiceApi {
    @Headers("platform: Web")
    @GET(ApiEndpoints.new)
    suspend fun getAuthToken(
        @Query("api_key")
        apiKey: String
    ): AuthResponseDto
}