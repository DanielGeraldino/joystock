package dospropleys.android.joystock.ui.cadastrarProduto

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.Model.Produto
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.cadastrar_produto_fragment.view.*

class CadastrarProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = CadastrarProdutoFragment()
    }

    private val banco = DataBase
    private var tipoProduto: Int = 0
    private var unidade: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.cadastrar_produto_fragment, container, false)

        ArrayAdapter.createFromResource(
            root.context,
            R.array.lista_unidades,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                root.spinnerUnidades.adapter = adapter }

        root.spinnerUnidades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                unidade = position
                Log.e("unidade", unidade.toString())
            }

        }

        ArrayAdapter.createFromResource(
            root.context,
            R.array.lista_tipos,
            android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            root.spinnerTipos.adapter = adapter }

        root.spinnerTipos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoProduto = position
                Log.e("tipo selec", tipoProduto.toString())
            }

        }

        root.btnCadastrarProduto.setOnClickListener {
            val produto = getProduto(root)

            produto?.let {
                banco.gravarProduto(it, root.context)
                limparCampos(root)
            }
        }

        return root
    }

    fun validarCampos(codBarra: String, descri: String, tipo: Int, unidade: Int, valor: String, context: Context) : Boolean {

        if(codBarra == "" || codBarra == null) {
            Toast.makeText(context, "Favor informar o codigo de barra!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(descri == "" || descri == null) {
            Toast.makeText(context, "Favor informar o descrição do produto!", Toast.LENGTH_SHORT).show()
            return false
        }
        if(valor == "" || valor == null || valor.toFloat() <= 0) {
            Toast.makeText(context, "Favor informar um valor valido!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun limparCampos(root: View) {
        root.nomeCadastroProduto.text = null
        root.codBarraProduto.text = null
        root.precoProduto.text = null
    }

    fun getProduto(root: View): Produto? {
        var descri = root.nomeCadastroProduto.text.toString()
        var tipo = this.tipoProduto
        var unidade = this.unidade
        var codBarra = root.codBarraProduto.text.toString()
        var valorText = root.precoProduto.text.toString()

        if(validarCampos(codBarra, descri, tipo, unidade, valorText, root.context)) {
            return Produto(codBarra, descri, tipo, unidade, valorText.toFloat())
        }

        return null
    }
}