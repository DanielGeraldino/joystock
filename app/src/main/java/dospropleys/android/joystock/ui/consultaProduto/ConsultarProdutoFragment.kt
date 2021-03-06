package dospropleys.android.joystock.ui.consultaProduto

import android.os.Bundle
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
    private val banco = DataBase
    private var listaProduto = ArrayList<Produto>()
    private lateinit var adapter: ProdutosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listaProduto = banco.getProdutos()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.consultar_produto_fragment, container, false)


        adapter = ProdutosAdapter(root.context, listaProduto)

        root.listagemConsultaItem.adapter = adapter

        root.consultaProduto.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(root.context, query, Toast.LENGTH_LONG).show()

                    return false;
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.getFilter().filter(newText)
                    return false
                }

            }
        )

        atualizarLista()

        return root
    }

    fun atualizarLista() {
        banco.consultaProdutos()
        listaProduto = banco.getProdutos()
        adapter.lista = listaProduto
        adapter.notifyDataSetChanged()
    }

}