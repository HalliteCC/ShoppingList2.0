package com.example.buylist2.activities

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import com.example.buylist2.R
import com.example.buylist2.databinding.ActivityMainBinding
import com.example.buylist2.ui.home.HomeFragmentDirections
import com.example.buylist2.ui.slideshow.SlideshowFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)


        //FAB para duas activity
        binding.appBarMain.fab.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            val currentFragmentId = navController.currentDestination?.id
            val navigation: NavDirections
            if(currentFragmentId == R.id.nav_home){
                navigation = HomeFragmentDirections.actionNavHomeToProductsFragment()
                navController.navigate(navigation)
            }else if(currentFragmentId == R.id.nav_slideshow) {
                navigation = SlideshowFragmentDirections.actionNavSlideshowToNavGallery()
                navController.navigate(navigation)
            }

        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}