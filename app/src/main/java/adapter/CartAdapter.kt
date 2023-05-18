package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letu.R
import dataclass.Main
import fragment.CartFragment

class CartAdapter(private val cartList: List<CartFragment.Produk>,
                  private val onItemClick : ((CartFragment.Produk) -> Unit))
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productImageView : ImageView = itemView.findViewById(R.id.gambar)
        val productmerkTv : TextView = itemView.findViewById(R.id.merk)
        val productpriceTv : TextView = itemView.findViewById(R.id.harga)
        val del : ImageButton = itemView.findViewById(R.id.buttondel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartAdapter.CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val product = cartList[position]
        Glide
            .with(holder.itemView.context)
            .load(product.gambar) // memuat gambar dari URL atau resource drawable
            .into(holder.productImageView) // menampilkan gambar pada ImageView
        holder.productmerkTv.text = product.nama
        holder.productpriceTv.text = product.harga.toString()

        holder.del.setOnClickListener{
            onItemClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

}