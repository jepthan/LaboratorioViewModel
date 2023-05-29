package cr.ac.una.controlarterial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cr.ac.una.controlarterial.entity.TomaArterial
import cr.ac.una.controlarterial.view.TomasArterialesViewModel


class AgregarTomaArterialFragment : Fragment() {

    private lateinit var viewModel : TomasArterialesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TomasArterialesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_agregar_toma_arterial, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var btnadd = view.findViewById<Button>(R.id.btn_AddTomaArterial)

        var sistonicaview = view.findViewById<EditText>(R.id.SistonicaTextView)
        var Diastolicaview = view.findViewById<EditText>(R.id.diastolicaTextView)
        var Pulsoview = view.findViewById<EditText>(R.id.pulsoTextView)


        btnadd.setOnClickListener { x ->

            var item =  TomaArterial(sistonicaview.text.toString().toInt(),Diastolicaview.text.toString().toInt(), Pulsoview.text.toString().toInt(),null)
            var items = ArrayList<TomaArterial>()
            items.add(item)

            viewModel.AddItem(items, view.context)

            var fab = getActivity()?.findViewById<FloatingActionButton>(R.id.fab)
            fab?.show()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}