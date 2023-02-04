package com.dsu.plantapp
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso

class ProdAdapter(private val mList:List<Products>):RecyclerView.Adapter<ProdAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.product_info,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val product = mList[position]

        holder.textView.setText(product.title)
        Picasso.get().load(product.images.get(0)).into(holder.imageView)

    }

    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView=ItemView.findViewById(R.id.imageview)
        val textView: TextView =ItemView.findViewById(R.id.textView)

    }
}