package ge.gogichaishvili.themovielist.login.data.repository_impl

import ge.gogichaishvili.themovielist.login.data.network.services.AuthServiceApi
import ge.gogichaishvili.themovielist.login.domain.model.AuthUiModel
import ge.gogichaishvili.themovielist.login.domain.repository.AuthRepository
import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.app.network.getErrorExceptionMessage
import ge.gogichaishvili.themovielist.cache.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val authServiceApi: AuthServiceApi
) : AuthRepository {
    override suspend fun authRequest(apiKey: String): Resource<Flow<AuthUiModel>> {
        return try {

            val response =
                authServiceApi.getAuthToken(apiKey).toDomainModel()

            UserSession.setAccessToken(response.request_token)

            val result = flow<AuthUiModel> {
                emit(response)
            }
            Resource.Success(data = result)

        } catch (e: Exception) {
            val errorMessage = (e as? HttpException)?.getErrorExceptionMessage()
            Resource.Error(message = errorMessage ?: e.message ?: "")
        }
    }

}