package com.example.edutasker.screens.notification

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.edutasker.composable.notification.screen.FullScreenNotificationUI
import com.example.edutasker.di.notificationActivityModule
import com.example.edutasker.screens.notification.viewModel.NotificationViewModel
import com.example.edutasker.screens.notification.viewModel.viewModelAndStaet.NotificationEvents
import com.example.edutasker.screens.notification.viewModel.viewModelAndStaet.NotificationEventsUI
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class NotificationActivity : ComponentActivity() {
    private val viewModel: NotificationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(notificationActivityModule)
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            FullScreenNotificationUI(
                notifications = state.notifications,
                onDismiss = { finish() },
                onNotificationClick = { id ->
                    viewModel.onEvent(
                        NotificationEvents.MarkNotificationAsRead(
                            id
                        )
                    )
                },
                errorMessage = if (state.messageErrorId != -1) {
                    getString(state.messageErrorId)
                } else null,
                initializeMessage = { viewModel.initializeMessage() },
                openTask = { taskId ->
                    val resultIntent = Intent().apply {
                        putExtra("taskId", taskId)
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            )
        }

        observeViewModel()
    }

    override fun onDestroy() {
        unloadKoinModules(notificationActivityModule)
        super.onDestroy()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state.uiEvents) {
                        NotificationEventsUI.Exit -> finish()
                        else -> {}
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, NotificationActivity::class.java)
            context.startActivity(intent)
        }
    }
}