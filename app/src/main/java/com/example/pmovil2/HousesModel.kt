import com.google.gson.annotations.SerializedName


data class HousesModel (
    @SerializedName("message" ) var message : String?        = null,
    @SerializedName("obj"     ) var obj     : ArrayList<Obj> = arrayListOf()
)



data class Obj (

    @SerializedName("_id"         ) var Id          : String?           = null,
    @SerializedName("houseName"   ) var houseName   : String?           = null,
    @SerializedName("type"        ) var type        : String?           = null,
    @SerializedName("status"      ) var status      : String?           = null,
    @SerializedName("price"       ) var price       : Double?              = null,
    @SerializedName("direction"   ) var direction   : String?           = null,
    @SerializedName("restrooms"   ) var restrooms   : Double?              = null,
    @SerializedName("bedrooms"    ) var bedrooms    : Double?              = null,
    @SerializedName("state"       ) var state       : String?           = null,
    @SerializedName("description" ) var description : String?           = null,
    @SerializedName("meters"      ) var meters      : Double?              = null,
    @SerializedName("location"    ) var location    : Location?         = Location(),
    @SerializedName("extras"      ) var extras      : ArrayList<Extras> = arrayListOf()

)

data class Location (

    @SerializedName("latitude"  ) var latitude  : Double? = null,
    @SerializedName("longitude" ) var longitude : Double? = null,
    @SerializedName("_id"       ) var Id        : String? = null

)

data class Extras (

    @SerializedName("laundry"         ) var laundry        : Boolean? = null,
    @SerializedName("parking"         ) var parking        : Boolean? = null,
    @SerializedName("air_conditioner" ) var airConditioner : Boolean? = null,
    @SerializedName("heating"         ) var heating        : Boolean? = null,
    @SerializedName("_id"             ) var Id             : String?  = null

)