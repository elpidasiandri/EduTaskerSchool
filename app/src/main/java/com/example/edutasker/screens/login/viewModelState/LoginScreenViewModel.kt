package com.example.edutasker.screens.login.viewModelState

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.R
import com.example.edutasker.screens.login.viewModelState.stateAndEvents.LoginEvents
import com.example.edutasker.screens.login.viewModelState.stateAndEvents.LoginState
import com.example.edutasker.screens.login.viewModelState.stateAndEvents.LoginUiEvents
import com.example.edutasker.useCases.professor.GetProfessorByEmailUseCase
import com.example.edutasker.useCases.professor.LoginProfessorUseCase
import com.example.edutasker.useCases.student.GetStudentByEmailUseCase
import com.example.edutasker.useCases.student.LoginStudentUseCase
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.utils.catchAndHandleError
import com.example.edutasker.utils.orEmptyIfNull
import com.example.edutasker.utils.showErrorBasedErrorCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginProfessor: LoginProfessorUseCase,
    private val loginStudent: LoginStudentUseCase,
    private val getProfessorByEmail: GetProfessorByEmailUseCase,
    private val getStudentByEmail: GetStudentByEmailUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.GoToLogin -> {
                _state.update {
                    it.copy(
                        isStudent = event.isStudent,
                        uiEvents = LoginUiEvents.GoToLoginScreen
                    )
                }
            }

            is LoginEvents.TryToConnect -> login(event.email, event.password)
            else -> {}
        }
    }

    fun setEventNone() {
        viewModelScope.launch() {
            _state.update {
                it.copy(
                    uiEvents = LoginUiEvents.None
                )
            }
        }
    }

    private fun showError(messageInt: Int) {
        _state.update {
            it.copy(
                messageErrorId = messageInt,
                uiEvents = LoginUiEvents.ErrorToast
            )
        }
    }


    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (state.value.isStudent) {
                true -> {
                    connectAsAStudent(email, password)
                }

                false -> {
                    connectAsAProfessor(email, password)
                }
            }
        }
    }

    private suspend fun connectAsAProfessor(email: String, password: String) {
        flow {
            emit(
                loginProfessor(
                    email = email,
                    password = password
                )
            )
        }.catchAndHandleError { errorMessage, errorCode ->
            showError(
                errorCode.showErrorBasedErrorCode()
            )
        }.collect { professor ->
            if (professor != null) {
                getProfessorAccount(email)
                _state.update {
                    it.copy(
                        uiEvents = LoginUiEvents.GoToProfessorScreen
                    )
                }
            } else {
                showError(R.string.incorrect_data)
            }
        }
    }

    private suspend fun connectAsAStudent(email: String, password: String) {
        flow {
            emit(
                loginStudent(
                    email = email,
                    password = password
                )
            )
        }.catchAndHandleError { errorMessage, errorCode ->
            showError(
                errorCode.showErrorBasedErrorCode()
            )
        }.collect { student ->
            if (student != null) {
                getStudentAccount(email)
                _state.update {
                    it.copy(
                        uiEvents = LoginUiEvents.GoToStudentScreen
                    )
                }
            } else {
                showError(R.string.incorrect_data)
            }
        }
    }

    private suspend fun getStudentAccount(email: String) {
        flow { emit(getStudentByEmail(email)) }.catch { }
            .collect { account ->
                CurrentUser.setCurrentUser(
                    userId = account?.studentId.orEmptyIfNull(),
                    isStudent = true,
                    name = account?.name.orEmptyIfNull(),
                    email = account?.email.orEmptyIfNull(),
                    image = account?.image.orEmptyIfNull(),
                    username = account?.username.orEmptyIfNull()
                )
            }

    }

    private suspend fun getProfessorAccount(email: String) {
        flow { emit(getProfessorByEmail(email)) }.catch { }
            .collect { account ->
                CurrentUser.setCurrentUser(
                    userId = account?.profId.orEmptyIfNull(),
                    isStudent = false,
                    name = account?.name.orEmptyIfNull(),
                    email = account?.email.orEmptyIfNull(),
                    image = account?.image.orEmptyIfNull(),
                    username = account?.username.orEmptyIfNull()
                )
            }
    }
}