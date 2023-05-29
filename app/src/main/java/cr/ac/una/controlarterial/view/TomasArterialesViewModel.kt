package cr.ac.una.controlarterial.view

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.initialize
import cr.ac.una.controlarterial.entity.TomaArterial
import kotlinx.coroutines.currentCoroutineContext
import kotlin.coroutines.coroutineContext

class TomasArterialesViewModel: ViewModel() {
    lateinit var database : FirebaseDatabase
    private lateinit var TomaArterialReff: DatabaseReference
    private val _ListMutableData: MutableLiveData<List<TomaArterial>> = MutableLiveData()
    val listLiveData : LiveData<List<TomaArterial>> =_ListMutableData


    fun updatelist(context: Context){

        initService(context)

        TomaArterialReff = database.getReference("TomaArterial")
        TomaArterialReff.get().addOnSuccessListener {
            val list = mutableListOf<TomaArterial>()
            it.children.forEach { x->
                val item = x.getValue(TomaArterial::class.java)
                item?._uuid = x.key
                if (item != null) {
                    list.add(item)
                }
            }
            _ListMutableData.value = list
        }


    }
    fun deleteitem(uuid :String,context: Context){
        initService(context)
        database.getReference("TomaArterial/${uuid}").removeValue()

    }
    fun AddItem(item :List<TomaArterial>,context: Context){

        initService(context)

        TomaArterialReff = database.getReference("TomaArterial")
        item.forEach { x->
            val TomaArterialId = TomaArterialReff.push().key
            TomaArterialReff.child(TomaArterialId!!).setValue(x)
        }
    }

    fun initService(context: Context){

        FirebaseApp.initializeApp(context)
        // Inicializar Firebase
        database = FirebaseDatabase.getInstance()


    }
}