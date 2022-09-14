package ge.gogichaishvili.themovielist.main.domain.model

data class PopularItemsUiModel(
    val page: Int,
    val results: List<ItemList>
)
