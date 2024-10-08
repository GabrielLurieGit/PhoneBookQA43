package interfaces;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface TestHelper {
    public static final String XML_DATA_FILE = "src/main/resources/data.xml";
    public static final String MY_USER = "myuser";
    public static final String MY_PASSWORD = "mypass";
    public static final String INVALID_PASS = "passwordInvalid1";
    public static final String INVALID_PASS2 = "passwordInvalid2";

    public static final String INVALID_EMAIL = "emailInvalid1";
    public static final String VALIDATION_MESSAGE1 = "Wrong email or password";


    public static final Gson GSON =  new Gson();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final OkHttpClient CLIENT = new OkHttpClient();
    public static final String BASE_URL = "https://contactapp-telran-backend.herokuapp.com";
    public static final String LOGIN_PATH = "/v1/user/login/usernamepassword";
    public static final String REGISTRATION_PATH = "/v1/user/registration/usernamepassword";
    public static final String GET_ALL_CONTACTS = "/v1/contacts";
    public static final String ADD_NEW_CONTACT = "/v1/contacts";
    public static final String UPDATE_CONTACT = "/v1/contacts";

    public static final String DELETE_CONTACT = "/v1/contacts/";
    public static final String DELETE_ALL_CONTACTS = "/v1/contacts/clear";

    public static final String AUTHORIZATION_HEADER= "Authorization";



}
