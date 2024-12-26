package com.example.tbchomework111


import android.os.Parcel
import android.os.Parcelable
import java.util.UUID

data class ItemDelivery(
    val id: String = UUID.randomUUID().toString(),
    val icon: Int,
    val title: String,
    val location: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.toString(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(icon)
        parcel.writeString(title)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemDelivery> {
        override fun createFromParcel(parcel: Parcel): ItemDelivery {
            return ItemDelivery(parcel)
        }

        override fun newArray(size: Int): Array<ItemDelivery?> {
            return arrayOfNulls(size)
        }
    }
}

