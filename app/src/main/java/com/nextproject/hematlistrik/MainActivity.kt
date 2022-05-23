package com.nextproject.hematlistrik

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nextproject.hematlistrik.databinding.ActivityMainBinding
import com.nextproject.hematlistrik.databinding.ActivityMainBinding.inflate
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view);

        binding.faTambah.setOnClickListener {
            DialogPilihKwh().show(supportFragmentManager, "DialogPilihKwh")
        }
    }


}