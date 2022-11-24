import com.google.gson.annotations.SerializedName


data class HousesModel (
    @SerializedName("message" ) var message : String?        = null,
    @SerializedName("obj"     ) var obj     : ArrayList<Obj> = arrayListOf()
)

data class Obj (


    @SerializedName("name"        ) var name        : String?   = null,
    @SerializedName("precio"      ) var precio      : Int?      = null,
    @SerializedName("tipo"        ) var tipo        : String?   = null,
    @SerializedName("estatus"     ) var estatus     : String?   = null,
    @SerializedName("direccion"   ) var direccion   : String?   = null,
    @SerializedName("restrooms"   ) var restrooms   : Int?      = null,
    @SerializedName("bed"         ) var bed         : Int?      = null,
    @SerializedName("estado"      ) var estado      : String?   = null,
    @SerializedName("descripcion" ) var descripcion : String?   = null,
    @SerializedName("metros"      ) var metros      : Int?      = null,
    @SerializedName("location"    ) var location    : Location? = Location()

)

data class Location (

    @SerializedName("latitude"  ) var latitude  : Double? = null,
    @SerializedName("longitude" ) var longitude : Double? = null

)