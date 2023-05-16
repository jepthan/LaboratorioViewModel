package cr.ac.una.controlarterial.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cr.ac.una.controlarterial.R
import cr.ac.una.controlarterial.entity.TomaArterial

class TomaArterialAdapter(context: Context, TomasArteriales: List<TomaArterial>):
ArrayAdapter<TomaArterial>(context, 0,TomasArteriales){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val tomaArterial = getItem(position)

        val SistolicaView = view!!.findViewById<TextView>(R.id.sistolicaTextView)
        val DistolicaView = view.findViewById<TextView>(R.id.diastolicaTextView)
        val PulsoView = view.findViewById<TextView>(R.id.pulsoTextView)

        SistolicaView.text = tomaArterial!!.Sistolica.toString()
        DistolicaView.text = tomaArterial.Diastolica.toString()
        PulsoView.text = tomaArterial.Pulso.toString()

        return view
    }
}