package cr.ac.una.jsoncrud.dao

import cr.ac.una.controlarterial.entity.TomaArterial
import cr.ac.una.controlarterial.entity.TomasArteriales
import retrofit2.http.*

interface TomaArterialDao {

    @GET("TomaArterial")
    suspend fun getItems(): TomasArteriales

    @GET("TomaArterial/{uuid}")
    suspend fun getItem(@Path("uuid") uuid: String): TomaArterial

    @POST("TomaArterial")
    suspend fun createItem(@Body items: List<TomaArterial>): TomaArterial

    @PUT("TomaArterial/{uuid}")
    suspend fun updateItem(@Path("uuid") uuid: String, @Body item: TomaArterial): TomaArterial

    @DELETE("TomaArterial/{uuid}")
    suspend fun deleteItem(@Path("uuid") uuid: String)
}