package ge.gogichaishvili.themovielist.login.data.network.dto

data class LoginRequestDto(
    val username: String,
    val password: String,
    val request_token: String
)
