package com.simpledev.idog.util.appdata;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.interactor.model.DaoSession;
import com.simpledev.idog.network.request.Apis;
import com.simpledev.idog.network.response.BreedResponse;
import com.simpledev.idog.network.response.RandomDogResponse;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.util.database.RxDatabaseHelper;
import com.simpledev.idog.util.database.RxPreferenceHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class AppDataHelper implements RxAppDataHelper {
    private Apis ceoDogApis;
    private Apis randomDogAPis;
    private RxDatabaseHelper mDatabaseHelper;
    private RxPreferenceHelper mPreferenceHelper;

    @Inject
    public AppDataHelper(@Named(Constants.Apis.DOG_CEO_URL) Apis ceoDogApis,@Named(Constants.Apis.RANDOM_DOG_URL) Apis randomDogAPis, RxDatabaseHelper mDatabaseHelper, RxPreferenceHelper mPreferenceHelper) {
        this.ceoDogApis = ceoDogApis;
        this.randomDogAPis = randomDogAPis;
        this.mDatabaseHelper = mDatabaseHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }


    @Override
    public DaoSession getDaoSession() {
        return mDatabaseHelper.getDaoSession();
    }

    @Override
    public void clearDaoSession() {
        mDatabaseHelper.clearDaoSession();
    }

    @Override
    public void storeBreedList(List<Breed> breedList) {
        mDatabaseHelper.storeBreedList(breedList);
    }

    @Override
    public Flowable<List<Breed>> getBreedList() {
        return mDatabaseHelper.getBreedList();
    }

    @Override
    public void clearBreedList() {
        mDatabaseHelper.clearBreedList();
    }

    @Override
    public void saveNumberOfBreed(Long id, int size) {
        mDatabaseHelper.saveNumberOfBreed(id, size);
    }


    @Override
    public void saveString(String key, String value) {
        mPreferenceHelper.saveString(key, value);
    }

    @Override
    public String getString(String key) {
        return mPreferenceHelper.getString(key);
    }

    @Override
    public void saveBoolean(String key, boolean value) {
        mPreferenceHelper.saveBoolean(key, value);
    }

    @Override
    public boolean getBoolean(String key) {
        return mPreferenceHelper.getBoolean(key);
    }

    @Override
    public boolean contains(String key) {
        return mPreferenceHelper.contains(key);
    }

    @Override
    public int getInt(String key) {
        return mPreferenceHelper.getInt(key);
    }

    @Override
    public void saveInt(String key, int value) {
        mPreferenceHelper.saveInt(key, value);
    }

    @Override
    public Single<BreedResponse> getAllImageOfBreed(String baseBreed, String subBreed) {
        return ceoDogApis.getAllImageOfBreed(baseBreed, subBreed);
    }

    @Override
    public Single<BreedResponse> getAllImageOfBreed(String baseBreed) {
        return ceoDogApis.getAllImageOfBreed(baseBreed);
    }

    @Override
    public Single<RandomDogResponse> getRandomDog() {
        return randomDogAPis.getRandomDog();
    }
}
