package com.mexicandeveloper.vaxcareexercise.ui

import android.content.ContentResolver
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.mexicandeveloper.vaxcareexercise.R
import com.mexicandeveloper.vaxcareexercise.databinding.ActivityMainBinding
import com.mexicandeveloper.vaxcareexercise.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        setSupportActionBar(binding.mainToolbar)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            //Toast.makeText(this,"change", Toast.LENGTH_SHORT).show()
            if (supportFragmentManager.backStackEntryCount > 0) {
                binding.mainToolbar.navigationIcon =
                    ContextCompat.getDrawable(this, android.R.drawable.ic_menu_close_clear_cancel)
                binding.mainToolbar.visibility = View.VISIBLE
                binding.mainToolbar.setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            } else {
                binding.mainToolbar.navigationIcon = null
            }

        }

    }



}