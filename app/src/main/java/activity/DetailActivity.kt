package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.letu.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dataclass.Child
import dataclass.Main
import dataclass.populer

class DetailActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        auth = Firebase.auth

        val productMain = intent.getParcelableExtra<Main>("Mainproduct")
        if (productMain !=null){
            val type : TextView = findViewById(R.id.typeproduct)
            val image : ImageView = findViewById(R.id.imageproduct)
            val merk : TextView = findViewById(R.id.merkproduct)
            val price : TextView = findViewById(R.id.priceproduct)
            val desc : TextView = findViewById(R.id.descproduct)

            type.text = productMain.productType
            image.setImageResource(productMain.productImage)
            merk.text = productMain.productMerk
            price.text = productMain.productPrice
            desc.text = productMain.productDesc
        }

        val productChild = intent.getParcelableExtra<Child>("Childproduct")
        if (productChild !=null){
            val type : TextView = findViewById(R.id.typeproduct)
            val image : ImageView = findViewById(R.id.imageproduct)
            val merk : TextView = findViewById(R.id.merkproduct)
            val price : TextView = findViewById(R.id.priceproduct)
            val desc : TextView = findViewById(R.id.descproduct)

            type.text = productChild.productType
            image.setImageResource(productChild.productImage)
            merk.text = productChild.productMerk
            price.text = productChild.productPrice
            desc.text = productChild.productDesc
        }

        val product = intent.getParcelableExtra<populer>("Populerproduct")
        if (product !=null){
            val type : TextView = findViewById(R.id.typeproduct)
            val image : ImageView = findViewById(R.id.imageproduct)
            val merk : TextView = findViewById(R.id.merkproduct)
            val price : TextView = findViewById(R.id.priceproduct)
            val desc : TextView = findViewById(R.id.descproduct)

            type.text = product.productType
            image.setImageResource(product.productImage)
            merk.text = product.productMerk
            price.text = product.productPrice
            desc.text = product.productDesc
        }
    }
}