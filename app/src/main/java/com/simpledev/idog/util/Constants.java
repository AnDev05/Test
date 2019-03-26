package com.simpledev.idog.util;

public final class Constants {

    public static final class DbKey{
        public static final String DB_NAME = "dog_data.db";
    }

    public static final class Firebase{
        public static final String BREED_COLLECTION = "breeds";
    }

    public static final class Prf{
        public static final String RF_HELPER = "app_ref";
        public static final String FIRST_TIME = "first_launch";
        public static final String LOAD_RESOURCE_OK = "load_resource";
        public static final String NUMBER_BREED = "number_breed";
    }


    public static final class BundleKey{
        public static final String SUB_BREED = "sub_breed";
        public static final String BASE_BREED = "base_breed";
        public static final String IMAGE_URL = "url";
        public static final String NAME_BREED = "breed_name";
        public static final String BREED = "breed";
    }

    public static final class ErrorCode {
        public static final int NETWORK_ERROR = 600;
        public static final int OTHER_ERROR = 900;
        public static final int ADDRESS_NOT_FOUND = 601;
    }

    public static class Apis {

        public static final String DOG_CEO_URL = "https://dog.ceo/";
        public static final String RANDOM_DOG_URL = "https://random.dog/";
    }
}
