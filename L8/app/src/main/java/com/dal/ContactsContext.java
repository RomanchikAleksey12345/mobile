package com.dal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dal.entities.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactsContext extends RoomDatabase {
    public abstract ContactDao contactDao();
}
