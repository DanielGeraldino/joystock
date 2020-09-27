package dospropleys.android.joystock.ui.cadastrarProduto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dospropleys.android.joystock.R

class CadastrarProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = CadastrarProdutoFragment()
    }

    private lateinit var viewModel: CadastrarProdutoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadastrar_produto_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CadastrarProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}