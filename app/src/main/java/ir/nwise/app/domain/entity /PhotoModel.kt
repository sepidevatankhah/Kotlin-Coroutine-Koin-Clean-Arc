package ir.nwise.app.domain.entity

data class PhotoModel(
                       val query : String = "",
                       val pageSize: Int=20,
                       val pageNum: Int=1)