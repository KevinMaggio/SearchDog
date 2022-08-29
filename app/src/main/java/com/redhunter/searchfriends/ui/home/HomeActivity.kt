package com.redhunter.searchfriends.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.redhunter.searchfriends.AppSearchFriends.Companion.preferences
import com.redhunter.searchfriends.R
import com.redhunter.searchfriends.databinding.ActivityHomeBinding
import com.redhunter.searchfriends.databinding.NavHeaderHomeBinding
import com.redhunter.searchfriends.ui.login.LoginActivity
import com.redhunter.searchfriends.utils.Constants.USER_NAME


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navigationView: NavigationView
    private lateinit var bindingNav: NavHeaderHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        binding.exit.setOnClickListener {
            preferences.clear()
            startActivity(Intent(this, LoginActivity::class.java))
                finish()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_terms_and_conditions
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setName()


    }

    private fun setName(){
        navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val hView: View = navigationView.getHeaderView(0)

        bindingNav = NavHeaderHomeBinding.bind(hView)
        bindingNav.tvName.text= "Hello $USER_NAME"
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}