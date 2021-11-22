package com.artushock.interestingfactsaboutnumbers.room

import com.artushock.interestingfactsaboutnumbers.model.NumberRequestItem

interface LocalRepository {
    fun getAllHistory(): List<NumberRequestItem>
    fun saveEntity(item: NumberRequestItem)
}