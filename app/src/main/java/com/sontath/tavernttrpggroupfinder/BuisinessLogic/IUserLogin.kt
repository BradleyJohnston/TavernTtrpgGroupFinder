package com.sontath.tavernttrpggroupfinder.BuisinessLogic

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface IUserLogin {

    fun init()
    fun getUser(): FirebaseUser?
    fun signUp(username: String, password: String): Task<AuthResult>
    fun signIn(username: String, password: String): Task<AuthResult>
    fun getName(): String
    fun getEmail(): String
    fun isEmailVerified(): Boolean
    fun getUid(): String
}