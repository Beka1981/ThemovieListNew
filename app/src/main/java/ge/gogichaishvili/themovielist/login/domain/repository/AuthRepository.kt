package ge.gogichaishvili.themovielist.login.domain.repository

import ge.gogichaishvili.themovielist.login.domain.model.AuthUiModel
import ge.gogichaishvili.themovielist.app.network.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authRequest(apiKey: String): Resource<Flow<AuthUiModel>>
}