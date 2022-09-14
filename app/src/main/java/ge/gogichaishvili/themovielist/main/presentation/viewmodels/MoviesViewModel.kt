package ge.gogichaishvili.themovielist.main.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.gogichaishvili.themovielist.app.network.Constants
import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.main.domain.model.PopularItemsUiModel
import ge.gogichaishvili.themovielist.main.domain.usecase.MoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _moviesFlow = MutableStateFlow<Resource<PopularItemsUiModel>>(Resource.Loading())
    val moviesFlow: StateFlow<Resource<PopularItemsUiModel>> = _moviesFlow

    init {
        viewModelScope.launch {
            val result = moviesUseCase.getMovies(Constants.API_KEY)
            if (result.isSuccess) {
                result.data!!
                    .catch { exception: Throwable ->
                        _moviesFlow.value = Resource.Error(exception.message.toString())
                    }
                    .collect {
                        _moviesFlow.value = Resource.Success(it)
                    }
            } else {
                _moviesFlow.value = Resource.Error(message = result.message ?: "")
            }
        }
    }

}


