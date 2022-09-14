package ge.gogichaishvili.themovielist.main.domain.repository

import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.main.domain.model.PopularItemsUiModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun moviesRequest(apiKey: String): Resource<Flow<PopularItemsUiModel>>
}