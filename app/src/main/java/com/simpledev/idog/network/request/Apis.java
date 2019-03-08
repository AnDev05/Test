package com.simpledev.idog.network.request;


import com.simpledev.idog.network.response.BreedResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Apis {
    @GET("api/breed/{baseBreed}/{subBreed}/images")
    Single<BreedResponse> getAllImageOfBreed(@Path("baseBreed") String baseBreed, @Path("subBreed") String subBreed);

    @GET("api/breed/{baseBreed}/images")
    Single<BreedResponse> getAllImageOfBreed(@Path("baseBreed") String baseBreed);
}
