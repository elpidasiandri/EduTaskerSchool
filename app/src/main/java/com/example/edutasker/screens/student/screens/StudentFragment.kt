package com.example.edutasker.screens.student.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.edutasker.composable.student.StudentScreenComposable
import com.example.edutasker.screens.student.viewModel.StudentViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class StudentFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()
                StudentScreenComposable(
                    onEvent = viewModel::onEvent,
                    state = state
                )
            }
        }
    }
}
