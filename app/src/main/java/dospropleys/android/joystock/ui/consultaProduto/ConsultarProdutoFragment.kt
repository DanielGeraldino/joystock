package dospropleys.android.joystock.ui.consultaProduto

import android.app.SearchManager
import android.content.Context
import android.content.Context.SEARCH_SERVICE
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.consultar_produto_fragment.*
import kotlinx.android.synthetic.main.consultar_produto_fragment.view.*
import java.util.*

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

        root.consultaProduto.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
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