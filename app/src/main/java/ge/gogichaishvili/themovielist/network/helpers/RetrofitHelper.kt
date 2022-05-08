package ge.gogichaishvili.themovielist.network.helpers

import ge.gogichaishvili.themovielist.network.api.PopularsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
     val popularsApi = Retrofit.Builder()
         .baseUrl("https://api.themoviedb.org/3/")
         .addConverterFactory(GsonConverterFactory.create())
         .build()
         .create(PopularsApi::class.java)
}