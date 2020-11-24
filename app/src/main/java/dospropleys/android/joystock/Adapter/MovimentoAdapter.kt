package dospropleys.android.joystock.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dospropleys.android.joystock.Model.Movimento
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.movimento_adapter.view.*

class MovimentoAdapter(private val movimentos: ArrayList<Movimento>, private val context: Context)
    : RecyclerView.Adapter<MovimentoAdapter.MyViewHolder>() {

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup, val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.movimento_adapter, parent, false)) {

        var data = itemView.dataMovimento
        var quantidade = itemView.movimentoQuantidade
        var tipoVenda = itemView.movimentoTipo
        var fornec = itemView.movimentoFornecedor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent, context)
    }

    override fun getItemCount(): Int {
        return movimentos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mov = movimentos[position]

        holder.data.text = mov.data
        holder.quantidade.text = mov.quant.toString()
        holder.fornec.text = mov.nomeFornecedor
        holder.tipoVenda.text = if(mov.tipo == -1) {
            mov.tipoMovimento
        } else {
            context.resources.getTextArray(R.array.lista_tipo_saida)[mov.tipo]
        }

    }
}