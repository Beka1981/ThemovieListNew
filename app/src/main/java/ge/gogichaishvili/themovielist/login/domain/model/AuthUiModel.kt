package ge.gogichaishvili.themovielist.login.domain.model

import java.io.Serializable

class AuthUiModel (
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)  : Serializable