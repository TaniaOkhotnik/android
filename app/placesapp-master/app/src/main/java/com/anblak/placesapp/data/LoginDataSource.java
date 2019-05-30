package com.anblak.placesapp.data;

import android.util.Log;

import com.anblak.placesapp.data.model.LoggedInUser;
import com.anblak.placesapp.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TAG = "Auth";

    public Result<LoggedInUser> login(String username, String password) {

        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("login", username)
                    .add("password", password)
                    .build();
            Request request = new Request.Builder()
                    .url(Constants.SERVER_URL + "/users/auth")
                    .post(formBody)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            LoggedInUser user = objectMapper.readValue(response.body().string(), LoggedInUser.class);
            Log.v(TAG, user.getUuid());
            return new Result.Success<>(user);
        } catch (Exception e) {
            Log.v(TAG, e.getLocalizedMessage());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
