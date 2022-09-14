package ge.gogichaishvili.themovielist.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ge.gogichaishvili.themovielist.login.domain.repository.AuthRepository
import ge.gogichaishvili.themovielist.login.domain.repository.LoginRepository
import ge.gogichaishvili.themovielist.login.domain.usecase.AuthUseCase
import ge.gogichaishvili.themovielist.login.domain.usecase.LoginUseCase
import ge.gogichaishvili.themovielist.main.domain.repository.MoviesRepository
import ge.gogichaishvili.themovielist.main.domain.usecase.MoviesUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(authRepository = authRepository)
    }

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository = loginRepository)
    }

    @Provides
    fun provideMoviesUseCase(moviesRepository: MoviesRepository): MoviesUseCase {
        return MoviesUseCase(moviesRepository = moviesRepository)
    }

}