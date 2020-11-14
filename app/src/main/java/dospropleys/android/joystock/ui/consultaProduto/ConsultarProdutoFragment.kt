package dospropleys.android.joystock.ui.consultaProduto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import dospropleys.android.joystock.Adapter.ProdutosAdapter
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.Model.Produto
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.consultar_produto_fragment.view.*

class ConsultarProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = ConsultarProdutoFragment()
    }

    //private lateinit var viewModel: ConsultarProdutoViewModel
    private val banco = DataBase
    private lateinit var listaProduto: ArrayList<Produto>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.consultar_produto_fragment, container, false)
        atualizarLista()
        val adapterItens = ProdutosAdapter(root.context, listaProduto)
        Log.e("tam lista", listaProduto.size.toString())
        root.listagemConsultaItem.adapter = adapterItens

        root.listagemConsultaItem.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(root.context,
                "Item selecionado: " +
                adapterItens.getItem(position),
                Toast.LENGTH_SHORT).show()
        }

        if(listaProduto.size == 0) {
            atualizarLista()
            adapterItens.insertItens(listaProduto)
        }

        root.consultaProduto.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(root.context, query, Toast.LENGTH_LONG).show()

                    return false;
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapterItens.getFilter().filter(newText)
                    return false
                }

            }
        )

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        atualizarLista()
    }

    fun atualizarLista() {
        banco.consultaProdutos()
        listaProduto = banco.getProdutos()
    }

}