package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val userName =  binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()

            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill your user and password", Toast.LENGTH_SHORT).show()
            }else if (userName.equals("test") && password.equals("test")){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "Your user or password is not correct", Toast.LENGTH_SHORT).show()
            }
        }

    }
}