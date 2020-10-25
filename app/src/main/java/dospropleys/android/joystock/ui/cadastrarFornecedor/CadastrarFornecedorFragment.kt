package dospropleys.android.joystock.ui.cadastrarFornecedor

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.Model.Fornecedor
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.cadastrar_fornecedor_fragment.*
import kotlinx.android.synthetic.main.cadastrar_fornecedor_fragment.view.*

class CadastrarFornecedorFragment : Fragment() {

    companion object {
        fun newInstance() = CadastrarFornecedorFragment()
    }

    private lateinit var viewModel: CadastrarFornecedorViewModel
    private val banco = DataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.cadastrar_fornecedor_fragment, container, false)

        root.btnSalvarFornecedor.setOnClickListener {
            val fornec = getFornecedor(root)

            fornec?.let {
                //TODO: Salvar fornecedor
            }
        }

        return root
    }

    private fun validarCampos(nome: String, cnpj: String, endereco: String, numero: String,
    bairro: String, cidade: String, cep: String, uf: String, telefone: String, email: String, context: Context) : Boolean {

        if(nome == "") {
            Toast.makeText(context, "Favor informar o nome do fornecedor!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(cnpj == "" || cnpj.toInt() <= 0) {
            Toast.makeText(context, "Favor informar cnpj valido!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(endereco == "") {
            Toast.makeText(context, "Favor informar endereco!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(numero == "" || numero.toInt() <= 0) {
            Toast.makeText(context, "Favor informar numero do endereco!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(bairro == "") {
            Toast.makeText(context, "Favor informar o bairro!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(cidade == "") {
            Toast.makeText(context, "Favor informar a cidade!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(cep == "" || cep.toInt() <= 0) {
            Toast.makeText(context, "Favor informar o cep!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(uf == "") {
            Toast.makeText(context, "Favor informar o UF!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(telefone == "" || telefone.toInt() <= 0){
            Toast.makeText(context, "Favor informar o telefone!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(email == "") {
            Toast.makeText(context, "Favor informar o nome do fornecedor", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun getFornecedor(root: View): Fornecedor? {
        var nome = root.nomeFornecCadastro.text.toString()
        var cnpj = root.cnpjCadastratoFornec.text.toString()
        var endereco = root.enderecoCadastroFornec.text.toString()
        var numero = root.numeroCadastroFornec.text.toString()
        var bairro = root.bairroCadastroFornec.text.toString()
        var cidade = root.cidadeCadastroFornec.text.toString()
        var complemento = root.complementoCadastroFornec.text.toString()
        var cep = cepCadastroFornec.text.toString()
        var uf = ufCadastroFornec.text.toString()
        var telefone = telefoneCadastroFornec.text.toString()
        var email = emailCadastroFornec.text.toString()

        if(validarCampos(nome, cnpj, endereco, numero, bairro, cidade, cep, uf, telefone, email, root.context)) {
            return Fornecedor(nome, cnpj.toInt(), endereco, numero.toInt(),
                bairro, cidade, cep.toInt(), complemento, uf, telefone.toInt(), email)
        }

        return null

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CadastrarFornecedorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}