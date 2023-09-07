package com.example.stardewvalleysnpcwiki

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Npc(
    val name: String,
    val description: String,
    val photo: Int,
    val birthday: String,
    val age: Int
) : Parcelable
