package com.example.feature_details_screen.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItemContracts

@Dao
interface DetailsDao {

    @Query("SELECT * FROM ${RemotePhoneDetailsItemContracts.TABLE_NAME} WHERE ${RemotePhoneDetailsItemContracts.Columns.ID} = :detailsId")
    suspend fun getDetails(detailsId:String):RemotePhoneDetailsItem?

    @Insert
    suspend fun addDetails(remotePhoneDetailsItem: RemotePhoneDetailsItem)

}