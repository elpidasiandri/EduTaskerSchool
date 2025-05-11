package com.example.edutasker.screens.login

import android.os.Bundle
import com.example.edutasker.screens.login.viewModelState.LoginScreenViewModel
import androidx.compose.runtime.key
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.edutasker.BaseActivity
import com.example.edutasker.R
import com.example.edutasker.composable.errorToast.CustomToast
import com.example.edutasker.databinding.ActivityBaseBinding
import com.example.edutasker.di.loginModule
import com.example.edutasker.screens.login.viewModelState.LoginUiEvents
import com.example.edutasker.screens.professor.ProfessorActivity
import kotlinx.coroutines.launch
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginMainActivity() : BaseActivity<ActivityBaseBinding>() {
    private val loginViewModel: LoginScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(loginModule)
        setUpViewModel()
        setNavGraph()
    }

    override fun inflateBinding(): ActivityBaseBinding {
        return ActivityBaseBinding.inflate(layoutInflater)
    }

    private fun setNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_login)
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