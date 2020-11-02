package dospropleys.android.joystock.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dospropleys.android.joystock.FirebaseHelper.Autenticacao
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.Model.Empresa
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.activity_cadastrar.*

class CadastrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        btnCadastrar.setOnClickListener {
            val login = emailEmpCadastar.text.toString()
            val senha = senhaEmpcadastrar.text.toString()

            if(login != ""){
                if(senha != "") {
                    cadastrar(login, senha)
                } else {
                    Toast.makeText(this, "Favor informar a senha!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Favor informar o E-mail", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun cadastrar(login: String, senha: String) : Boolean{
        return Autenticacao.cadastrarEmail(login, senha, this)
    }

    fun salvarDados() {
        val nome = nomeEmpCadastrar.text.toString()
        val cnpj = cnpjEmpCadastrar.text.toString()
        val email = emailEmpCadastar.text.toString()

        if(nome != "") {
            if(cnpj != "") {
                val emp = Empresa(nome, cnpj, email)
                DataBase.salvarEmpresa(emp, this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(Autenticacao.getUsuario() != null) {
            salvarDados()
        }
    }
}