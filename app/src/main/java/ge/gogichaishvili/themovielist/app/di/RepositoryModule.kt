package ge.gogichaishvili.themovielist.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ge.gogichaishvili.themovielist.login.data.network.services.AuthServiceApi
import ge.gogichaishvili.themovielist.login.data.network.services.LoginServiceApi
import ge.gogichaishvili.themovielist.login.data.repository_impl.AuthRepositoryImpl
import ge.gogichaishvili.themovielist.login.data.repository_impl.LoginRepositoryImpl
import ge.gogichaishvili.themovielist.login.domain.repository.AuthRepository
import ge.gogichaishvili.themovielist.login.domain.repository.LoginRepository
import ge.gogichaishvili.themovielist.main.data.network.services.MoviesServiceApi
import ge.gogichaishvili.themovielist.main.data.repositoryImpl.MoviesRepositoryImpl
import ge.gogichaishvili.themovielist.main.domain.repository.MoviesRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideAuthRepositoryImpl(authServiceApi: AuthServiceApi): AuthRepository {
        return AuthRepositoryImpl(authServiceApi = authServiceApi) as AuthRepository
    }

    @Provides
    fun provideLoginRepositoryImpl(loginServiceApi: LoginServiceApi): LoginRepository {
        return LoginRepositoryImpl(loginServiceApi = loginServiceApi) as LoginRepository
    }

    @Provides
    fun provideMoviesRepositoryImpl(moviesServiceApi: MoviesServiceApi): MoviesRepository {
        return MoviesRepositoryImpl(moviesServiceApi = moviesServiceApi) as MoviesRepository
    }

}