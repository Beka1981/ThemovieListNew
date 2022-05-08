package ge.gogichaishvili.themovielist.network.data

import com.google.gson.annotations.SerializedName
import ge.gogichaishvili.themovielist.domain.model.ItemList
import ge.gogichaishvili.themovielist.domain.model.ItemTypesEnum
import ge.gogichaishvili.themovielist.domain.model.PopularItemsUiModel

data class PopularMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieItem>
) {
    fun toDomainModel(): PopularItemsUiModel {
        return PopularItemsUiModel(
            page = page,
            results.map {
                ItemList(
                    originalTitle = it.originalTitle,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage,
                    releaseDate = it.releaseDate,
                    overview = it.overview,
                    popularity = it.popularity,
                    id = it.id,
                    viewType = ItemTypesEnum.MOVIES
                )
            }
        )
    }
}

