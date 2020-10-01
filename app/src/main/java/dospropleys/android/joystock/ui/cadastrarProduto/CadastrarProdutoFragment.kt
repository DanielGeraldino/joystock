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
import com.google.android.gms.common.internal.Objects
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.cadastrar_produto_fragment.view.*

class CadastrarProdutoFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = CadastrarProdutoFragment()
    }

    private lateinit var viewModel: CadastrarProdutoViewModel

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

        root.spinnerUnidades.onItemSelectedListener = this

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CadastrarProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val teste = parent?.getItemAtPosition(position).toString()
        Toast.makeText(activity, teste, Toast.LENGTH_LONG).show()
        Log.d("spinner", teste)
    }

}