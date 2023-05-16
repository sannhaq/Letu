package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dataclass.Child
import com.example.letu.R

class ChildAdapter(private val childList: ArrayList<Child>) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    var onItemClick : ((Child) -> Unit)? = null

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView : ImageView = itemView.findViewById(R.id.image_product)
        val productnameTv : TextView = itemView.findViewById(R.id.name_product)
        val productpriceTv : TextView = itemView.findViewById(R.id.price_product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val product = childList[position]
        holder.productImageView.setImageResource(product.productImage)
        holder.productnameTv.text = product.productName
        holder.productpriceTv.text = product.productPrice

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }
}