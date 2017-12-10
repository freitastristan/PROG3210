package com.example.tristan.prog3210a01;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Tristan on 2017-11-29.
 */

@Entity
public class AppUsers {
    @PrimaryKey(autoGenerate = true)
    long id;
    public String name;
    public String password;


    public AppUsers(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
