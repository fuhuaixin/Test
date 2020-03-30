package com.example.test0.base

import android.os.Parcel
import android.os.Parcelable

class ErrorMsg(var code: Int = 0, var msg: String = "服务异常") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(code)
        parcel.writeString(msg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorMsg> {
        override fun createFromParcel(parcel: Parcel): ErrorMsg {
            return ErrorMsg(parcel)
        }

        override fun newArray(size: Int): Array<ErrorMsg?> {
            return arrayOfNulls(size)
        }
    }

}