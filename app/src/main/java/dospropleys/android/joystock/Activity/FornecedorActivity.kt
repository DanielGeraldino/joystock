package dospropleys.android.joystock.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dospropleys.android.joystock.Model.Fornecedor
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.activity_fornecedor.*

class FornecedorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedor)

        var fornecedor = intent.getSerializableExtra("fornecedor") as Fornecedor

        iniciarCampos(fornecedor)

        btnSalvarFornecedor.setOnClickListener {

            salvarFornec(fornecedor)

            finish()
        }

    }

    private fun salvarFornec(f: Fornecedor) {

        f.nome = nomeFornecCadastro.text.toString()
        f.cnpj = cnpjCadastratoFornec.text.toString().toInt()
        f.endereco = enderecoCadastroFornec.text.toString()
        f.numEndereco = numeroCadastroFornec.text.toString().toInt()
        f.bairro = bairroCadastroFornec.text.toString()
        f.cidade = cidadeCadastroFornec.text.toString()
        f.cep = cepCadastroFornec.text.toString().toInt()
        f.complemento = complementoCadastroFornec.text.toString()
        f.uf = ufCadastroFornec.text.toString()
        f.telefone = telefoneCadastroFornec.text.toString().toInt()
        f.email = emailCadastroFornec.text.toString()

        Fornecedor.salvarFornecedor(f, this)

    }

    private fun iniciarCampos(fornecedor: Fornecedor) {
        nomeFornecCadastro.setText(fornecedor.nome)
        cnpjCadastratoFornec.setText(fornecedor.cnpj.toString())
        enderecoCadastroFornec.setText(fornecedor.endereco)
        numeroCadastroFornec.setText(fornecedor.numEndereco.toString())
        bairroCadastroFornec.setText(fornecedor.bairro)
        cidadeCadastroFornec.setText(fornecedor.cidade)
        cepCadastroFornec.setText(fornecedor.cep.toString())
        complementoCadastroFornec.setText(fornecedor.complemento)
        ufCadastroFornec.setText(fornecedor.uf)
        telefoneCadastroFornec.setText(fornecedor.telefone.toString())
        emailCadastroFornec.setText(fornecedor.email)
    }
}