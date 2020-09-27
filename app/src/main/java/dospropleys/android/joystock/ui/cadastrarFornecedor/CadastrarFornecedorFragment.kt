package dospropleys.android.joystock.ui.cadastrarFornecedor

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dospropleys.android.joystock.R

class CadastrarFornecedorFragment : Fragment() {

    companion object {
        fun newInstance() = CadastrarFornecedorFragment()
    }

    private lateinit var viewModel: CadastrarFornecedorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadastrar_fornecedor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CadastrarFornecedorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}