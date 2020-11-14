package dospropleys.android.joystock.Model

import android.provider.ContactsContract
import dospropleys.android.joystock.Helper.DataHelper

class Movimento {
    var idItem: String
    var itemDescri: String
    var data: String
    var observacao: String
    var tipo: Int
    var dataRegistro: String
    var quant: Float
    var tipoMovimento: String

    constructor(keyItem: String, nomeItem: String, data: String, obs: String, tp: Int, quantidade: Float, tipoMov: Boolean) {
        this.idItem = keyItem
        this.itemDescri = nomeItem
        this.data = data
        this.observacao = obs
        this.tipo = tp
        this.quant = quantidade
        this.dataRegistro = DataHelper.getDataAtual().toString()

        tipoMovimento = if(tipoMov) {
            "Entrada"
        } else {
            "Saida"
        }

    }

}