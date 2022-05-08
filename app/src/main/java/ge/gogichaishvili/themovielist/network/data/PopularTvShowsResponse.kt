package ge.gogichaishvili.themovielist.network.data

import com.google.gson.annotations.SerializedName
import ge.gogichaishvili.themovielist.domain.model.ItemList
import ge.gogichaishvili.themovielist.domain.model.ItemTypesEnum
import ge.gogichaishvili.themovielist.domain.model.PopularItemsUiModel

data class PopularTvShowsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val result: List<TvShowItem>
) {
    fun toDomainModel(): PopularItemsUiModel {
        return PopularItemsUiModel(
            page = page,
            result.map {
                ItemList(
                    originalTitle = it.original_name,
                    posterPath = it.poster_path,
                    voteAverage = it.vote_average,
                    releaseDate = it.first_air_date,
                    overview = it.overview,
                    popularity = it.popularity,
                    id = it.id,
                    viewType = ItemTypesEnum.SHOW
                )
            }
        )
    }
}
