package com.example.tristan.prog3210a01;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Tristan on 2017-11-29.
 */

@Entity
public class User {
    @PrimaryKey
    public final int id;
    public String name;
    public String password;


    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
