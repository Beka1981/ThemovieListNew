package ge.gogichaishvili.themovielist.login.domain.usecase

import ge.gogichaishvili.themovielist.login.domain.model.AuthUiModel
import ge.gogichaishvili.themovielist.login.domain.repository.AuthRepository
import ge.gogichaishvili.themovielist.app.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AuthUseCase(
    private val authRepository: AuthRepository
) {
    suspend fun auth(
        apiKey: String
    ): Resource<Flow<AuthUiModel>> =
        withContext(Dispatchers.IO) {
            val result =
                authRepository.authRequest(apiKey)
            if (result.isSuccess) {
                Resource.Success(data = result.data!!)
            } else {
                Resource.Error(
                    message = result.message ?: "",
                    errorCode = (result as Resource.Error).errorCode
                )
            }
        }
}