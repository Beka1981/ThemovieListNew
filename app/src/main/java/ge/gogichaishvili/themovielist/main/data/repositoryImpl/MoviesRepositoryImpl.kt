package ge.gogichaishvili.themovielist.main.data.repositoryImpl

import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.app.network.getErrorExceptionMessage
import ge.gogichaishvili.themovielist.main.data.network.services.MoviesServiceApi
import ge.gogichaishvili.themovielist.main.domain.model.PopularItemsUiModel
import ge.gogichaishvili.themovielist.main.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MoviesRepositoryImpl(
    private val moviesServiceApi: MoviesServiceApi
) : MoviesRepository {

    override suspend fun moviesRequest(apiKey: String): Resource<Flow<PopularItemsUiModel>> {
        return try {

            val response =
                moviesServiceApi.getPopularMovies(apiKey).toDomainModel()

            val result = flow<PopularItemsUiModel> {
                emit(response)
            }
            Resource.Success(data = result)

        } catch (e: Exception) {
            val errorMessage = (e as? HttpException)?.getErrorExceptionMessage()
            Resource.Error(message = errorMessage ?: e.message ?: "")
        }
    }

}