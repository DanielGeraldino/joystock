package dospropleys.android.joystock.ui.consultaFornecedor

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dospropleys.android.joystock.R

class ConsultarFornecedorFragment : Fragment() {

    companion object {
        fun newInstance() = ConsultarFornecedorFragment()
    }

    private lateinit var viewModel: ConsultarFornecedorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.consultar_fornecedor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConsultarFornecedorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}