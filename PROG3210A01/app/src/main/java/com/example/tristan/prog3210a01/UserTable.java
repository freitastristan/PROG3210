package com.example.tristan.prog3210a01;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Tristan on 2017-11-29.
 */

public class UserTable {
    @Entity(tableName = "user",
            foreignKeys = {
                    @ForeignKey(
                            entity = User.class,
                            parentColumns = "id",
                            childColumns = "userId",
                            onDelete = ForeignKey.CASCADE
                    )},
            indices = { @Index(value = "id")}
    )
    public class UserData {

        @PrimaryKey(autoGenerate = true)
        long id;

        public long userId;
        String description;

        public UserData(long userId, String description) {
            this.userId = userId;
            this.description = description;
        }
    }
}

