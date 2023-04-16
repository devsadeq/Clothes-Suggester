package com.devsadeq.clothessuggester.data.local

import android.content.SharedPreferences

class LocalDataImpl(
    private val sharedPreferences: SharedPreferences
) : LocalData {

    companion object {
        const val TWO_DAYS = 2 * 24 * 60 * 60 * 1000
    }

    override fun saveClothing(id: String, lastTimeWorn: Long) {
        sharedPreferences.edit().putLong(id, lastTimeWorn).apply()
    }

    override fun isClothingWornInLastTwoDays(id: String): Boolean {
        val lastTimeWorn = sharedPreferences.getLong(id, 0)
        val twoDaysAgo = System.currentTimeMillis() - TWO_DAYS
        return lastTimeWorn > twoDaysAgo
    }
}