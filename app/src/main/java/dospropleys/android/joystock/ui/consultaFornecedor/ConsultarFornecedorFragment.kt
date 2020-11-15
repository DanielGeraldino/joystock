package dospropleys.android.joystock.ui.consultaFornecedor

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import dospropleys.android.joystock.Adapter.FornecedorAdpater
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.consultar_fornecedor_fragment.*
import kotlinx.android.synthetic.main.consultar_fornecedor_fragment.view.*

class ConsultarFornecedorFragment : Fragment() {

    companion object {
        fun newInstance() = ConsultarFornecedorFragment()
    }

    var fornecedores = DataBase.getForncedores()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.consultar_fornecedor_fragment, container, false)

        val adapter = FornecedorAdpater(root.context, fornecedores)

        root.listagemConsultaFornec.adapter = adapter

        root.listagemConsultaFornec.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(root.context, adapter.getItem(position).toString() , Toast.LENGTH_SHORT).show()
        }

        root.consultaFornecedor.setOnQueryTextListener( object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                return false
            }

        })

        return root
    }

}