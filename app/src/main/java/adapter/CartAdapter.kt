import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letu.R
import fragment.CartFragment

class CartAdapter(
    private val cartList: MutableList<CartFragment.Produk>,
    private val onItemClick: ((CartFragment.Produk) -> Unit),
    private val onPlusClick: ((CartFragment.Produk) -> Unit),
    private val onMinusClick: ((CartFragment.Produk) -> Unit)
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView: ImageView = itemView.findViewById(R.id.gambar)
        val productmerkTv: TextView = itemView.findViewById(R.id.merk)
        val productpriceTv: TextView = itemView.findViewById(R.id.harga)
        val del: ImageButton = itemView.findViewById(R.id.buttondel)
        val plus: ImageView = itemView.findViewById(R.id.plus)
        val min: ImageView = itemView.findViewById(R.id.min)
        val qtyTextView: TextView = itemView.findViewById(R.id.qty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val product = cartList[position]
        Glide
            .with(holder.itemView.context)
            .load(product.gambar)
            .into(holder.productImageView)
        holder.productmerkTv.text = product.nama
        holder.productpriceTv.text = product.harga.toString()
        holder.qtyTextView.text = product.qty.toString()

        holder.del.setOnClickListener {
            onItemClick.invoke(product)
            removeProduct(product)
        }

        holder.plus.setOnClickListener {
            onPlusClick.invoke(product)
        }

        holder.min.setOnClickListener {
            onMinusClick.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    private fun removeProduct(product: CartFragment.Produk) {
        val position = cartList.indexOf(product)
        if (position != -1) {
            cartList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
