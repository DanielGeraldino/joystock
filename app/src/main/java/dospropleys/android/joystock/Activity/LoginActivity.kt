package dospropleys.android.joystock.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dospropleys.android.joystock.FirebaseHelper.Autenticacao
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Autenticacao.getAuth()

        btnEntraSistema.setOnClickListener {
            btnEntrar()
        }
    }

    fun btnEntrar() {
        var login: String = editLogin.text.toString()
        var senha: String = editSenha.text.toString()

        Autenticacao.login(login, senha, this)

        if(Autenticacao.getUsuario() != null) {
            abrirTelaPrincipal()
        }
    }

    fun abrirTelaPrincipal() {
        var intent: Intent = Intent(this@LoginActivity, TelaPrincipal::class.java)
        startActivity(intent)
    }

    public override fun onStart() {
        super.onStart()

        if (Autenticacao.getUsuario() != null) {
            Log.e("usario atual: ", Autenticacao.getUsuario()?.email.toString())
            abrirTelaPrincipal()
        } else {
            Log.d("usuario atual: ", "sem usuario")
        }
    }
}