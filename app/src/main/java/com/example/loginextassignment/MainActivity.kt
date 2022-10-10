package com.example.loginextassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.loginextassignment.databinding.ActivityMainBinding
import com.example.loginextassignment.ui.account.AccountFragment
import com.example.loginextassignment.ui.home.HomeFragment
import com.example.loginextassignment.ui.order.OrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    private val homeFragment by lazy {
        HomeFragment()
    }

    private val ordersFragment by lazy {
        OrderFragment()
    }

    private val accountFragment by lazy {
        AccountFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.toolbar)
        init()
        activityMainBinding.toolbarText.text = getString(R.string.home)
        setCurrentFragment(R.id.homeScreen)
        setBottomNNavBar()
    }

    private fun setBottomNNavBar(){
        activityMainBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.home ->{
                    activityMainBinding.toolbarText.text = getString(R.string.home)
                    setCurrentFragment(R.id.homeScreen)
                }

                R.id.order ->{
                    activityMainBinding.toolbarText.text = getString(R.string.order)
                    setCurrentFragment(R.id.orderScreen)
                }

                R.id.account ->{
                    activityMainBinding.toolbarText.text = getString(R.string.account)
                    setCurrentFragment(R.id.accountScreen)
                }
            }
            true
        }

    }

    private fun init(){
        val navController = Navigation.findNavController(this,R.id.navHostFragment)
        activityMainBinding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setCurrentFragment(fragment: Int){
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.navigation_main, true).build()
        Navigation.findNavController(this, R.id.navHostFragment)
            .navigate(fragment, null, navOptions )
    }

}