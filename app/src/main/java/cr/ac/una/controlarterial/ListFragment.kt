package cr.ac.una.controlarterial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import cr.ac.una.controlarterial.Adapter.TomaArterialAdapter
import cr.ac.una.controlarterial.entity.TomaArterial
import cr.ac.una.controlarterial.entity.TomasArteriales
import cr.ac.una.controlarterial.view.TomasArterialesViewModel
import cr.ac.una.jsoncrud.dao.TomaArterialDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@Suppress("MoveLambdaOutsideParentheses")
class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: TomasArterialesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProvider(this).get(TomasArterialesViewModel::class.java)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listview = view.findViewById<ListView>(R.id.list_TomaArterial)

        val adapter = TomaArterialAdapter(requireContext(), ArrayList<TomaArterial>())
        listview.adapter = adapter

        viewModel.updatelist()

        viewModel.listLiveData.observe(viewLifecycleOwner,{ elements->
            adapter.clear()
            adapter.addAll(elements)
            adapter.notifyDataSetChanged()
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}