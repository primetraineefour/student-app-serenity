package com.studentapp.constants;

/**
 * Created by Jay
 */
public class EndPoints {

    /*
     * This is Endpoints of student api
     */
    public static final String GET_ALL_STUDENT = "/list";
    public static final String GET_SINGLE_STUDENT_BY_ID = "/{studentId}";
    public static final String UPDATE_STUDENT_BY_ID = "/{studentId}";
    public static final String DELETE_STUDENT_BY_ID = "/{studentId}";

    /*
     * This is Endpoints of Authenticate api
     */
    public static final String LOGIN = "/api";
    public static final String LOGOUT = "/close_session";



}
