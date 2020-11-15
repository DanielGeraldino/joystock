package dospropleys.android.joystock.Model

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
    var nomeFornecedor: String = ""
    var cnpj: Int = -1
    var nfNumero = ""

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

    constructor(keyItem: String, nomeItem: String, data: String, obs: String, tp: Int, quantidade: Float, tipoMov: Boolean, nomefornec: String, cnpj: Int, nf: String) {
        this.idItem = keyItem
        this.itemDescri = nomeItem
        this.data = data
        this.observacao = obs
        this.tipo = tp
        this.quant = quantidade
        this.dataRegistro = DataHelper.getDataAtual().toString()
        this.nomeFornecedor = nomefornec
        this.cnpj = cnpj
        this.nfNumero = nf

        tipoMovimento = if(tipoMov) {
            "Entrada"
        } else {
            "Saida"
        }
    }

}