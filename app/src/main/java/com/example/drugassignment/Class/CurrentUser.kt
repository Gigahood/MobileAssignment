package com.example.drugassignment.Class

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.IgnoreExtraProperties
import java.time.LocalDateTime
import java.util.*

@IgnoreExtraProperties
class CurrentUser{
    var displayName : String? = "DrugDetox"
    var email : String? = ""
    var address : String? = ""
    var role : String? = ""
    @RequiresApi(Build.VERSION_CODES.O)
    var registerDate : String? = ""
    var availability : Boolean = true

    constructor()

    constructor(
        displayName: String?,
        email: String?,
        address: String?,
        role: String?,
        registerDate: String?,
        availability : Boolean
    ) {
        this.displayName = displayName
        this.email = email
        this.address = address
        this.role = role
        this.registerDate = registerDate
        this.availability = availability
    }


}