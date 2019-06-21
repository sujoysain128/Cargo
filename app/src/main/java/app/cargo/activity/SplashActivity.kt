package app.cargo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import app.cargo.MainActivity
import app.cargo.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_splash)
        startNextActivity()
    }


    fun startNextActivity() {
        Handler().postDelayed({
            val i = Intent(this, SigninActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}
