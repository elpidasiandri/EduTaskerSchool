package com.example.edutasker.screens.professor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.edutasker.BaseActivity
import com.example.edutasker.R
import com.example.edutasker.databinding.ActivityBaseBinding
import com.example.edutasker.di.professorModule
import com.example.edutasker.screens.professor.viewModel.ProfessorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ProfessorActivity() : BaseActivity<ActivityBaseBinding>() {
    private val  viewModel: ProfessorViewModel by viewModel()

    override fun inflateBinding(): ActivityBaseBinding {
        return ActivityBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(professorModule)
        setNavGraph()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(professorModule)
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