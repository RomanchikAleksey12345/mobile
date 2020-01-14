package com.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ContactsApp;
import com.dal.ContactDao;
import com.dal.ContactsContext;
import com.dal.entities.Contact;

import java.util.List;

public class ContactsViewModel extends ViewModel {
    private ContactsContext context;
    private LiveData<List<Contact>> contacts;

    public ContactsViewModel() {
        context = ContactsApp.getInstance().getContext();
        ContactDao dao = context.contactDao();
        contacts = dao.getAll();
    }

    public LiveData<List<Contact>> getData() {
        return contacts;
    }

    public void insertData(Contact contact) {
        ContactDao dao = context.contactDao();
        dao.insert(contact);
    }
}
