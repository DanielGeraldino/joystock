package dospropleys.android.joystock.ui.consultaProduto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.consultar_produto_fragment.view.*

class ConsultarProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = ConsultarProdutoFragment()
    }

    private lateinit var viewModel: ConsultarProdutoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.consultar_produto_fragment, container, false)

        val listaItem = listOf<String>("farofa", "caneta", "corpo", "carteira")
        var adapterItens = ArrayAdapter(root.context, android.R.layout.simple_list_item_1, listaItem)

        root.listagemConsultaItem.adapter = adapterItens

        root.listagemConsultaItem.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(root.context,
                "Item selecionado: " +
                adapterItens.getItem(position),
                Toast.LENGTH_SHORT).show()
        }

        root.consultaProduto.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(root.context, query, Toast.LENGTH_LONG).show()
                    return false;
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapterItens.filter.filter(newText)
                    return false
                }

            }
        )

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConsultarProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}