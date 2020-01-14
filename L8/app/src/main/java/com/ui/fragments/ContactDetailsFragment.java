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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dal.entities.Contact;
import com.ui.contacts.R;
import com.ui.contacts.databinding.ContactDetailsFragmentBinding;
import com.viewModels.ContactViewModel;

public class ContactDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ContactViewModel viewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);

        final ContactDetailsFragmentBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.contact_details_fragment, container, false);
        Bundle args = getArguments();
        int id = args.getInt("ContactId");

        LiveData<Contact> contact = viewModel.getData(id);
        contact.observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                binding.setContact(contact);
            }
        });

        final View view = binding.getRoot();
        Button deleteB = view.findViewById(R.id.deleteContact);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Contact contact = binding.getContact();
                        viewModel.delete(contact);
                        getActivity().getFragmentManager().popBackStack();
                    }
                });
            }
        });

        Button updateB = view.findViewById(R.id.updateContact);
        updateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Contact contact = binding.getContact();
                        contact.name = ((EditText)view.findViewById(R.id.name)).getText().toString();
                        contact.phone = ((EditText)view.findViewById(R.id.phone)).getText().toString();
                        viewModel.updateData(contact);
                    }
                });
            }
        });

        return view;
    }

}
