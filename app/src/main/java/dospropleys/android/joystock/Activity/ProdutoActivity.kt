package dospropleys.android.joystock.Activity

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dospropleys.android.joystock.R

class ProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        if(Intent.ACTION_SEARCH == intent.action) {
                intent.getStringArrayExtra(SearchManager.QUERY)?.also { query ->
                Toast.makeText(this, " " + query.toString(), Toast.LENGTH_LONG).show()
            }
    }
    }
}