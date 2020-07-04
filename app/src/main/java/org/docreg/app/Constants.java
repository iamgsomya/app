package org.docreg.app;

public class Constants {
    static String prefix = "http://192.168.1.8:5001";
    static String authToken;
    static String loginUrl = prefix + "/user-login";
    static String registerPatientUrl = prefix + "/register-patient";
    static String docRegisterUrl = prefix + "/register-doctor";
    static String fetchPatientsUrl = prefix + "/recognize-patient";

}
