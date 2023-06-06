package cr.ac.una.controlarterialJefteGeysel.entity

data class TomaArterial(
    var Sistolica: Int,
    var Diastolica: Int,
    var Pulso: Int,
    var _uuid:String?
) {
    constructor() : this(0, 0, 0, "")
}
