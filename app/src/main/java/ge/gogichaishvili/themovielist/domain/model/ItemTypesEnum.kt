package ge.gogichaishvili.themovielist.domain.model

enum class ItemTypesEnum(val value: Int) {

    MOVIES(1),
    SHOW(2);

    companion object {
        fun getEnumByCode(code: Int): ItemTypesEnum {
            return when (code) {
                MOVIES.value -> MOVIES
                SHOW.value -> SHOW
                else -> throw IllegalStateException()
            }
        }
    }
}