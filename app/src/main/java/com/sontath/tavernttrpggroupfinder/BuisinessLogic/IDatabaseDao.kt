package com.sontath.tavernttrpggroupfinder.BuisinessLogic

import com.sontath.tavernttrpggroupfinder.ui.profile.gameProfileUiState
import com.sontath.tavernttrpggroupfinder.ui.profile.genericProfileUiState
import com.sontath.tavernttrpggroupfinder.ui.profile.playerProfileUiState

interface IDatabaseDao {

    fun init()
    fun readGenericProfile(id: String): genericProfileUiState
    fun readPlayerProfile(id: String): playerProfileUiState
    fun readGameProfiles(id: String): ArrayList<gameProfileUiState>
}