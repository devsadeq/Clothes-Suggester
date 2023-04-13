package com.devsadeq.clothessuggester.data.local

interface SharedPreferencesService {
    fun saveClothing(id: String, lastTimeWorn: Long)

    fun isClothingWornInLastTwoDays(id: String): Boolean
}