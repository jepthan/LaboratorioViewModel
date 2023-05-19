package cr.ac.una.controlarterial.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cr.ac.una.controlarterial.R
import cr.ac.una.controlarterial.entity.TomaArterial

class TomaArterialAdapter(var tomasArteriales: ArrayList<TomaArterial>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            ViewHolder(view)
        }


    }
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = tomasArteriales[position]

        if (holder is HeaderViewHolder) {
            holder.bind()
        } else if (holder is ViewHolder) {
            val controlArterialItem = item
            holder.bind(controlArterialItem)
        }
    }

    override fun getItemCount(): Int {
        return tomasArteriales.size
    }
    fun updateData(newData: ArrayList<TomaArterial>) {

        tomasArteriales = newData
        if (!newData.isEmpty())
            if(newData[0]._uuid !=null)
                newData.add(0,TomaArterial(0,0,0,null))
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(){
            val sistolicaTextView = itemView.findViewById<TextView>(R.id.sistolica)
            val distolicaTextView = itemView.findViewById<TextView>(R.id.distolica)
            val ritmoTextView = itemView.findViewById<TextView>(R.id.ritmo)
            sistolicaTextView.text = "Sistólica"
            distolicaTextView.text = "Diastólica"
            ritmoTextView.text = "Ritmo"
        }

    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val sistolicaTextView = itemView.findViewById<TextView>(R.id.sistolica)
        val distolicaTextView = itemView.findViewById<TextView>(R.id.distolica)
        val ritmoTextView = itemView.findViewById<TextView>(R.id.ritmo)


        fun bind(tomaArterial: TomaArterial) {
            itemView.setBackgroundColor (Color.LTGRAY)
            sistolicaTextView.text = tomaArterial.Sistolica.toString()
            distolicaTextView.text = tomaArterial.Diastolica.toString()
            ritmoTextView.text = tomaArterial.Pulso.toString()
        }
    }

}