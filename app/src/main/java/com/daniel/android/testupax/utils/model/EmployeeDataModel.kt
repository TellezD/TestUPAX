package com.daniel.android.testupax.utils.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeDataModel(
    val id: Int,
    val name: String,
    val date: String,
    val rol: String
) : Parcelable