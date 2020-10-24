package dospropleys.android.joystock.FirebaseHelper

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dospropleys.android.joystock.Model.Produto
import kotlin.math.log

class DataBase {

    companion object {
        private val data = Firebase.database.getReference()
        private val userId = Autenticacao.getIdUsuario()
        private lateinit var produtos: ArrayList<Produto>

        //EVENTOS
        private lateinit var eventoProdutos: ValueEventListener

        fun getDataBase(): DatabaseReference {
            return data.child(userId.toString())
        }

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

        fun desconectBaseDados() {
            getDataBase().removeEventListener(eventoProdutos)
        }
    }
}