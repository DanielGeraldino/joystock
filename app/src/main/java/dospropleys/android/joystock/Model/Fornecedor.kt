package dospropleys.android.joystock.Model

class Fornecedor {

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
    }
}