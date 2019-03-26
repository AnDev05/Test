package com.simpledev.idog.util.database;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.interactor.model.DaoSession;

import java.util.List;

import io.reactivex.Flowable;

public interface RxDatabaseHelper {
    DaoSession getDaoSession();
    void clearDaoSession();

    /**
     * Store breed list to sqlite db
     * @param breedList
     */
    void storeBreedList(List<Breed> breedList);

    /**
     * Get from sqlite db
     * @return
     */
    Flowable<List<Breed>> getBreedList();

    /**
     * Clear all breed in db
     */
    void clearBreedList();

    void saveNumberOfBreed(Long id, int size);
}
