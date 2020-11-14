package dospropleys.android.joystock.Model

import dospropleys.android.joystock.FirebaseHelper.DataBase

class Produto {

    var codigoBarra: String
    var descricao: String
    var tipoProduto: Int
    var unidadeEstoque: Int
    var valorVenda: Float
    var saldo: Float
    var id: String

    constructor() {
        this.codigoBarra = ""
        this.descricao = ""
        this.tipoProduto = -1
        this.unidadeEstoque = -1
        this.valorVenda = (-1.0).toFloat()
        this.saldo = 0f
        this.id = ""
    }

    constructor(codigoBarra: String, descricao: String, tipo: Int, unidade: Int, valor: Float) {
        this.codigoBarra = codigoBarra
        this.descricao = descricao
        this.tipoProduto = tipo
        this.unidadeEstoque = unidade
        this.valorVenda = valor
        this.saldo = 0f
        this.id = ""
    }

    fun saida(quant: Float) : Boolean{
        val novoSaldo = saldo - quant
        if(id != "") {
            if (novoSaldo >= 0) {
                DataBase.saidaProduto(id, saldo - quant)
                return true
            }
        }
        return false
    }
}