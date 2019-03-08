package com.simpledev.idog.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BreedListResponse extends BaseResponse{
    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private Object listBreeds;
}

