package com.artushock.interestingfactsaboutnumbers.room

import com.artushock.interestingfactsaboutnumbers.model.NumberRequestItem

class LocalRepositoryImpl(
    private val localDataSource: HistoryDao,
) : LocalRepository{
    override fun getAllHistory(): List<NumberRequestItem> = 
        convertHistoryEntityToItem(localDataSource.all())

    override fun saveEntity(item: NumberRequestItem) {
        localDataSource.insert(convertItemToEntity(item))
    }

    private fun convertHistoryEntityToItem(entityList: List<HistoryEntity>): List<NumberRequestItem> {
        return entityList.map {
            NumberRequestItem(
                number = it.number,
                fact = it.fact
            )
        }
    }

    private fun convertItemToEntity(item: NumberRequestItem): HistoryEntity {
        return HistoryEntity(
            number = item.number,
            fact = item.fact
        )
    }
}
