package ge.gogichaishvili.themovielist.app.network

object ApiEndpoints {

    //BaseUrl
    const val baseUrl = "https://api.themoviedb.org/3/"

    //Login
    private const val token = "authentication/token"
    const val new = "$token/new"
    const val login = "$token/validate_with_login"
    const val movies = "movie/top_rated"

}