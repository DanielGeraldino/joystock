package dospropleys.android.joystock.FirebaseHelper

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dospropleys.android.joystock.Model.Fornecedor
import dospropleys.android.joystock.Model.Produto

class DataBase {

    companion object {
        private val data = Firebase.database.getReference()
        private val userId = Autenticacao.getIdUsuario()
        private var produtos: ArrayList<Produto> = ArrayList()
        private var fornecedores: ArrayList<Fornecedor> = ArrayList()

        //EVENTOS
        private lateinit var eventConsultaProduto: ValueEventListener
        private lateinit var eventConsultaFornecedor: ValueEventListener

        fun getDataBase(): DatabaseReference {
            return data.child(userId.toString())
        }

        // METODOS PARA MANIPULAÇÃO DO PRODUTO
        fun gravarProduto(p: Produto, context: Context) {
            var consultaP = consultaProduto(p.codigoBarra)
            if(consultaP == null) {
                getDataBase().child("produtos").push().setValue(p)
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

        fun consultaProdutos() {

            val eventoProduto = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val listaProdutos: ArrayList<Produto> = ArrayList()

                    for(produto in snapshot.children) {
                        produto.getValue<Produto>()?.let { listaProdutos.add(it) }
                    }

                    produtos = listaProdutos
                }

            }

            getDataBase().child("produtos").addValueEventListener(eventoProduto)

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
                            listaFornec.add(it)
                        }

                        fornecedores = listaFornec
                    }
                }
            }

            getDataBase().child("fornecedores").addValueEventListener(eventoCosulta)
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

                getDataBase().child("fornecedores").push().setValue(fornec)
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


        fun desconectBaseDados() {
            getDataBase().removeEventListener(eventConsultaProduto)
            getDataBase().removeEventListener(eventConsultaFornecedor)
        }
    }
}