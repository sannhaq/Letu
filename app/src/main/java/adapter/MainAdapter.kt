package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dataclass.Main
import com.example.letu.R

class MainAdapter(private val productList: List<Main>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var onItemClick : ((Main) -> Unit)? = null

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productImageView : ImageView = itemView.findViewById(R.id.image_product)
        val productmerkTv : TextView = itemView.findViewById(R.id.merk_product)
        val productnameTv : TextView = itemView.findViewById(R.id.name_product)
        val productpriceTv : TextView = itemView.findViewById(R.id.price_product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val product = productList[position]
        holder.productImageView.setImageResource(product.productImage)
        holder.productmerkTv.text = product.productMerk
        holder.productnameTv.text = product.productName
        holder.productpriceTv.text = product.productPrice

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}