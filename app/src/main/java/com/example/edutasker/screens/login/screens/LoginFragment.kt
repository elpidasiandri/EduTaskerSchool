package com.example.edutasker.screens.login.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.edutasker.composable.login.LoginScreenComposable
import com.example.edutasker.screens.login.viewModelState.LoginScreenViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment() : Fragment() {
    private val loginViewModel: LoginScreenViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                LoginScreenComposable(onEvent = loginViewModel::onEvent)
            }
        }
    }
}