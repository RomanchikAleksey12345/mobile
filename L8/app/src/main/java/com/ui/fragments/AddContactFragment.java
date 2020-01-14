package com.ui.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dal.entities.Contact;
import com.ui.contacts.R;
import com.viewModels.ContactsViewModel;

public class AddContactFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_contact_fragment, container, false);

        Button saveB = view.findViewById(R.id.saveContact);
        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameET = view.findViewById(R.id.name);
                EditText phoneET = view.findViewById(R.id.phone);

                final Contact contact = new Contact();
                contact.name = nameET.getText().toString();
                contact.phone = phoneET.getText().toString();

                final ContactsViewModel contactsViewModel = new ViewModelProvider(requireActivity()).get(ContactsViewModel.class);
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        contactsViewModel.insertData(contact);
                    }
                });
            }
        });

        return view;
    }
}
