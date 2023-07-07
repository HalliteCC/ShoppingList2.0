package com.example.buylist2.ui.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.ui.*
import com.example.buylist2.R
import com.example.buylist2.databinding.ActivityMainBinding
import com.example.buylist2.ui.fragment.ItemsListFragmentDirections
import com.example.buylist2.ui.fragment.ShoppingListFragmentDirections
import com.example.buylist2.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        //Navigation
        setupNavigation()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun setupNavigation(){
        //FAB para duas activity
        binding.appBarMain.fab.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            val currentFragmentId = navController.currentDestination?.id
            val navigation: NavDirections
            if(currentFragmentId == R.id.shopping_List){
                navigation = ShoppingListFragmentDirections.actionShoppingListToShoppingListRegisterFragment()
                navController.navigate(navigation)
            }else if(currentFragmentId == R.id.items_list) {
                navigation = ItemsListFragmentDirections.actionItemsListToItemRegisterFragment()
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
                R.id.shopping_List, R.id.items_list, R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //Logout
        navView.setNavigationItemSelectedListener {
            if(it.itemId == R.id.nav_logout){
                viewModel.logout()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }else {
                NavigationUI.onNavDestinationSelected(it, navController)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            true
        }
    }




}