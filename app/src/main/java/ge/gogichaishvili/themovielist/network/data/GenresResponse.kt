package ge.gogichaishvili.themovielist.network.data

import ge.gogichaishvili.themovielist.domain.model.GenresUiModel

data class GenresResponse(
    val genres: List<Genre>
) {
    fun toDomainModel(): GenresUiModel {
        return GenresUiModel(
            genres = genres
        )
    }
}