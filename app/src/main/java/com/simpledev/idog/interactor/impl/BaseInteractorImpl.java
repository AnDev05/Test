package com.simpledev.idog.interactor.impl;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.simpledev.idog.interactor.BaseInteractor;
import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.presenter.helper.OnDataReadyListener;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class BaseInteractorImpl implements BaseInteractor {

    @Inject
    public BaseInteractorImpl(RxAppDataHelper rxAppDataHelper) {
        mAppDataHelper = rxAppDataHelper;
    }

    protected RxAppDataHelper mAppDataHelper;

    @Override
    public void initResource(final OnDataReadyListener<Breed> dataListener) {
        FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();
        final List<Breed> breedList = new ArrayList<>();
        mFireStore.collection(Constants.Firebase.BREED_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<DocumentSnapshot> documents = task.getResult().getDocuments();

                            for (DocumentSnapshot doc: documents) {
                                Map<String, Object> objectMap = doc.getData();
                                String breedName = (String) Objects.requireNonNull(objectMap).get("breedName");
                                String breedApi = (String) Objects.requireNonNull(objectMap.get("breedApi"));
                                Long number = (Long) objectMap.get("number");
                                String fullUrl = (String) Objects.requireNonNull(objectMap).get("fullUrl");

                                String[] splits = Objects.requireNonNull(breedApi).split("-");

                                Breed breed = new Breed();
                                breed.setFirebaseId(doc.getId());
                                breed.setBreedName(breedName);
                                breed.setBreadApi(breedApi);
                                breed.setNumberOfImage(number);
                                breed.setImageUrl(fullUrl);

                                breed.setBaseBreed(splits.length > 1 ? splits[1] : breedApi);
                                breed.setSubBreed(splits.length > 1 ? splits[0] : null);

                                breedList.add(breed);
                            }
                            //Clear breed list
                            mAppDataHelper.clearBreedList();
                            //Save to db
                            saveBreedList(breedList);
                            //Save number of breed
                            mAppDataHelper.saveInt(Constants.Prf.NUMBER_BREED, breedList.size());
                            //notify data ready
                            dataListener.onSuccess(breedList);
                        }
                    }
                });
    }

    @Override
    public void saveBreedList(List<Breed> breedList) {
        mAppDataHelper.storeBreedList(breedList);
    }

    @Override
    public boolean checkResourceOk(int dataSize) {
        return mAppDataHelper.getInt(Constants.Prf.NUMBER_BREED) == dataSize;
    }

    @Override
    public boolean checkResourceOk() {
        return mAppDataHelper.getInt(Constants.Prf.NUMBER_BREED) != 0;
    }

    @Override
    public void setNumberResource(int dataSize) {
        mAppDataHelper.saveInt(Constants.Prf.NUMBER_BREED, dataSize);
    }

    @Override
    public Flowable<List<Breed>> getBreeds() {
        return mAppDataHelper.getBreedList();
    }
}
