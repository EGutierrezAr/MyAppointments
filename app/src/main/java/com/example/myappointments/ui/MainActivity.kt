package com.example.myappointments.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myappointments.util.PreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myappointments.util.PreferenceHelper.get
import com.example.myappointments.util.PreferenceHelper.set
import com.example.myappointments.R
import com.example.myappointments.io.ApiService
import com.example.myappointments.io.response.LoginResponse
import com.example.myappointments.util.toast
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    val snackBar by lazy {
        Snackbar.make(mainLayout,
            R.string.press_back_again, Snackbar.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Persistence:
        //Shared preferences *
        //sqlite
        //files
        /*
        val preferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val session = preferences.getBoolean("session", false)
        if (session)
        */
        val preferences =
            PreferenceHelper.defaultPrefs(this)
        //if (preferences["session", false])
        if (preferences["jwt", ""].contains("."))
            goToMenuActivity()

        btnLogin.setOnClickListener {
            // validates
            performLogin()
        }

        tvGoToRegister.setOnClickListener {
            Toast.makeText(this, getString(R.string.please_fill_your_register_data), Toast.LENGTH_SHORT).show()

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performLogin () {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (email.trim().isEmpty() || password.trim().isEmpty() ){
            toast(getString(R.string.error_empty_credencials))
            return
        }

        val call = apiService.postLogin(email, password)
        call.enqueue(object: Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                toast(t.localizedMessage)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    val loginResponse = response.body()
                    if (loginResponse == null){
                        toast(getString(R.string.error_login_response))
                        return
                    }
                    if(loginResponse.success){
                        createSessionPreference(loginResponse.jwt)
                        toast(getString(R.string.welcome_name, loginResponse.user.name))
                        goToMenuActivity()
                    }else{
                        toast(getString(R.string.error_invalid_credentials))
                    }
                }else{
                    toast(getString(R.string.error_login_response))
                }
            }

        })
    }

    private fun createSessionPreference(jwt: String){
        /*val preferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("session", true)
        editor.apply()*/
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["jwt"] = jwt
    }

    private fun goToMenuActivity(){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (snackBar.isShown)
            super.onBackPressed()
        else
            snackBar.show()
    }
}
