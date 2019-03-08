package com.simpledev.idog.interactor;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.presenter.helper.OnDataReadyListener;

import java.util.List;

import io.reactivex.Flowable;

public interface BaseInteractor {

    void initResource(OnDataReadyListener<Breed> dataReadyListener);

    void saveBreedList(List<Breed> breedList);

    boolean checkResourceOk(int dataSize);

    boolean checkResourceOk();

    void setNumberResource(int dataSize);

    Flowable<List<Breed>> getBreeds();
}
