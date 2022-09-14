package ge.gogichaishvili.themovielist.login.data.network.dto

import ge.gogichaishvili.themovielist.login.domain.model.AuthUiModel

data class AuthResponseDto(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
) {
    fun toDomainModel(): AuthUiModel {
        return AuthUiModel(
            success,
            expires_at,
            request_token
        )
    }
}
