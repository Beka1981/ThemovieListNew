package ge.gogichaishvili.themovielist.domain.model

import java.io.Serializable

data class DetailsUiModel(
    val id: Int? = null,
    val backdropPath: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null
) : Serializable
