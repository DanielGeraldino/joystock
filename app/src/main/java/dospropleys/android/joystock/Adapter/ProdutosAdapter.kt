package dospropleys.android.joystock.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dospropleys.android.joystock.Model.Produto
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.produto_item_adapter.view.*


class ProdutosAdapter() : BaseAdapter(), Filterable {

    lateinit var listaInicial: ArrayList<Produto>
    lateinit var context: Context
    lateinit var lista: ArrayList<Produto>
    var textComplete: AutoCompleteTextView? = null

    constructor(c: Context, lista: ArrayList<Produto>) : this() {
        this.context = c
        this.lista = lista
        this.listaInicial = lista
    }

    constructor(c: Context, lista: ArrayList<Produto>, autoCompleteTextView: AutoCompleteTextView) : this() {
        this.context = c
        this.lista = lista
        this.listaInicial = lista
        this.textComplete = autoCompleteTextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val produto = this.lista.get(position)

        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflater.inflate(R.layout.produto_item_adapter, null)


        layout.setOnClickListener {

            Toast.makeText(context, produto.descricao, Toast.LENGTH_SHORT).show()
            textComplete?.let {
                it.setText(produto.descricao)
                if(it.text.length > 2) {
                    it.setSelection(it.text.length)
                    it.dismissDropDown()
                } else {
                    it.showDropDown()
                }
            }
        }

        layout.nomeItem.text = produto.descricao

        return layout

    }

    override fun getItem(position: Int): Any {
        return lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return lista.get(position).codigoBarra.toLong()
    }

    override fun getCount(): Int {
        return lista.size
    }

    fun insertItens(lista: ArrayList<Produto>) {
        lista.forEach {
            this.lista.add(it)
        }
        this.notifyDataSetChanged()
    }

    fun getItemFiltrado() : Produto {
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
                        it.descricao.toLowerCase().contains(queryString) ||
                                it.codigoBarra.toLowerCase().contains(queryString)
                    }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null) {
                    lista = results.values as ArrayList<Produto>
                }
                notifyDataSetChanged()
            }

        }

    }
}