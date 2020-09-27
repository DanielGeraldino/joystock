package dospropleys.android.joystock.ui.entradaProduto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.app_bar_main.view.*

class EntradaProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = EntradaProdutoFragment()
    }

    private lateinit var viewModel: EntradaProdutoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.entrada_produto_fragment, container, false)

        return root
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EntradaProdutoViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}