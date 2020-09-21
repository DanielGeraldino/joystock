package dospropleys.android.joystock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        btnEntraSistema.setOnClickListener {
            btnEntrar()
        }
    }

    fun btnEntrar() {
        var login: String = editLogin.text.toString()
        var senha: String = editSenha.text.toString()

        loginNovoUsuario(login, senha)
    }

   fun loginNovoUsuario(login: String, senha: String) {
        auth.signInWithEmailAndPassword(login, senha)
            .addOnCompleteListener(this) {
                if(it.isSuccessful) {
                    Log.d("login", "deu certo")
                } else {
                    Log.w("login", it.exception)
                }
            }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.e("use atual: ", currentUser.email.toString())
        } else {
            Log.e("use atual: ", "sem usuario")
        }
    }
}