package activity

import adapter.PopulerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letu.R
import dataclass.populer
import java.util.*
import kotlin.collections.ArrayList

class PopulerActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView : SearchView
    private lateinit var productList: ArrayList<populer>
    private lateinit var populerAdapter: PopulerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_populer)

        init()
    }

    private fun init(){
        recyclerView = findViewById(R.id.populerrecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        productList = ArrayList()
        addDataToList()

        populerAdapter = PopulerAdapter(productList)
        recyclerView.adapter = populerAdapter

        populerAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Populerproduct", it)
            startActivity(intent)
        }

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query : String?){
        if (query != null){
            val filteredList = ArrayList<populer>()
            for (i in productList){
                if (i.productName.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }else{
                populerAdapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList(){
        productList.add(populer("hoNCUeQsVG8oiPYpSCQO","Joyko Stabilo", "Rp 10.000",
            R.drawable.stabilo1, "Stabilo", "Joyko", getString(R.string.stabilio)))
        productList.add(populer("Iqy5EJ8P4raXoUj6Llj2","Pensil Warna", "Rp 35.000",
            R.drawable.pensilwarna, "Pensil Warna", "Faber-Castell", getString(R.string.pensilwarna)))
        productList.add(populer("3qS2taAO1RkyzguKno2Q","Penggaris Butterfly", "Rp 5.000",
            R.drawable.penggaris1, "Penggaris", "ButterFly", getString(R.string.penggaris)))
        productList.add(populer("bi6TbGeYS4bz45SNydK4","Tipex Kenko", "Rp 5.000",
            R.drawable.tipex1, "Tipex", "Kenko", getString(R.string.tipex)))
        productList.add(populer("niblAIAnS2RObwBOSU4P","White Board Marker", "Rp 9.000",
            R.drawable.spidol, "Spidol", "Faber-Castell", getString(R.string.spidol)))
        productList.add(populer("xkxteh9egyqhU3uCYtIa","Mechanical Pensil", "Rp 20.000",
            R.drawable.pensil2, "pensil", "Faber-Castell", getString(R.string.pensil2)))
        productList.add(populer("dPYKTDL56aAbwY8Do3Gi","Pensil Graphite", "Rp 50.000",
            R.drawable.pensil1, "pensil", "Faber-Castell", getString(R.string.pensil1)))
        productList.add(populer("KIoiCQgtcJ8oi8S9JiBK","Fast Gel Z 0.5", "Rp 20.000",
            R.drawable.pulpen2, "Pulpen", "Faber-Castell", getString(R.string.pulpen2)))
        productList.add(populer("3TJFMGikQvce015LkdYd","Grip 2011 FineWriter", "Rp 287.000",
            R.drawable.pulpen3, "Pulpen", "Faber-Castell", getString(R.string.pulpen3)))
        productList.add(populer("Q3OUqxCggmakATGxUAXK","Buku tulis SIDU 38", "Rp 5.000",
            R.drawable.buku, "Buku", "Sidu", getString(R.string.buku)))
    }
}