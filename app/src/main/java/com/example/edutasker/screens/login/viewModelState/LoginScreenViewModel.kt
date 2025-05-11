package com.example.edutasker.screens.login.viewModelState

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.R
import com.example.edutasker.useCases.professor.GetProfessorByEmailUseCase
import com.example.edutasker.useCases.professor.LoginProfessorUseCase
import com.example.edutasker.useCases.student.GetStudentByEmailUseCase
import com.example.edutasker.useCases.student.LoginStudentUseCase
import com.example.edutasker.utils.CurrentUser
import com.example.edutasker.utils.catchAndHandleError
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
    private val getStudentByEmail: GetStudentByEmailUseCase,
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

    private fun showErrorBasedErrorCode(errorCode: Int) {
        when (errorCode) {
            500 -> showError(R.string.no_internet_connection)
            else -> showError(R.string.something_went_wrong)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (state.value.isStudent) {
                true -> {
                    flow {
                        emit(
                            loginStudent(
                                email = email,
                                password = password
                            )
                        )
                    }.catchAndHandleError { errorMessage, errorCode ->
                        showErrorBasedErrorCode(errorCode)
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

                false -> {
                    flow {
                        emit(
                            loginProfessor(
                                email = email,
                                password = password
                            )
                        )
                    }.catchAndHandleError { errorMessage, errorCode ->
                        showErrorBasedErrorCode(errorCode)
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
            }
        }
    }

    private suspend fun getStudentAccount(email: String) {
        flow { emit(getStudentByEmail(email)) }.catch { }
            .collect { account ->
                CurrentUser.userId = account?.studentId ?: ""
                CurrentUser.username = account?.username ?: ""
                CurrentUser.name = account?.name ?: ""
                CurrentUser.email = account?.email ?: ""
                CurrentUser.image = account?.image ?: ""
                CurrentUser.isStudent = true
            }

    }

    private suspend fun getProfessorAccount(email: String) {
        flow { emit(getProfessorByEmail(email)) }.catch { }
            .collect { account ->
                CurrentUser.userId = account?.profId ?: ""
                CurrentUser.username = account?.username ?: ""
                CurrentUser.name = account?.name ?: ""
                CurrentUser.email = account?.email ?: ""
                CurrentUser.image = account?.image ?: ""
                CurrentUser.isStudent = false
            }
    }
}