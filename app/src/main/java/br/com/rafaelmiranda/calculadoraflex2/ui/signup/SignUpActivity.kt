package br.com.rafaelmiranda.calculadoraflex2.ui.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.rafaelmiranda.calculadoraflex2.R
import br.com.rafaelmiranda.calculadoraflex2.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        btCreate.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    saveRealTimeDatabase()
                } else {
                    Toast.makeText(this@SignUpActivity, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveRealTimeDatabase() {
        val user = User(inputEmail.text.toString(), inputPassword.text.toString(), inputPhone.text.toString())

        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user).addOnCompleteListener{
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                    val returningIntent = Intent()
                    returningIntent.putExtra("email", inputEmail.text.toString())
                    setResult(Activity.RESULT_OK, returningIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao criar usuário", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveInRealTimeDatabase2() {
        val user = User(
            inputName.text.toString(),
            inputEmail.text.toString(),
            inputPhone.text.toString()
        )
        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                    val returnIntent = Intent()
                    returnIntent.putExtra(
                        "email", inputEmail.text.toString()
                    )
                    setResult(RESULT_OK, returnIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao criar o Usuário", Toast.LENGTH_SHORT).show()
                }
            }

    }

}
