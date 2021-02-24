package com.example.androiddevchallenge.model

import android.os.Parcel
import android.os.Parcelable

data class Pet(
    val name: String,
    val drawable: Int,
    val size: String
): Parcelable{
    constructor(parcel: Parcel) : this(
        // TODO: Need to check how this should be handled the kotlin way
        name = parcel.readString()!!,
        drawable = parcel.readInt(),
        size = parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(drawable)
        parcel.writeString(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }
}


