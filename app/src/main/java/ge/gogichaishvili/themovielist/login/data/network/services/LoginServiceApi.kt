package ge.gogichaishvili.themovielist.login.data.network.services

import ge.gogichaishvili.themovielist.app.network.ApiEndpoints
import ge.gogichaishvili.themovielist.login.data.network.dto.AuthResponseDto
import ge.gogichaishvili.themovielist.login.data.network.dto.LoginRequestDto
import retrofit2.http.*

interface LoginServiceApi {
    @POST(ApiEndpoints.login)
    suspend fun login(
        @Query("api_key")
        apiKey: String,
        @Body loginData: LoginRequestDto
    ): AuthResponseDto
}