package com.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ContactsApp;
import com.dal.ContactDao;
import com.dal.ContactsContext;
import com.dal.entities.Contact;

public class ContactViewModel extends ViewModel {
    private ContactsContext context;
    private LiveData<Contact> contact;

    public ContactViewModel() {
        context = ContactsApp.getInstance().getContext();
    }

    public void updateData(Contact contact) {
        ContactDao dao = context.contactDao();
        dao.update(contact);
    }

    public LiveData<Contact> getData(int id) {
        ContactDao dao = context.contactDao();
        return contact = dao.getById(id);
    }

    public void delete(Contact contact) {
        ContactDao dao = context.contactDao();
        dao.delete(contact);
    }
}
