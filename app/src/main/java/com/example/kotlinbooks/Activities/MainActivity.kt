package com.example.kotlinbooks.Activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbooks.R

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check for internet connection
        fun isNetworkAvailable(): Boolean {
            val cm =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (cm is ConnectivityManager) {
                val networkInfo: NetworkInfo? = cm.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

        if (!isNetworkAvailable()) {
            AlertDialog.Builder(this)
                .setTitle("No internet")
                .setMessage("Please check your wireless and internet settings")
                .setPositiveButton(
                    "Go to settings"
                ) { dialog, which -> startActivityForResult(Intent(Settings.ACTION_SETTINGS), 0) }
                .setNegativeButton("OK", null)
                .show()
        }
    }
}
