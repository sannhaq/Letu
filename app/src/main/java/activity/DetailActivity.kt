package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.letu.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dataclass.Child
import dataclass.Main
import dataclass.populer

class DetailActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var buy: Button
    private var nameproduct = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val db = FirebaseFirestore.getInstance()

        auth = Firebase.auth
        buy = findViewById(R.id.buynow)

        val keranjangReference = db.collection("keranjang").document(auth.currentUser!!.uid)

        val backButton: ImageView = findViewById(R.id.back)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val productMain = intent.getParcelableExtra<Main>("Mainproduct")
        if (productMain !=null){
            val type : TextView = findViewById(R.id.typeproduct)
            val image : ImageView = findViewById(R.id.imageproduct)
            val name : TextView = findViewById(R.id.nameproduct)
            val price : TextView = findViewById(R.id.priceproduct)
            val desc : TextView = findViewById(R.id.descproduct)

            type.text = productMain.productType
            image.setImageResource(productMain.productImage)
            name.text = productMain.productName
            price.text = productMain.productPrice
            desc.text = productMain.productDesc

            buy.setOnClickListener{
                nameproduct = productMain.productName.toString()
                keranjangReference.update("produk", FieldValue.arrayUnion(productMain.id))
                Toast.makeText(this,"$nameproduct Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }

        val productChild = intent.getParcelableExtra<Child>("Childproduct")
        if (productChild !=null){
            val type : TextView = findViewById(R.id.typeproduct)
            val image : ImageView = findViewById(R.id.imageproduct)
            val name : TextView = findViewById(R.id.nameproduct)
            val price : TextView = findViewById(R.id.priceproduct)
            val desc : TextView = findViewById(R.id.descproduct)

            type.text = productChild.productType
            image.setImageResource(productChild.productImage)
            name.text = productChild.productName
            price.text = productChild.productPrice
            desc.text = productChild.productDesc
            buy.setOnClickListener{
                nameproduct = productChild.productName
                keranjangReference.update("produk", FieldValue.arrayUnion(productChild.id))
                Toast.makeText(this,"$nameproduct Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }

        val product = intent.getParcelableExtra<populer>("Populerproduct")
        if (product !=null){
            val type : TextView = findViewById(R.id.typeproduct)
            val image : ImageView = findViewById(R.id.imageproduct)
            val name : TextView = findViewById(R.id.nameproduct)
            val price : TextView = findViewById(R.id.priceproduct)
            val desc : TextView = findViewById(R.id.descproduct)

            type.text = product.productType
            image.setImageResource(product.productImage)
            name.text = product.productName
            price.text = product.productPrice
            desc.text = product.productDesc
            buy.setOnClickListener{
                nameproduct = product.productName
                keranjangReference.update("produk", FieldValue.arrayUnion(product.id))
                Toast.makeText(this,"$nameproduct Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}