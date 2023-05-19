package fragment

import CartAdapter
import activity.PembayaranActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letu.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class CartFragment : Fragment() {

    data class Produk(
        val id: String,
        val gambar: String,
        val nama: String,
        val harga: Int,
        var qty: Int
    )

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: CartAdapter
    private val produk = arrayListOf<Produk>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        val priceTextView: TextView = view.findViewById(R.id.price)

        var totalHarga = 0

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        val docRef = db.collection("keranjang").document(auth.currentUser!!.uid)
        recyclerView = view.findViewById(R.id.cartrecyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter = CartAdapter(
            produk,
            onItemClick = { produk ->
                Log.d("Test", produk.id)
                docRef.update("produk", FieldValue.arrayRemove(produk.id))
                    .addOnSuccessListener {
                        produk.qty--
                        totalHarga -= produk.harga
                        priceTextView.text = "Rp. $totalHarga"
                        adapter.notifyDataSetChanged()
                    }
                    .addOnFailureListener { exception ->
                        Log.d("Delete", "Failed to delete product: ${exception.message}")
                    }
            },
            onPlusClick = { produk ->
                produk.qty++
                totalHarga += produk.harga
                priceTextView.text = "Rp. $totalHarga"
                adapter.notifyDataSetChanged()
            },
            onMinusClick = { produk ->
                if (produk.qty > 1) {
                    produk.qty--
                    totalHarga -= produk.harga
                    priceTextView.text = "Rp. $totalHarga"
                    adapter.notifyDataSetChanged()
                }
            }
        )
        recyclerView.adapter = adapter

        val buttonbuy: Button = view.findViewById(R.id.buttonbuy)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (produkId in document.data?.get("produk") as? List<String> ?: emptyList()) {
                        db.collection("produk").document(produkId).get().addOnSuccessListener { it ->
                            if (it != null) {
                                produk.add(
                                    Produk(
                                        it.id,
                                        it.data!!.get("gambar").toString(),
                                        it.data!!.get("nama").toString(),
                                        it.data!!.get("harga").toString().toInt(),
                                        1 // Jumlah produk awal diatur menjadi 1
                                    )
                                )
                                adapter.notifyDataSetChanged()

                                totalHarga += it.data!!.get("harga").toString().toInt()
                                priceTextView.text = "Rp. $totalHarga"
                            }
                        }
                    }
                } else {
                    Log.d("Test", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Test", "get failed with ", exception)
            }

        buttonbuy.setOnClickListener {
            val intent = Intent(requireContext(), PembayaranActivity::class.java)
            intent.putExtra("TOTAL_HARGA", totalHarga)
            startActivity(intent)
        }

        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    companion object {
        fun newInstance(): CartFragment {
            return CartFragment()
        }
    }
}