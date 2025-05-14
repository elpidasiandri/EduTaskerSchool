package com.example.edutasker.screens.login.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.edutasker.R
import com.example.edutasker.composable.login.LoginChooseUserComposable
import com.example.edutasker.screens.login.viewModelState.LoginScreenViewModel
import com.example.edutasker.screens.login.viewModelState.stateAndEvents.LoginUiEvents
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class LoginChooseUserFragment : Fragment() {
    private val loginViewModel: LoginScreenViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val uiState by loginViewModel.state.collectAsState()
                val navController = findNavController()

                LoginChooseUserComposable(
                    onEvent = loginViewModel::onEvent
                )

                LaunchedEffect(uiState.uiEvents) {
                    when (uiState.uiEvents) {
                        is LoginUiEvents.GoToLoginScreen -> {
                            navController.navigate(R.id.action_loginChooseUserFragment_to_loginFragment2)
                            loginViewModel.setEventNone()
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}
