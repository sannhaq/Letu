package dataclass

import android.os.Parcel
import android.os.Parcelable

data class populer(val id:String, val productName:String, val productPrice:String, val productImage:Int, val productType:String, val productMerk:String, val productDesc:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(productName)
        parcel.writeString(productPrice)
        parcel.writeInt(productImage)
        parcel.writeString(productType)
        parcel.writeString(productMerk)
        parcel.writeString(productDesc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<populer> {
        override fun createFromParcel(parcel: Parcel): populer {
            return populer(parcel)
        }

        override fun newArray(size: Int): Array<populer?> {
            return arrayOfNulls(size)
        }
    }

}
