package com.example.feature_details_screen.data.model

import androidx.room.TypeConverter


class RemotePhoneDetailsConverters {

    @TypeConverter
    fun fromListToString (list:List<String>):String{
        return list.joinToString("^&")
    }
    @TypeConverter
    fun fromStringToList (string:String):List<String>{
        return string.split("^&")
    }


}