package com.bhn.raptorauthorizationservice.commons;

public class Constants {

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILURE = "FAILURE";

    public class Header {
        public static final String HEADER_AUTH = "Authorization";
        public static final String HEADER_AUTH_PREFIX = "Bearer ";
    }

    public class Jwt {
        public static final String PAYLOAD_ATTRIBUTE_NAME = "sub";
        public static final String PAYLOAD_ATTRIBUTE_ROLES = "roles";
    }

    public class Error {
        public static final String FIELD_MISSING = "Field {} is required";
        public static final String UNKNOWN_FIELD = "Field {} is should be cm/inch";
    }

    public class Message {
        public static final String SUCCESSFULLY_SAVED_TEMPLATE_DATA_MSG = "Template saved successfully.";
    }
}