package dospropleys.android.joystock.Model

class Empresa {

    var nome: String
    var cnpj: String
    var email: String

    constructor(nome: String, cnpj: String, email: String) {
        this.nome = nome
        this.cnpj = cnpj
        this.email = email
    }
}