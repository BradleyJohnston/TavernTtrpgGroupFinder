package com.sontath.tavernttrpggroupfinder.BuisinessLogic

interface IDatabaseDao {

    fun init()
    fun readGenericProfile(id: String)
    fun readPlayerProfile(id: String)
    fun readGameProfiles(id: String)
}