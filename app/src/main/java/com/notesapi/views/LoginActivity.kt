package com.notesapi.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.livedata.app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("lifecycle-test", "LoginActivity - onCreate")
    }

    override fun onPause() {
        Log.d("lifecycle-test", "LoginActivity - onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d("lifecycle-test", "LoginActivity - onResume")
        super.onResume()
    }

    override fun onDestroy() {
        Log.d("lifecycle-test", "LoginActivity - onDestroy")
        super.onDestroy()
    }

    override fun onStart() {
        Log.d("lifecycle-test", "LoginActivity - onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d("lifecycle-test", "LoginActivity - onStop")
        super.onStop()
    }
}

