package fragment

import activity.DetailActivity
import activity.PopulerActivity
import adapter.ChildAdapter
import adapter.MainAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.letu.*
import com.google.firebase.ktx.Firebase
import dataclass.Child
import dataclass.Main
import org.w3c.dom.Text
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recycler: RecyclerView
    private lateinit var productList: ArrayList<Main>
    private lateinit var childList: ArrayList<Child>
    private lateinit var mainAdapter: MainAdapter
    private lateinit var childAdapter: ChildAdapter
    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        db.collection("produk")
//            .whereEqualTo("kategori", "populer")
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.d("Test", "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("Test", "Error getting documents: ", exception)
//            }
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        init(view)

        return view
    }

    private fun init(view : View){
        // Intent menuju PopulerActivity
        view.findViewById<TextView>(R.id.seemore).setOnClickListener {
            val intent = Intent(requireContext(), PopulerActivity::class.java)
            startActivity(intent)
        }

        // Inten saat klik explore now
        view.findViewById<TextView>(R.id.explore).setOnClickListener {
            val intent = Intent(requireContext(), PopulerActivity::class.java)
            startActivity(intent)
        }

        // Main Product
        recyclerView = view.findViewById(R.id.mainrecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // Child Product
        recycler = view.findViewById(R.id.childrecyclerView)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        productList = ArrayList()
        childList = ArrayList()

        addDataToList()

        mainAdapter = MainAdapter(productList)
        recyclerView.adapter = mainAdapter

        mainAdapter.onItemClick = {
            val intent = Intent (requireContext(), DetailActivity::class.java)
            intent.putExtra("Mainproduct", it)
            startActivity(intent)
        }

        childAdapter = ChildAdapter(childList)
        recycler.adapter = childAdapter

        childAdapter.onItemClick = {
            val intent = Intent (requireContext(), DetailActivity::class.java)
            intent.putExtra("Childproduct", it)
            startActivity(intent)
        }
    }

    private fun addDataToList() {
        // Main Product
        productList.add(
            Main("dPYKTDL56aAbwY8Do3Gi","Faber-Castell", "Pensil Graphite", "Rp 50.000", R.drawable.pensil1, "pensil", getString(
                R.string.pensil1
            ))
        )
        productList.add(
            Main("KIoiCQgtcJ8oi8S9JiBK", "Faber-Castell", "Fast Gel Z 0.5", "Rp 20.000", R.drawable.pulpen2, "Pulpen", getString(
                R.string.pulpen2
            ))
        )
        productList.add(
            Main("niblAIAnS2RObwBOSU4P", "Faber-Castell", "White Board Marker", "Rp 9.000", R.drawable.spidol, "Spidol", getString(
                R.string.spidol
            ))
        )
        productList.add(
            Main("xkxteh9egyqhU3uCYtIa","Faber-Castell", "Mechanical Pensil", "Rp 20.000", R.drawable.pensil2, "Pensil", getString(
                R.string.pensil2
            ))
        )
        productList.add(
            Main("3TJFMGikQvce015LkdYd","Faber-Castell", "Grip 2011 FineWriter", "Rp 287.000", R.drawable.pulpen3, "Pulpen", getString(
                R.string.pulpen3
            ))
        )

        // Child Product
        childList.add(
            Child("hoNCUeQsVG8oiPYpSCQO","Joyko Stabilo", "Rp 10.000", R.drawable.stabilo1, "Stabilo", "Joyko", getString(
                R.string.stabilio
            ))
        )
        childList.add(
            Child("Iqy5EJ8P4raXoUj6Llj2","Pensil Warna", "Rp 35.000", R.drawable.pensilwarna, "Pensil Warna", "Faber-Castell", getString(
                R.string.pensilwarna
            ))
        )
        childList.add(
            Child("3qS2taAO1RkyzguKno2Q","Penggaris Butterfly", "Rp 5.000", R.drawable.penggaris1, "Penggaris", "ButterFly", getString(
                R.string.penggaris
            ))
        )
        childList.add(
            Child("bi6TbGeYS4bz45SNydK4","Tipex Kenko", "Rp 5.000", R.drawable.tipex1, "Tipex", "Kenko", getString(
                R.string.tipex
            ))
        )
        childList.add(
                Child("niblAIAnS2RObwBOSU4P","White Board Marker", "Rp 9.000", R.drawable.spidol, "Spidol", "Faber-Castell", getString(
                R.string.spidol
            ))
        )
        childList.add(
            Child("xkxteh9egyqhU3uCYtIa","Mechanical Pensil", "Rp 20.000", R.drawable.pensil2, "Pensil", "Faber-Castell", getString(
                R.string.pensil2
            ))
        )
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}