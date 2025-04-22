package com.example.edutasker.screens.login

import android.os.Bundle
import com.example.edutasker.screens.login.viewModelState.LoginScreenViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.key
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.edutasker.composable.errorToast.CustomToast
import com.example.edutasker.databinding.LoginActivityBinding
import com.example.edutasker.di.loginModule
import com.example.edutasker.screens.login.viewModelState.LoginUiEvents
import com.example.edutasker.screens.professorActivities.ProfessorActivity
import kotlinx.coroutines.launch
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.unloadKoinModules

class LoginMainActivity() : AppCompatActivity() {
    private val loginViewModel: LoginScreenViewModel by viewModel()
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(loginModule)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(loginModule)
    }

    private fun setUpViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.state.collect { state ->
                    when (state.uiEvents) {
                        LoginUiEvents.ErrorToast -> {
                            showComposeToast(getString(state.messageErrorId))
                            loginViewModel.setEventNone()
                        }

                        LoginUiEvents.GoToProfessorScreen -> {
                            ProfessorActivity.newInstance(context = this@LoginMainActivity)
                            finish()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun showComposeToast(message: String) {
        val toastId = System.currentTimeMillis()
        binding.composeToastContainer.setContent {
            key(toastId) {
                CustomToast(message = message)
            }
        }
    }
}