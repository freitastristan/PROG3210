package com.example.tristan.prog3210a01;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
/**
 * Created by Tristan on 2017-11-29.
 */

@Dao
public interface DOADatabase {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

    @Query("select * from user")
    public List<User> getAllUser();
}
