package com.artushock.interestingfactsaboutnumbers.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artushock.interestingfactsaboutnumbers.api.RetrofitImpl
import com.artushock.interestingfactsaboutnumbers.app.App.Companion.getHistoryDao
import com.artushock.interestingfactsaboutnumbers.model.NumberFactData
import com.artushock.interestingfactsaboutnumbers.model.NumberRequestItem
import com.artushock.interestingfactsaboutnumbers.room.LocalRepository
import com.artushock.interestingfactsaboutnumbers.room.LocalRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.FieldPosition

class NumbersViewModel(
    private val dataToObserve: MutableLiveData<NumberFactData> = MutableLiveData(),
    private val retrofitImpl: RetrofitImpl = RetrofitImpl(),
    private val localRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao()),
) : ViewModel() {

    fun getSpecificNumberFact(number: String): MutableLiveData<NumberFactData> {
        sendRequest(number)
        return dataToObserve
    }

    fun getRandomNumberFact(): MutableLiveData<NumberFactData> {
        sendRequest(null)
        return dataToObserve
    }

    fun saveRequestToDb(item: NumberRequestItem) {
        localRepository.saveEntity(item)
    }

    fun getAllItemsFromDb(): List<NumberRequestItem> = localRepository.getAllHistory()


    private fun sendRequest(number: String?) {
        dataToObserve.value = NumberFactData.Loading

        val callback = if (number == null)
            retrofitImpl.getRetrofitImpl().getRandomNumberFact()
        else
            retrofitImpl.getRetrofitImpl().getSpecificNumberFact(number)

        callback.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() != null) {
                    dataToObserve.postValue(NumberFactData.Success(response.body()!!))
                } else {
                    val message = response.message()
                    if (message.isNullOrEmpty()) {
                        dataToObserve.postValue(NumberFactData.Error(Throwable("Undefined")))
                    } else {
                        dataToObserve.postValue(NumberFactData.Error(Throwable(message)))
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                dataToObserve.postValue(NumberFactData.Error(t))
            }
        })
    }
}