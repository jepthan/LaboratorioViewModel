package cr.ac.una.controlarterial.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.ac.una.controlarterial.AuthInterceptor
import cr.ac.una.controlarterial.entity.TomaArterial
import cr.ac.una.jsoncrud.dao.TomaArterialDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

class TomasArterialesViewModel: ViewModel() {
    lateinit var apiService : TomaArterialDao
    private val _ListMutableData: MutableLiveData<List<TomaArterial>> = MutableLiveData()
    val listLiveData : LiveData<List<TomaArterial>> =_ListMutableData

    fun updatelist(){
        initService()

        GlobalScope.launch(Dispatchers.IO) {

            var Datos = apiService.getItems().items
            withContext(Dispatchers.Main){
                _ListMutableData.value = Datos!!
            }
        }
    }
    fun deleteitem(uuid :String){
        GlobalScope.launch(Dispatchers.IO) {
            apiService.deleteItem(uuid)
        }

    }
    fun AddItem(item :List<TomaArterial>){
        initService()
        GlobalScope.launch(Dispatchers.IO){
            apiService.createItem(item)
            withContext(Dispatchers.Main){
                updatelist()
            }
        }

    }

    fun initService(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor("TGpxJJkXwkg38zO0Cgv9F80pwe1tOwq3rC2WD7_XwH7LZ3wYRg"))
            .addInterceptor(interceptor)
            .build()

        //val gson = GsonBuilder().setPrettyPrinting().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://crudapi.co.uk/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(TomaArterialDao::class.java)
    }
}