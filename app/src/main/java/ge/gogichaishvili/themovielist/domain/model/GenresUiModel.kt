package ge.gogichaishvili.themovielist.domain.model

import ge.gogichaishvili.themovielist.network.data.Genre
import java.io.Serializable

data class GenresUiModel(
    val genres: List<Genre>
) : Serializable
