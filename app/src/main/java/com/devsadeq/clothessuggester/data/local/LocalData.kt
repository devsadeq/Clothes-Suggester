package com.devsadeq.clothessuggester.data.local

import com.devsadeq.clothessuggester.data.model.Outfit

interface LocalData {
    fun saveSuggestedOutfit(id: String, lastTimeWorn: Long)

    fun getSuggestedOutfit(temperature: Double): Outfit?

    fun isOutfitWornInLastTwoDays(id: String): Boolean
}