package com.example.graphapplication.ui.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.graphapplication.R
import com.example.graphapplication.base.BaseActivity
import com.example.graphapplication.data.model.PieChart
import com.example.graphapplication.databinding.ActivityMainBinding
import com.example.graphapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfig: AppBarConfiguration

    override fun handleTask() {
        setUpData()
        initView()
    }

    private fun setUpData() {
    }

    private fun initView() {
        appBarConfig =
            AppBarConfiguration(setOf(R.id.pieChartFragment, R.id.columnChartFragment))

        // action bar
        setupActionBarWithNavController(controller, appBarConfig)
        // bottom view
        binding.bottomNavigationView.setupWithNavController(controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun getActivityBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getNavHostFragment(): NavHostFragment =
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
}