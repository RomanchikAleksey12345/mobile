package com.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dal.entities.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("Select * from contact")
    LiveData<List<Contact>> getAll();

    @Query("Select * from contact where id = :id")
    LiveData<Contact> getById(int id);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
