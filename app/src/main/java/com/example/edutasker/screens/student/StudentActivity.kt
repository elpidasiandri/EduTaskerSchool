package com.example.edutasker.screens.student

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.key
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.edutasker.BaseActivity
import com.example.edutasker.R
import com.example.edutasker.composable.errorOrSuccessToast.CustomToastComposable
import com.example.edutasker.databinding.ActivityBaseBinding
import com.example.edutasker.di.notificationActivityModule
import com.example.edutasker.di.notificationDatabaseModule
import com.example.edutasker.di.studentModule
import com.example.edutasker.screens.login.LoginMainActivity
import com.example.edutasker.screens.student.viewModel.StudentViewModel
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentUiEvents
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class StudentActivity() : BaseActivity<ActivityBaseBinding>() {
    private val viewModel: StudentViewModel by viewModel()

    override fun inflateBinding(): ActivityBaseBinding {
        return ActivityBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(listOf(notificationDatabaseModule, notificationActivityModule, studentModule))
        setNavGraph()
        setUpViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(listOf(notificationDatabaseModule, notificationActivityModule, studentModule))
    }

    private fun setUpViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state.uiEvents) {
                        StudentUiEvents.GoToLogout -> {
                            LoginMainActivity.newInstance(context = this@StudentActivity)
                            finish()
                        }

                        StudentUiEvents.Error -> {
                            showMessage(state.messageErrorId, isError = true)

                        }

                        StudentUiEvents.Success -> {
                            showMessage(state.messageErrorId, isError = false)
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun showMessage(messageErrorId: Int, isError: Boolean) {
        showComposeToast(getString(messageErrorId), isError)
        viewModel.setEventNone()
    }

    private fun showComposeToast(message: String, isError: Boolean) {
        val toastId = System.currentTimeMillis()
        binding.composeToastContainer.setContent {
            key(toastId) {
                CustomToastComposable(isError = isError, message = message)
            }
        }
    }


    private fun setNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.student_nav)
    }

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, StudentActivity::class.java)
            context.startActivity(intent)
        }
    }
}