package com.example.feature_details_screen.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItemContracts
import io.reactivex.Maybe
import io.reactivex.Single


@Dao
interface DetailsDao {

    @Query("SELECT * FROM ${RemotePhoneDetailsItemContracts.TABLE_NAME} WHERE ${RemotePhoneDetailsItemContracts.Columns.ID} = :detailsId")
    fun getDetails(detailsId:String): Single<RemotePhoneDetailsItem>

    @Insert
    fun addDetails(remotePhoneDetailsItem: RemotePhoneDetailsItem)

}