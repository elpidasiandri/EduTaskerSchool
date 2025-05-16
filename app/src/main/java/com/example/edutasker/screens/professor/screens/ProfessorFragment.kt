package com.example.edutasker.screens.professor.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.edutasker.composable.professor.ProfessorScreenComposable
import com.example.edutasker.screens.professor.viewModel.ProfessorViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ProfessorFragment : Fragment() {

    private val viewModel: ProfessorViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()
                ProfessorScreenComposable(
                    onEvent = viewModel::onEvent,
                    state = state,
                )
            }
        }
    }
}
