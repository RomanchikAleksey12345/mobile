package com.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dal.entities.Contact;
import com.ui.contacts.databinding.ContactListViewBinding;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contactList;
    private AdapterView.OnItemClickListener onItemClickListener;

    public ContactAdapter(List<Contact> contactList, AdapterView.OnItemClickListener onItemClickListener) {
        this.contactList = contactList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ContactListViewBinding itemBinding = ContactListViewBinding.inflate(layoutInflater, parent, false);
        return new ContactViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contactList != null ? contactList.size() : 0;
    }

    public void setContactList(List<Contact> contacts) {
        this.contactList = contacts;
    }

    public Contact getItem(int id) {
        return contactList.get(id);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ContactListViewBinding binding;

        public ContactViewHolder(ContactListViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.contactName.setOnClickListener(this);
        }

        public void bind(Contact contact) {
            binding.setContact(contact);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }
}

//public class ContactAdapter extends BaseAdapter {
//    private ArrayList<Contact> contacts;
//    private LayoutInflater mLayoutInflater;
//
//    public ContactAdapter(ArrayList<Contact> contacts) {
//        this.contacts = contacts;
//    }
//
//    @Override
//    public int getCount() {
//        return contacts.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return contacts.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(final int i, View view, final ViewGroup viewGroup) {
//        View result = view;
//        ContactListViewBinding binding;
//        if (result == null) {
//            if (mLayoutInflater == null) {
//                mLayoutInflater = (LayoutInflater) viewGroup.getContext()
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            }
//            binding = ContactListViewBinding.inflate(
//                    mLayoutInflater, viewGroup, false);
//            result = binding.getRoot();
//            result.setTag(binding);
//        }
//        else {
//            binding = (ContactListViewBinding) result.getTag();
//        }
////        result.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Context context = viewGroup.getContext();
////                Toast toast = Toast.makeText(context,
////                        "Position=" + Integer.toString(i) + ": " + ((Contact)getItem(i)).name,
////                        Toast.LENGTH_SHORT);
////                toast.show();
////            }
////        });
//        binding.setContact(contacts.get(i));
//        return result;
//    }

//    private LayoutInflater _inflater;
//    private int _layout;
//    private List<Contact> _contacts;
//
//    public ContactAdapter(Context context, int resource, List<Contact> contacts) {
//        super(context, resource, contacts);
//        _contacts = contacts;
//        _layout = resource;
//        _inflater = LayoutInflater.from(context);
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view= _inflater.inflate(_layout, parent, false);
//        TextView nameView = view.findViewById(R.id.contactName);
//        Contact contact = _contacts.get(position);
//        nameView.setText(contact.name);
//
//        ContactListViewBinding binding;
//        binding.inflate(_inflater, parent, false);
//
//        return view;
//    }
//}

