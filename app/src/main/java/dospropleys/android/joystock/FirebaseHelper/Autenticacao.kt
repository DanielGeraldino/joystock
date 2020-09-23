package dospropleys.android.joystock.FirebaseHelper

import android.app.Activity
import android.text.BoringLayout
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Autenticacao {
    companion object {
        private var auth: FirebaseAuth = FirebaseAuth.getInstance()

        fun getAuth(): FirebaseAuth {
            return auth
        }

        fun login(login: String, senha: String, contexto: Activity): Boolean{

            var isAcesso: Boolean = false

            auth.signInWithEmailAndPassword(login, senha)
                .addOnCompleteListener(contexto) {
                    if(it.isSuccessful) {
                        Log.d("login", "deu certo")
                        isAcesso = true
                    } else {
                        Log.w("login", it.exception)
                        Toast.makeText(contexto, "Falha no login! Verifique seu e-mail ou senha.", Toast.LENGTH_LONG).show()
                    }
                }

            return isAcesso
        }

        fun desconectar() {
            auth.signOut()
        }

        fun getUsuario(): FirebaseUser? {
            return auth.currentUser
        }
    }
}