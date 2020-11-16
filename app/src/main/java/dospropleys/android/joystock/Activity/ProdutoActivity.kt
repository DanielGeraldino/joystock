package dospropleys.android.joystock.Activity

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import dospropleys.android.joystock.Model.Produto
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.activity_produto.*
import kotlinx.android.synthetic.main.cadastrar_produto_fragment.view.*

class ProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        var produto = intent.getSerializableExtra("produto") as Produto
        var tipoUnidadePos = produto.unidadeEstoque
        var tipoProdutoPos = produto.tipoProduto

        nomeCadastroProduto.setText(produto.descricao)
        codBarraProduto.setText(produto.codigoBarra)
        precoProduto.setText(produto.valorVenda.toString())
        textSaldo.text = produto.saldo.toString()

        //SpinnerUnidades
        ArrayAdapter.createFromResource(
            this,
            R.array.lista_unidades,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidades.adapter = adapter }

        spinnerUnidades.setSelection(tipoUnidadePos)

        spinnerUnidades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoUnidadePos = position
            }

        }

        //SpinnerTipo
        ArrayAdapter.createFromResource(
            this,
            R.array.lista_tipos,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerTipos.adapter = adapter }

        spinnerTipos.setSelection(tipoProdutoPos)

        spinnerTipos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoProdutoPos = position
            }

        }

        btnCadastrarProduto.setOnClickListener {
            produto.descricao = nomeCadastroProduto.text.toString()
            produto.tipoProduto = tipoProdutoPos
            produto.unidadeEstoque = tipoUnidadePos
            produto.codigoBarra = codBarraProduto.text.toString()
            produto.valorVenda = precoProduto.text.toString().toFloat()

            Produto.salvarAlteração(produto, this)

            finish()
        }

    }
}