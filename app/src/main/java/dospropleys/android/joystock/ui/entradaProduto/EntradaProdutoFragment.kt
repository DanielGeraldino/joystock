package dospropleys.android.joystock.ui.entradaProduto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dospropleys.android.joystock.Adapter.FornecedorAdpater
import dospropleys.android.joystock.Adapter.ProdutosAdapter
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.entrada_produto_fragment.view.*

class EntradaProdutoFragment : Fragment() {

    companion object {
        fun newInstance() = EntradaProdutoFragment()
    }

    private var produtos = DataBase.getProdutos()
    private var fornecedores = DataBase.getForncedores()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.entrada_produto_fragment, container, false)

        val adapterProduto = ProdutosAdapter(root.context, produtos, root.autoNomeItemEntrada)
        root.autoNomeItemEntrada.setAdapter(adapterProduto)

        val adapterFornecedor = FornecedorAdpater(root.context, fornecedores, root.autoNomeFornecEntrada)
        root.autoNomeFornecEntrada.setAdapter(adapterFornecedor)

        root.btnFechamentoEntrada.setOnClickListener {
            val fornec = adapterFornecedor.getItemFiltrado()
            val produto = adapterProduto.getItemFiltrado()

            val textQuantidade = root.quantidadeEntrada.text.toString()
            val quantidade = textQuantidade.toFloat()

            if(produto.entrada(quantidade)) {
                DataBase.gravarMovimento(
                    root.context,
                    produto.id,
                    produto.descricao,
                    fornec.nome,
                    fornec.cnpj,
                    root.dataNFEntrada.text.toString(),
                    root.editNumNFEntrada.text.toString(),
                    -1,
                    quantidade,
                    true
                )

                limparCampos(root)
            }

        }

        return root
    }

    fun limparCampos(root: View) {
        root.autoNomeItemEntrada.setText("")
        root.autoNomeFornecEntrada.setText("")
        root.editNumNFEntrada.setText("")
        root.dataNFEntrada.setText("")
        root.quantidadeEntrada.setText("")
    }

}