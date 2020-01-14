package com.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dal.entities.Contact;
import com.ui.adapters.ContactAdapter;
import com.ui.contacts.MainActivity;
import com.ui.contacts.R;
import com.viewModels.ContactsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private final ContactAdapter contactAdapter = new ContactAdapter(new ArrayList<Contact>(), this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_list_fragment, container, false);
        return view;
    }

    @Override
    public void onStart() {
        ContactsViewModel contactsViewModel = new ViewModelProvider(requireActivity()).get(ContactsViewModel.class);
        LiveData<List<Contact>> data = contactsViewModel.getData();
        final RecyclerView contactsLV = getView().findViewById(R.id.contactsList);

        data.observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
//                ContactAdapter contactAdapter = new ContactAdapter(requireActivity(), R.layout.contact_list_view, contacts);
//                contactsLV.setAdapter(contactAdapter);
                int orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 4);
                    contactsLV.setLayoutManager(gridLayoutManager);
                } else {
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(requireActivity());
                    contactsLV.setLayoutManager(mLayoutManager);
                }

                contactAdapter.setContactList(contacts);
                contactsLV.setAdapter(contactAdapter);
            }
        });

//        contactsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ContactAdapter adapter = (ContactAdapter) parent.getAdapter();
//                Contact contact = (Contact) adapter.getItem((int)id);
//                Bundle args = new Bundle();
//                args.putInt("ContactId", contact.id);
//
//                ((MainActivity)getActivity()).replaceFragment(ContactDetailsFragment.class, args);
//            }
//        });
        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = contactAdapter.getItem(position);
        Bundle args = new Bundle();
        args.putInt("ContactId", contact.id);

        ((MainActivity)getActivity()).replaceFragment(ContactDetailsFragment.class, args);
    }
}
