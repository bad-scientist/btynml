package com.example.beautymnl.data.model

import android.os.Parcel
import android.os.Parcelable

data class Developer(
    var id: Int = 0,
    var name: String? = "",
    var photo: String? = "",
    var company: String? = "",
    var phone: String? = "",
    var email: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(photo)
        parcel.writeString(company)
        parcel.writeString(phone)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Developer> {
        override fun createFromParcel(parcel: Parcel): Developer {
            return Developer(parcel)
        }

        override fun newArray(size: Int): Array<Developer?> {
            return arrayOfNulls(size)
        }
    }
}