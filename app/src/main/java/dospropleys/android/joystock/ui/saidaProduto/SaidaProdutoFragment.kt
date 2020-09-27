package dospropleys.android.joystock.ui.saidaProduto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dospropleys.android.joystock.R

class SaidaProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = SaidaProdutoFragment()
    }

    private lateinit var viewModel: SaidaProdutoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saida_produto_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SaidaProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}