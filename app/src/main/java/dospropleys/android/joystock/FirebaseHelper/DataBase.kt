package dospropleys.android.joystock.FirebaseHelper

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dospropleys.android.joystock.Model.Empresa
import dospropleys.android.joystock.Model.Fornecedor
import dospropleys.android.joystock.Model.Movimento
import dospropleys.android.joystock.Model.Produto

class DataBase {

    companion object {
        private val data = Firebase.database.getReference()
        private val userId = Autenticacao.getIdUsuario()
        private var produtos: ArrayList<Produto> = ArrayList()
        private var fornecedores: ArrayList<Fornecedor> = ArrayList()

        // NODES
        val PRODUTOS = "produtos"
        val MOVIMENTOS = "movimentos"
        val DADOS_EMPRESA = "dados"
        val FORNECEDORES = "fornecedores"

        //EVENTOS
        private lateinit var eventConsultaProduto: ValueEventListener
        private lateinit var eventConsultaFornecedor: ValueEventListener

        fun getDataBase(): DatabaseReference {
            return data.child(userId.toString())
        }

        fun salvarEmpresa(emp: Empresa, context: Context) {
            getDataBase().child(DADOS_EMPRESA).setValue(emp)
                .addOnSuccessListener {
                    Log.e("salvar empresa", "sucesso")
                }.addOnFailureListener {
                    Log.d("salvar empresa", it.message)
                }
        }

        // METODOS PARA MANIPULAÇÃO DO PRODUTO
        fun gravarProduto(p: Produto, context: Context) {
            var consultaP = consultaProduto(p.codigoBarra)
            if(consultaP == null) {
                getDataBase().child(PRODUTOS).push().setValue(p)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Produto cadastrado", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        Toast.makeText(context, "Falha no cadastro do produto. Tente novamente mais tarde!", Toast.LENGTH_LONG).show()
                        Log.d("gravar produto", "falha")
                    }
            } else {
                Log.d("gravar produto", "produto " + p.codigoBarra + " ja existe.")
            }
        }

        fun gravarSaldo(idProduto: String, novoSaldo: Float) {
            Log.e("saida", idProduto)
            getDataBase().child(PRODUTOS).child(idProduto).child("saldo").setValue(novoSaldo)
                .addOnSuccessListener {
                    Log.e("saldo editado", true.toString())
                }
                .addOnFailureListener {
                    Log.d("saldo editado", it.toString() )
                }
        }

        fun consultaProdutos() {

            val eventoProduto = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val listaProdutos: ArrayList<Produto> = ArrayList()

                    for(produto in snapshot.children) {
                        produto.getValue<Produto>()?.let {
                            it.id = produto.key.toString()
                            listaProdutos.add(it)
                        }
                    }

                    produtos = listaProdutos
                }

            }

            getDataBase().child(PRODUTOS).addValueEventListener(eventoProduto)

        }

        fun consultaProduto(codigo: String) : Produto? {
            if(produtos == null) { consultaProdutos() }

            produtos.forEach {
                if(it.codigoBarra == codigo) {
                    return it
                }
            }
            return null
        }

        fun getProdutos() : ArrayList<Produto>{
            return produtos
        }

        // METODOS PARA MANILUÇÃO DO MOVIMENTO
        fun gravarMovimento(context: Context ,idItem: String, nomeItem: String, dataMov: String, obs: String, tipo: Int, quantidade: Float, tipoMovimento: Boolean) {
            getDataBase().child(MOVIMENTOS).push().setValue(
                Movimento(idItem, nomeItem,dataMov, obs, tipo, quantidade, tipoMovimento)
            ).addOnSuccessListener {
                Toast.makeText(context, "Movimento gravado com sucesso!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "falha ao grava o movimento!", Toast.LENGTH_SHORT).show()
                Log.d("grava movimento", it.toString())
            }
        }

        fun gravarMovimento(context: Context,
            idItem: String, nomeItem:String, nomeFornec: String, cnpj: Int, dataMov: String, nf: String, tipo: Int, quantidade: Float, entrada: Boolean) {
            getDataBase().child(MOVIMENTOS).push().setValue(
                Movimento(idItem, nomeItem,dataMov, "", tipo, quantidade, entrada, nomeFornec, cnpj, nf)
            ).addOnSuccessListener {
                Toast.makeText(context, "Movimento gravado com sucesso!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "falha ao grava o movimento!", Toast.LENGTH_SHORT).show()
                Log.d("grava movimento", it.toString())
            }
        }

        //METODOS PARA MANIPULAÇÃO DO FORNECEDOR
        fun consultarFornecedores() {
            val eventoCosulta = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var listaFornec: ArrayList<Fornecedor> = ArrayList()

                    for(fornec in snapshot.children) {
                        fornec.getValue<Fornecedor>()?.let {
                            it.idFornec = fornec.key.toString()
                            listaFornec.add(it)
                        }

                        fornecedores = listaFornec
                    }
                }
            }

            getDataBase().child(FORNECEDORES).addValueEventListener(eventoCosulta)
        }

        fun consultaFornecedor(cnpj: Int) : Fornecedor? {
            if(fornecedores == null) { consultarFornecedores() }

            fornecedores.forEach {
                if(it.cnpj == cnpj) {
                    return it
                }
            }
            return null
        }

        fun gravarFornecedor(fornec: Fornecedor, context: Context) {
            val fExiste = consultaFornecedor(fornec.cnpj)

            if(fExiste == null) {

                getDataBase().child(FORNECEDORES).push().setValue(fornec)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Fornecedor cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Falha ao cadastrar o fornecedor!", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Fornecedor com mesmo cnpj já exite!", Toast.LENGTH_SHORT).show()
            }
        }

        fun getForncedores() : ArrayList<Fornecedor>{
            return fornecedores
        }


        fun desconectBaseDados() {
            getDataBase().removeEventListener(eventConsultaProduto)
            getDataBase().removeEventListener(eventConsultaFornecedor)
        }
    }
}