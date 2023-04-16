package com.devsadeq.clothessuggester.data.local

interface LocalData {
    fun saveClothing(id: String, lastTimeWorn: Long)

    fun isClothingWornInLastTwoDays(id: String): Boolean
}