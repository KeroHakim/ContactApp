package dataClass

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactData(val name: String?, val phone: String, val description: String ) : Parcelable