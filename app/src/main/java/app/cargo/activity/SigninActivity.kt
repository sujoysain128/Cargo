package app.cargo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.cargo.MainActivity
import app.cargo.R
import app.cargo.dilaog_fragment.SignupDialogFragment
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        tv_login.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        tv_sign_up.setOnClickListener {
        val signupDialogFragment = SignupDialogFragment()
            signupDialogFragment.show(supportFragmentManager, "SignupDialogFragment")
        }

    }
}
