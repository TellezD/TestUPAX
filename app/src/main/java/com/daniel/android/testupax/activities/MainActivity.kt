package com.daniel.android.testupax.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.daniel.android.testupax.R
import com.daniel.android.testupax.databinding.MainActivityBinding
import com.daniel.android.testupax.fragments.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.MainListener {

    private val binding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (currentNavController().currentDestination?.id == R.id.main_fragment) {
            finish()
            return false
        } else {
            currentNavController().navigateUp()
        }
    }

    private fun currentNavController() = findNavController(R.id.navHostFragment)

    override fun goToMapsPointers() {
        startActivity(Intent(this, GoogleMapsActivity::class.java))
    }

    override fun goToDynamicView() {
        currentNavController().navigate(R.id.go_to_dynamic)
    }

    override fun goToEmployees() {
        currentNavController().navigate(R.id.go_to_employees)
    }
}