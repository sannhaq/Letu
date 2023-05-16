package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letu.R
import dataclass.populer

class PopulerAdapter(private var productList: ArrayList<populer>) : RecyclerView.Adapter<PopulerAdapter.PopulerViewHolder>()  {

    var onItemClick : ((populer) -> Unit)? = null

    class PopulerViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val productImageView : ImageView = itemView.findViewById(R.id.image_product)
        val productnameTv : TextView = itemView.findViewById(R.id.name_product)
        val productpriceTv : TextView = itemView.findViewById(R.id.price_product)
    }

    fun setFilteredList(productList: List<populer>){
        this.productList = productList as ArrayList<populer>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.populer_item, parent, false)
        return PopulerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopulerViewHolder, position: Int) {
        val product = productList[position]
        holder.productImageView.setImageResource(product.productImage)
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