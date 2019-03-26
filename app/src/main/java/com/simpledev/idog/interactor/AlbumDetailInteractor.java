package com.simpledev.idog.interactor;

import com.simpledev.idog.network.response.BreedResponse;

import io.reactivex.Single;

public interface AlbumDetailInteractor extends BaseInteractor {

    Single<BreedResponse> getAllImagesOfBreed(String baseBreed, String subBreed);

    Single<BreedResponse> getAllImagesOfBreed(String baseBreed);

    void saveNumberOfBreed(Long id, int size);
}