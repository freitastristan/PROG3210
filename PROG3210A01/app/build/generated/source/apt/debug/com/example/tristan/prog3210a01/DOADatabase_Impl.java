package com.example.tristan.prog3210a01;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class DOADatabase_Impl implements DOADatabase {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAppUsers;

  private final SharedSQLiteStatement __preparedStmtOfRemoveAllUsers;

  public DOADatabase_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppUsers = new EntityInsertionAdapter<AppUsers>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `AppUsers`(`id`,`name`,`password`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppUsers value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.password == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.password);
        }
      }
    };
    this.__preparedStmtOfRemoveAllUsers = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from appusers";
        return _query;
      }
    };
  }

  @Override
  public void registerUser(AppUsers appUser) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAppUsers.insert(appUser);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeAllUsers() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveAllUsers.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveAllUsers.release(_stmt);
    }
  }

  @Override
  public List<AppUsers> getAllUsers() {
    final String _sql = "select * from appusers";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfPassword = _cursor.getColumnIndexOrThrow("password");
      final List<AppUsers> _result = new ArrayList<AppUsers>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AppUsers _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpPassword;
        _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        _item = new AppUsers(_tmpName,_tmpPassword);
        _item.id = _cursor.getLong(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
