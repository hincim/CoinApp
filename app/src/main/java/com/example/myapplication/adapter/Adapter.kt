package com.example.myapplication.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RvRowBinding
import com.example.myapplication.model.CryptoModel
import com.example.myapplication.view.DetailActivity

class Adapter(val cryptoList: ArrayList<CryptoModel>,private val listener:Listener) : RecyclerView.Adapter<Adapter.CryptoHolder>() {

    private val colors:Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93")

    interface Listener{
        fun onItemClickListener(cryptoModel: CryptoModel)
    }

    class CryptoHolder(val binding: RvRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = RvRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {

        holder.binding.textName.text = cryptoList.get(position).currency
        holder.binding.textPrice.text = cryptoList.get(position).price
        holder.itemView.setBackgroundColor(Color.parseColor(colors.get(position % 6)))

        holder.itemView.setOnClickListener{
            listener.onItemClickListener(cryptoList.get(position))
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("info",cryptoList.get(position).currency)
            intent.putExtra("info2",cryptoList.get(position).price)
            intent.putExtra("color",colors.get(position % 6))
            holder.itemView.context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }


}