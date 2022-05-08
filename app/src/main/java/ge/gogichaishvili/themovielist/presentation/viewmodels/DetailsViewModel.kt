package ge.gogichaishvili.themovielist.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ge.gogichaishvili.themovielist.domain.model.DetailsUiModel
import ge.gogichaishvili.themovielist.domain.model.GenresUiModel
import ge.gogichaishvili.themovielist.domain.repository.MoviesDetailsRepository
import ge.gogichaishvili.themovielist.domain.repository.MoviesGenresRepository
import ge.gogichaishvili.themovielist.domain.repository.TvShowDetailsRepository
import ge.gogichaishvili.themovielist.domain.repository.TvShowsGenresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val moviesDetailsMutableLiveData = MutableLiveData<DetailsUiModel>()
    private val moviesDetailsRepository = MoviesDetailsRepository.getInstance()

    private val tvShowDetailsMutableLiveData = MutableLiveData<DetailsUiModel>()
    private val tvShowDetailsRepository = TvShowDetailsRepository.getInstance()

    private val moviesGenresMutableLiveData = MutableLiveData<GenresUiModel>()
    private val moviesGenresRepository = MoviesGenresRepository.getInstance()

    private val tvShowGenresMutableLiveData = MutableLiveData<GenresUiModel>()
    private val tvShowGenresRepository = TvShowsGenresRepository.getInstance()

    fun getMoviesDetailsLiveData(): LiveData<DetailsUiModel> {
        return moviesDetailsMutableLiveData
    }

    fun getTvShowsDetailsLiveData(): LiveData<DetailsUiModel> {
        return tvShowDetailsMutableLiveData
    }

    fun getMoviesGenresLiveData(): LiveData<GenresUiModel> {
        return moviesGenresMutableLiveData
    }

    fun getTvShowsGenresLiveData(): LiveData<GenresUiModel> {
        return tvShowGenresMutableLiveData
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            moviesDetailsMutableLiveData.postValue(
                moviesDetailsRepository.getMoviesDetails(
                    movieId,
                    "843c612d1207fdec63f0e6a5fd426d68"
                )
                    .toDomainModel()
            )
        }
    }

    fun getTvShowDetails(tvShowId: Int) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            tvShowDetailsMutableLiveData.postValue(
                tvShowDetailsRepository.getShowsDetails(
                    tvShowId,
                    "843c612d1207fdec63f0e6a5fd426d68"
                )
                    .toDomainModel()
            )
        }
    }

    fun getMovieGenres() {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            moviesGenresMutableLiveData.postValue(
                moviesGenresRepository.getMoviesGenres("843c612d1207fdec63f0e6a5fd426d68")
                    .toDomainModel()
            )
        }
    }

    fun getTvShowGenres() {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            tvShowGenresMutableLiveData.postValue(
                tvShowGenresRepository.getTvShowsGenres("843c612d1207fdec63f0e6a5fd426d68")
                    .toDomainModel()
            )
        }
    }

}