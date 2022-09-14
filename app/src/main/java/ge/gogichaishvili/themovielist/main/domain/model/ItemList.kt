package ge.gogichaishvili.themovielist.main.domain.model

import java.io.Serializable

data class ItemList(
    val id: Int,
    val originalTitle: String?,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val releaseDate: String? = null,
    val overview: String? = null,
    val popularity: Double? = null
) : Serializable
