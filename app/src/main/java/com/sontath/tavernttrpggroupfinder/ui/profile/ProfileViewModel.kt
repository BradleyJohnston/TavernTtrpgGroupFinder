package com.sontath.tavernttrpggroupfinder.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    // Generic String Resources
    lateinit var genericNameString: String
    lateinit var genericGenderString: String
    lateinit var genericAgeString: String

    // Generic data class
    var genericData: genericProfileUiState? = null

    // Generic Live Data
    private val _genericName = MutableLiveData<String>().apply {
        value = (genericNameString + genericData?.name) ?: ""
    }
    val genericName: LiveData<String> = _genericName

    private val _genericGender = MutableLiveData<String>().apply {
        value = (genericGenderString + genericData?.gender) ?: ""
    }
    val genericGender: LiveData<String> = _genericGender

    private val _genericAge = MutableLiveData<String>().apply {
        value = (genericAgeString + genericData?.age) ?: ""
    }
    val genericAge: LiveData<String> = _genericAge

    // Player String Resources
    lateinit var playerVersionString: String
    lateinit var playerArchetypeString: String
    lateinit var playerPartySizeString: String

    // Player Generic Class
    var playerData: playerProfileUiState? = null

    // Player Live Data
    private val _playerVersion = MutableLiveData<String>().apply {
        value = (playerVersionString + playerData?.preferredGameVersion) ?: ""
    }
    var playerVersion: LiveData<String> = _playerVersion

    private val _playerArchetype = MutableLiveData<String>().apply {
        value = (playerArchetypeString + playerData?.preferredGameVersion) ?: ""
    }
    var playerArchetype: LiveData<String> = _playerArchetype

    private val _playerPartySize = MutableLiveData<String>().apply {
        value = (playerPartySizeString + playerData?.preferredPartySize) ?: ""
    }
    var playerPartySize: LiveData<String> = _playerPartySize
}