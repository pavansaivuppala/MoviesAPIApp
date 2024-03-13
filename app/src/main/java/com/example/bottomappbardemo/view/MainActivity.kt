package com.example.bottomappbardemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bottomappbardemo.R
import com.example.bottomappbardemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var walletFragment: WalletFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        homeFragment = HomeFragment()
        walletFragment = WalletFragment()
        settingsFragment = SettingsFragment()

        binding.bottomMenu.setOnNavigationItemReselectedListener {
            menuItem ->
            when(menuItem.itemId){
                R.id.home -> openFragment(homeFragment)
                R.id.wallet -> openFragment(walletFragment)
                R.id.setting -> openFragment(settingsFragment)
            }
        }
    }

    private fun openFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()

    }


}