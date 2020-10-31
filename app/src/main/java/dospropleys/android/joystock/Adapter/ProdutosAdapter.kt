package dospropleys.android.joystock.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Toast
import dospropleys.android.joystock.Model.Produto
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.produto_item_adapter.view.*


class ProdutosAdapter() : BaseAdapter() {

    lateinit var listaInicial: ArrayList<Produto>
    lateinit var context: Context
    lateinit var lista: ArrayList<Produto>

    constructor(c: Context, lista: ArrayList<Produto>) : this() {
        this.context = c
        this.lista = lista
        this.listaInicial = lista
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val produto = this.lista.get(position)

        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflater.inflate(R.layout.produto_item_adapter, null)

        layout.setOnClickListener {
            Toast.makeText(context, produto.descricao, Toast.LENGTH_SHORT).show()
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

    fun filtro(): Filter {
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