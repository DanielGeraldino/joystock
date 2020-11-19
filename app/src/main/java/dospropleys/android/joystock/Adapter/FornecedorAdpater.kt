package dospropleys.android.joystock.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dospropleys.android.joystock.Activity.FornecedorActivity
import dospropleys.android.joystock.Model.Fornecedor
import dospropleys.android.joystock.Model.Produto
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.produto_item_adapter.view.*

class FornecedorAdpater() : BaseAdapter(), Filterable {

    lateinit var listaInicial: ArrayList<Fornecedor>
    lateinit var context: Context
    lateinit var lista: ArrayList<Fornecedor>
    var textComplete: AutoCompleteTextView? = null

    constructor(c: Context, listaFornec: ArrayList<Fornecedor>) : this() {
        listaInicial = listaFornec
        lista = listaFornec
        context = c

    }

    constructor(c: Context, listaFornec: ArrayList<Fornecedor>, autoCompleteTextView: AutoCompleteTextView) : this() {
        this.context = c
        this.lista = listaFornec
        this.listaInicial = listaFornec
        this.textComplete = autoCompleteTextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val fornecedor = this.lista.get(position)

        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflater.inflate(R.layout.produto_item_adapter, null)


        layout.setOnClickListener {

            Toast.makeText(context, fornecedor.nome, Toast.LENGTH_SHORT).show()
            if(textComplete != null) {
                textComplete?.let {
                    it.setText(fornecedor.nome)
                    if(it.text.length > 2) {
                        it.setSelection(it.text.length)
                        it.dismissDropDown()
                    } else {
                        it.showDropDown()
                    }
                }
            } else {
                Log.d("idFornec", fornecedor.idFornec)
                abrirTelaForncedor(fornecedor)
            }
        }

        layout.nomeItem.text = fornecedor.nome

        return layout

    }

    fun abrirTelaForncedor(f: Fornecedor) {
        var intent = Intent(context, FornecedorActivity::class.java)
        intent.putExtra("fornecedor", f)
        context.startActivity(intent)
    }


    override fun getItem(position: Int): Any {
        return lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return lista.get(position).cnpj.toLong()
    }

    override fun getCount(): Int {
        return lista.size
    }

    fun insertItens(lista: ArrayList<Fornecedor>) {
        lista.forEach {
            this.lista.add(it)
        }
        this.notifyDataSetChanged()
    }

    fun getItemFiltrado() : Fornecedor {
        return lista[0]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()

                filterResults.values = if (queryString==null || queryString.isEmpty())
                    listaInicial
                else
                    lista.filter {
                        it.nome.toLowerCase().contains(queryString) ||
                                it.cnpj.toString().contains(queryString)
                    }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null) {
                    lista = results.values as ArrayList<Fornecedor>
                }
                notifyDataSetChanged()
            }

        }

    }
}