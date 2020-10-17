package dospropleys.android.joystock.ui.cadastrarProduto

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

    private lateinit var viewModel: CadastrarProdutoViewModel
    private val banco = DataBase
    private var tipoProduto: Int = -1
    private var unidade: Int = -1

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
                tipoProduto = position
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
                unidade = position
            }

        }

        root.btnCadastrarProduto.setOnClickListener {
            if(validarCampos()) {
                banco.gravarProduto(getProduto(root), root.context)
                limparCampos(root)
            }
        }

        return root
    }

    fun validarCampos() : Boolean {
        return true
    }

    fun limparCampos(root: View) {
        root.nomeCadastroProduto.text = null
        root.codBarraProduto.text = null
        root.precoProduto.text = null
    }

    fun getProduto(root: View): Produto {
        var descri = root.nomeCadastroProduto.text.toString()
        var tipo = this.tipoProduto
        var unidade = this.unidade
        var codBarra = root.codBarraProduto.text.toString()
        var valorText = root.precoProduto.text.toString()

        return Produto(codBarra, descri, tipo, unidade, valorText.toFloat())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CadastrarProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }
}