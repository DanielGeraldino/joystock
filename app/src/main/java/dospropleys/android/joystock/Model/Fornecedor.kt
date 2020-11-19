package dospropleys.android.joystock.Model

import android.content.Context
import dospropleys.android.joystock.FirebaseHelper.DataBase
import java.io.Serializable

class Fornecedor : Serializable {

    companion object {

        fun salvarFornecedor(fornecedor: Fornecedor, context: Context) {
            DataBase.salvarAlteracao(fornecedor, context)
        }

    }

    var nome: String
    var cnpj: Int
    var endereco: String
    var numEndereco: Int
    var bairro: String
    var cidade: String
    var cep: Int
    var complemento: String
    var uf: String
    var telefone: Int
    var email: String
    var idFornec: String

    constructor(nome: String, cnpj: Int, endereco: String, numero: Int, bairro: String, cidade: String, cep: Int, complemento: String, uf: String, telefone: Int, email: String) {
        this.nome = nome
        this.cnpj = cnpj
        this.endereco = endereco
        this.numEndereco = numero
        this.bairro = bairro
        this.cidade = cidade
        this.complemento = complemento
        this.cep = cep
        this.uf = uf
        this.telefone = telefone
        this.email = email
        this.idFornec = ""
    }

    constructor() {
        this.nome = ""
        this.cnpj = -1
        this.endereco = ""
        this.numEndereco = -1
        this.bairro = ""
        this.cidade = ""
        this.complemento = ""
        this.cep = -1
        this.uf = ""
        this.telefone = -1
        this.email = ""
        this.idFornec = ""
    }
}