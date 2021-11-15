package com.example.vinilosdjam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.vinilosdjam.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button4.setOnClickListener { goToMenu() }
    }

    fun goToMenu(){
        val intent = Intent(this, TabsActivity::class.java)
        startActivity(intent)

    }

}