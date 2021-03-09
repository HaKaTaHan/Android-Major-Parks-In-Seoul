package com.teamnoyes.majorparksinseoul.datamodel

data class ModelAllParksData(val SearchParkInfoService: ModelAllParkDataResult)

data class ModelAllParkDataResult(val list_total_count: Int, val RESULT: ModelResultMessage, val row: List<ModelAllParkDataInfo>)
data class ModelResultMessage(val CODE: String, val MESSAGE: String)
data class ModelAllParkDataInfo(
    val P_IDX: Int,
    val P_PARK: String,
    val P_LIST_CONTENT: String,
    val AREA: String,
    val OPEN_DT: String,
    val MAIN_EQUIP: String,
    val MAIN_PLANTS: String,
    val GUIDANCE: String,
    val VISIT_ROAD: String,
    val USE_REFER: String,
    val P_IMG: String,
    val P_ADDR: String,
    val P_NAME: String,
    val P_ADMINTEL: String,
    val G_LONGITUDE: Double,
    val G_LATITUDE: Double,
    val LONGITUDE: Double,
    val LATITUDE: Double,
    val TEMPLATE_URL: String
)