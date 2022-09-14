package ge.gogichaishvili.themovielist.main.data.network.dto

import com.google.gson.annotations.SerializedName
import ge.gogichaishvili.themovielist.main.domain.model.ItemList
import ge.gogichaishvili.themovielist.main.domain.model.PopularItemsUiModel

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
                    id = it.id
                )
            }
        )
    }
}

