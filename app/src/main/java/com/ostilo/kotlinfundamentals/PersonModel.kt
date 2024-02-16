package com.ostilo.kotlinfundamentals

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PersonModel(var name: String?, var age:Int) : Parcelable