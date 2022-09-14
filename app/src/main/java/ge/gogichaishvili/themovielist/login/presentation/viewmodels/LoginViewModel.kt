package ge.gogichaishvili.themovielist.login.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.gogichaishvili.themovielist.R
import ge.gogichaishvili.themovielist.app.network.Constants
import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.app.network.SingleLiveEvent
import ge.gogichaishvili.themovielist.cache.UserSession
import ge.gogichaishvili.themovielist.login.domain.model.AuthUiModel
import ge.gogichaishvili.themovielist.login.domain.usecase.AuthUseCase
import ge.gogichaishvili.themovielist.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _authFlow = MutableStateFlow<Resource<AuthUiModel>>(Resource.Loading())
    val authFlow: StateFlow<Resource<AuthUiModel>> = _authFlow

    private val _loginFlow = MutableStateFlow<Resource<AuthUiModel>>(Resource.Loading())
    val loginFlow: StateFlow<Resource<AuthUiModel>> = _loginFlow

    private val _viewStateLiveData = SingleLiveEvent<ViewState>()
    val viewStateLiveData: LiveData<ViewState> get() = _viewStateLiveData

    data class ViewState(val isValid: Boolean, val errorMessageRes: Int)

    init {
        viewModelScope.launch {
            val result = authUseCase.auth(Constants.API_KEY)
            if (result.isSuccess) {
                result.data!!
                    .catch { exception: Throwable ->
                        _authFlow.value = Resource.Error(exception.message.toString())
                    }
                    .collect {
                        _authFlow.value = Resource.Success(it)
                    }
            } else {
                _authFlow.value = Resource.Error(message = result.message ?: "")
            }

        }
    }

    fun onLogin(username: String, password: String) {
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            val viewState = ViewState(
                isValid = false,
                errorMessageRes = R.string.all_fields_are_required
            )
            _viewStateLiveData.postValue(viewState)
        } else {
            val token = UserSession.getAccessToken()
            if (token != null) {
                viewModelScope.launch {
                    val result = loginUseCase.login(Constants.API_KEY, username, password, token)
                    if (result.isSuccess) {
                        result.data!!
                            .catch { exception: Throwable ->
                                _loginFlow.value = Resource.Error(exception.message.toString())
                            }
                            .collect {
                                _loginFlow.value = Resource.Success(it)
                            }
                    } else {
                        _loginFlow.value = Resource.Error(message = result.message ?: "")
                    }
                }
            }
        }

    }

}