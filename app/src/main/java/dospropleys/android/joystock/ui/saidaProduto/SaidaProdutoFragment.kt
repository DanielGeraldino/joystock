package dospropleys.android.joystock.ui.saidaProduto

import android.app.SearchManager
import android.database.MatrixCursor
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.BaseColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dospropleys.android.joystock.Adapter.ProdutosAdapter
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.Model.Movimento
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.saida_produto_fragment.*
import kotlinx.android.synthetic.main.saida_produto_fragment.view.*

class SaidaProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = SaidaProdutoFragment()
    }

    private lateinit var viewModel: SaidaProdutoViewModel
    private var tipoSaida = 0
    private var produtos = DataBase.getProdutos()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.saida_produto_fragment, container, false)

        ArrayAdapter.createFromResource(
            root.context,
            R.array.lista_tipo_saida,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            root.spinnerSaidaProduto.adapter = adapter
        }

        root.spinnerSaidaProduto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoSaida = position
            }

        }

        // Pesquisa
        val adapter = ProdutosAdapter(root.context, produtos, root.autoPesqSaida)

        root.autoPesqSaida.setAdapter(adapter)

        root.btnFinalizarSaida.setOnClickListener {
            val produto = adapter.getItemFiltrado()
            var textQuant= quantSaida.text.toString();
            val quant = textQuant.toFloat()

            DataBase.gravarMovimento(
                root.context,
                produto.id,
                produto.descricao,
                dataSaida.text.toString(),
                obsSaida.text.toString(),
                tipoSaida,
                quant,
                false
            )

            limparCampos()
        }

        return root
    }

    private fun limparCampos() {
        autoPesqSaida.setText("")
        dataSaida.setText("")
        obsSaida.setText("")
        quantSaida.setText("")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SaidaProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}