package com.simpledev.idog.interactor;

import com.simpledev.idog.network.response.RandomDogResponse;

import io.reactivex.Single;

public interface RandomInteractor extends BaseInteractor {
    Single<RandomDogResponse> loadRandomDog();
}