package com.luthfir.examplelogin.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.dicoding.huguapplication.R
import com.dicoding.huguapplication.databinding.ActivityLoginBinding
import com.dicoding.huguapplication.ui.home.HomeFragment
import com.dicoding.huguapplication.ui.home.MajalahActivity
import com.dicoding.huguapplication.ui.login.RegisterActivity
import com.dicoding.huguapplication.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObserver()
        initListener()

    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun initObserver() {

        viewModel.getLoginStatus(this)

        viewModel.isLoading.observe(this) { isloading ->
            showLoading(isloading)
        }
        viewModel.loginMessage.observe(this) { message ->
            if (!message.isNullOrEmpty()) {
                showSnackbar(message)
            }
        }
        viewModel.loginStatus.observe(this) { loginStatus ->
            if (loginStatus) {
                viewModel.setLoginStatus(this@LoginActivity, loginStatus)
                val intentHome = Intent(this@LoginActivity, HomeFragment::class.java)
                startActivity(intentHome)
                finish()
            }
        }

        viewModel.loginStatus.observe(this) { loginStatus ->
            if (loginStatus) {
                viewModel.setLoginStatus(this@LoginActivity, loginStatus)
                val intentHome = Intent(this@LoginActivity, HomeFragment::class.java)
                startActivity(intentHome)
                finish()
            }
    }
    }

    private fun initListener() {

        binding.btnLogin.setOnClickListener {
            initLogin()
        }
    }

    private fun initRegister() {
        binding.btnRegister.setOnClickListener {
            val intentRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intentRegister)
            finish()
        }
    }

    private fun initLogin() {
        val textUsername = binding.etLoginUsername.text.toString()
        val textPassword = binding.etLoginPassword.text.toString()

        viewModel.checkLogin(textUsername, textPassword)
    }

    private fun showSnackbar(message: String) {

        val snackbar = Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_SHORT)
        snackbar.setAction("Dismiss") {
            snackbar.dismiss()
        }
        snackbar.show()
    }

    private fun showLoading(isLoading: Boolean) {

        binding.progressBar.isVisible = isLoading
    }
}