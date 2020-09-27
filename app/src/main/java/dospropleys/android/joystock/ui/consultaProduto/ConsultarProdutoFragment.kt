package dospropleys.android.joystock.ui.consultaProduto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dospropleys.android.joystock.R

class ConsultarProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = ConsultarProdutoFragment()
    }

    private lateinit var viewModel: ConsultarProdutoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.consultar_produto_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConsultarProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}