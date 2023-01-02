package com.sontath.tavernttrpggroupfinder.BuisinessLogic

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class FirebaseUserLogin : IUserLogin {

    private lateinit var auth: FirebaseAuth

    override fun init() {
        auth = FirebaseAuth.getInstance()
    }

    override fun getUser(): FirebaseUser? {
        return auth.currentUser
    }

    override fun signUp(username: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(username, password)
    }

    override fun signIn(username: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(username, password)
    }

    override fun getName(): String {
        return auth.currentUser?.displayName.toString()
    }

    override fun getEmail(): String {
        return auth.currentUser?.email.toString()
    }

    override fun isEmailVerified(): Boolean {
        return auth.currentUser?.isEmailVerified == true
    }

    override fun getUid(): String {
        return auth.currentUser?.uid.toString()
    }
}