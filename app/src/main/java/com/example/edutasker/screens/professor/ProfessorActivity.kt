package com.example.edutasker.screens.professor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.key
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.edutasker.BaseActivity
import com.example.edutasker.R
import com.example.edutasker.composable.errorOrSuccessToast.CustomToastComposable
import com.example.edutasker.databinding.ActivityBaseBinding
import com.example.edutasker.di.notificationCommonModule
import com.example.edutasker.di.professorModule
import com.example.edutasker.screens.login.LoginMainActivity
import com.example.edutasker.screens.notification.NotificationActivity
import com.example.edutasker.screens.professor.viewModel.ProfessorViewModel
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorEvents
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorUiEvents
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ProfessorActivity() : BaseActivity<ActivityBaseBinding>() {
    private val viewModel: ProfessorViewModel by viewModel()
    private lateinit var notificationLauncher: ActivityResultLauncher<Intent>

    override fun inflateBinding(): ActivityBaseBinding {
        return ActivityBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(
            listOf(
                notificationCommonModule,
                professorModule
            )
        )
        setNavGraph()
        setUpViewModel()
        setUpLauncher()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(
            listOf(
                notificationCommonModule,
                professorModule
            )
        )
    }

    private fun setUpLauncher() {
        notificationLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val taskId = result.data?.getStringExtra("taskId")
                    taskId?.let { taskIdNotNull ->
                        viewModel.onEvent(ProfessorEvents.OpenTaskDialog(taskIdNotNull))
                    }
                }
            }
    }

    private fun setUpViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state.uiEvents) {
                        ProfessorUiEvents.GoToLogout -> {
                            LoginMainActivity.newInstance(context = this@ProfessorActivity)
                            finish()
                        }

                        ProfessorUiEvents.Error -> {
                            showMessage(state.messageErrorId, isError = true)

                        }

                        ProfessorUiEvents.Success -> {
                            showMessage(state.messageErrorId, isError = false)
                        }

                        ProfessorUiEvents.OpenNotification -> {
                            val intent =
                                Intent(this@ProfessorActivity, NotificationActivity::class.java)
                            notificationLauncher.launch(intent)
                            viewModel.setEventNone()
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
        navController.setGraph(R.navigation.professor_nav)
    }

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, ProfessorActivity::class.java)
            context.startActivity(intent)
        }
    }
}