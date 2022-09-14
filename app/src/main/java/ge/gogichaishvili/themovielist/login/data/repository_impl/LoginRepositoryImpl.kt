package ge.gogichaishvili.themovielist.login.data.repository_impl

import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.app.network.getErrorExceptionMessage
import ge.gogichaishvili.themovielist.cache.UserSession
import ge.gogichaishvili.themovielist.login.data.network.dto.LoginRequestDto
import ge.gogichaishvili.themovielist.login.data.network.services.LoginServiceApi
import ge.gogichaishvili.themovielist.login.domain.model.AuthUiModel
import ge.gogichaishvili.themovielist.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception

class LoginRepositoryImpl(
    private val loginServiceApi: LoginServiceApi
) : LoginRepository {

    override suspend fun loginRequest(
        apiKey: String,
        loginData: LoginRequestDto
    ): Resource<Flow<AuthUiModel>> {
        return try {

            val response = loginServiceApi.login(apiKey, loginData).toDomainModel()

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

