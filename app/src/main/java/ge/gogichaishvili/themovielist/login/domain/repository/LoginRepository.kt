package ge.gogichaishvili.themovielist.login.domain.repository

import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.login.data.network.dto.LoginRequestDto
import ge.gogichaishvili.themovielist.login.domain.model.AuthUiModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginRequest(apiKey: String, loginData: LoginRequestDto): Resource<Flow<AuthUiModel>>
}