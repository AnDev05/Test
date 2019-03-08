package com.simpledev.idog.util.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.interactor.model.DaoMaster;
import com.simpledev.idog.interactor.model.DaoSession;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Flowable;


public class DatabaseHelper extends DaoMaster.OpenHelper implements RxDatabaseHelper {

    private static DatabaseHelper sDatabaseHelper;

    private DaoSession mDaoSession;
    @Inject
    public DatabaseHelper(Context context, String database) {
        super(context, database);
    }

    public static DatabaseHelper getInstance(Context context, String database) {
        if(sDatabaseHelper == null) {
            sDatabaseHelper = new DatabaseHelper(context, database);
        }
        return sDatabaseHelper;
    }

    @Override
    public DaoSession getDaoSession() {
        if(mDaoSession == null) {
            mDaoSession = new DaoMaster(sDatabaseHelper.getWritableDb()).newSession();
        }
        return mDaoSession;
    }

    @Override
    public void clearDaoSession() {
        if(mDaoSession !=null){
            mDaoSession.clear();
        }
    }

    @Override
    public void storeBreedList(List<Breed> breedList) {
        for (Breed breed: breedList){
            getDaoSession().insert(breed);
        }
    }

    @Override
    public Flowable<List<Breed>> getBreedList() {
        return Flowable.fromCallable(new Callable<List<Breed>>() {
            @Override
            public List<Breed> call() {
                return getDaoSession().getBreedDao().loadAll();
            }
        });
    }

    @Override
    public void clearBreedList() {
        getDaoSession().getBreedDao().deleteAll();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
