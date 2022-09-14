package ge.gogichaishvili.themovielist.app.network

import ge.gogichaishvili.themovielist.cache.UserSession
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = UserSession.getAccessToken()

        val requestBuilder = chain.request().newBuilder()

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        requestBuilder.addHeader("Accept-Language", Locale.getDefault().language)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}