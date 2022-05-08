package ge.gogichaishvili.themovielist.domain.model

data class PopularItemsUiModel(
    val page: Int,
    val results: List<ItemList>
)
