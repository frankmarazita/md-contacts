package com.example.mdcontacts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(val name: String, val phone: String, val email: String) : Parcelable