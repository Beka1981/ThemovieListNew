package ge.gogichaishvili.themovielist.main.domain.usecase

import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.main.domain.model.PopularItemsUiModel
import ge.gogichaishvili.themovielist.main.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getMovies(
        apiKey: String
    ): Resource<Flow<PopularItemsUiModel>> =
        withContext(Dispatchers.IO) {
            val result =
                moviesRepository.moviesRequest(apiKey)
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