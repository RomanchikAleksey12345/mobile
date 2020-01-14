package com;

import android.app.Application;

import androidx.room.Room;

import com.dal.ContactsContext;

public class ContactsApp extends Application {
    public static ContactsApp instance;

    private ContactsContext context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = Room
                .databaseBuilder(this, ContactsContext.class, "database")
                .build();
    }

    public static ContactsApp getInstance() {
        return instance;
    }

    public ContactsContext getContext() {
        return context;
    }
}
