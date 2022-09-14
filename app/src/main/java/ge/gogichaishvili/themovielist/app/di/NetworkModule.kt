package ge.gogichaishvili.themovielist.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.gogichaishvili.themovielist.login.data.network.services.AuthServiceApi
import ge.gogichaishvili.themovielist.app.network.ApiEndpoints
import ge.gogichaishvili.themovielist.app.network.RequestInterceptor
import ge.gogichaishvili.themovielist.login.data.network.services.LoginServiceApi
import ge.gogichaishvili.themovielist.main.data.network.services.MoviesServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRequestInterceptor(): RequestInterceptor = RequestInterceptor()

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiEndpoints.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthServiceApi(retrofit: Retrofit): AuthServiceApi =
        retrofit.create(AuthServiceApi::class.java)

    @Singleton
    @Provides
    fun provideLoginServiceApi(retrofit: Retrofit): LoginServiceApi =
        retrofit.create(LoginServiceApi::class.java)

    @Singleton
    @Provides
    fun provideMoviesServiceApi(retrofit: Retrofit): MoviesServiceApi =
        retrofit.create(MoviesServiceApi::class.java)

}