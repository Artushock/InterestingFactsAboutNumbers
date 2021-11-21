package com.artushock.interestingfactsaboutnumbers.model

sealed class NumberFactData {
    data class Success(val serverResponseData: String) : NumberFactData()
    data class Error(val error: Throwable) : NumberFactData()
    object Loading : NumberFactData()
}
