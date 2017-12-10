package com.example.tristan.prog3210a01;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class AppDatabase_Impl extends AppDatabase {
  private volatile DOADatabase _dOADatabase;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(17) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AppUsers` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `password` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e7726af5b3a5b75928627bd2dfa46aa9\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `AppUsers`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAppUsers = new HashMap<String, TableInfo.Column>(3);
        _columnsAppUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsAppUsers.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsAppUsers.put("password", new TableInfo.Column("password", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppUsers = new TableInfo("AppUsers", _columnsAppUsers, _foreignKeysAppUsers, _indicesAppUsers);
        final TableInfo _existingAppUsers = TableInfo.read(_db, "AppUsers");
        if (! _infoAppUsers.equals(_existingAppUsers)) {
          throw new IllegalStateException("Migration didn't properly handle AppUsers(com.example.tristan.prog3210a01.AppUsers).\n"
                  + " Expected:\n" + _infoAppUsers + "\n"
                  + " Found:\n" + _existingAppUsers);
        }
      }
    }, "e7726af5b3a5b75928627bd2dfa46aa9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "AppUsers");
  }

  @Override
  public DOADatabase userDao() {
    if (_dOADatabase != null) {
      return _dOADatabase;
    } else {
      synchronized(this) {
        if(_dOADatabase == null) {
          _dOADatabase = new DOADatabase_Impl(this);
        }
        return _dOADatabase;
      }
    }
  }
}
