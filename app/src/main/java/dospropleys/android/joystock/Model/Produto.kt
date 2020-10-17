package dospropleys.android.joystock.Model

class Produto {

    var codigoBarra: String
    var descricao: String
    var tipoProduto: Int
    var unidadeEstoque: Int
    var valorVenda: Float

    constructor() {
        this.codigoBarra = ""
        this.descricao = ""
        this.tipoProduto = -1
        this.unidadeEstoque = -1
        this.valorVenda = (-1.0).toFloat()
    }

    constructor(codigoBarra: String, descricao: String, tipo: Int, unidade: Int, valor: Float) {
        this.codigoBarra = codigoBarra
        this.descricao = descricao
        this.tipoProduto = tipo
        this.unidadeEstoque = unidade
        this.valorVenda = valor
    }
}