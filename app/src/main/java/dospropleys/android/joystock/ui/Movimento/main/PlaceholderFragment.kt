package dospropleys.android.joystock.ui.Movimento.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dospropleys.android.joystock.Adapter.MovimentoAdapter
import dospropleys.android.joystock.FirebaseHelper.DataBase
import dospropleys.android.joystock.Model.Movimento
import dospropleys.android.joystock.R
import kotlinx.android.synthetic.main.fragment_movimento.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private var entradas = ArrayList<Movimento>()
    private var saidas = ArrayList<Movimento>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var idItem = activity?.intent?.getStringExtra("id_produto")

        DataBase.getMovimento(idItem).forEach {
            if(it.tipoMovimento == "Saida") {
                Log.e("mov saida", it.idItem)
                saidas.add(it)
            } else if(it.tipoMovimento == "Entrada") {
                Log.e("mov entrada", entradas.size.toString())
                entradas.add(it)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movimento, container, false)

        if(arguments?.getInt(ARG_SECTION_NUMBER) == 1) {
            root.recyclerMovimento.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(root.context)
                adapter = MovimentoAdapter(entradas, root.context)
            }
        } else {
            root.recyclerMovimento.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(root.context)
                adapter = MovimentoAdapter(saidas, root.context)
            }
        }

        return root
    }

    override fun onStop() {
        super.onStop()
        saidas.clear()
        entradas.clear()
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}