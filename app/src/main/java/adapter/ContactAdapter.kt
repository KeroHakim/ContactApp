package adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route.contact.R
import dataClass.ContactData

class ContactAdapter(private val contactList: MutableList<ContactData>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    //  interface instance
    var onContactItemClickListener : OnContactItemClickListener? = null

    private val TAG = "Kiro"
    // createViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ContactViewHolder(view)
    }
    // fun add contact
    fun addContact(contact: ContactData){
        Log.d(TAG, "addContact: ${contactList.size}")
        contactList.add(contact)
        Log.d(TAG, "addContact: ${contactList.size}")
        Log.d(TAG, "addContact: $contact")
        notifyItemInserted(contactList.size-1)
    }
    // list size
    override fun getItemCount(): Int = contactList.size
    //bindViewHolder
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
        // holder interface

    }
    // interface
    fun interface OnContactItemClickListener {
        fun onContactItemClick(item :ContactData)
    }
    // ViewHolder
    inner class ContactViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val contactName = itemView.findViewById<TextView>(R.id.contact_Name)
        fun bind(contact : ContactData){
            contactName.text = contact.name
            itemView.setOnClickListener{
                onContactItemClickListener?.onContactItemClick(contact)
            }
        }
    }
}