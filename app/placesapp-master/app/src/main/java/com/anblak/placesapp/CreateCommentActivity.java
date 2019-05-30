package com.anblak.placesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anblak.placesapp.data.LoginRepository;
import com.anblak.placesapp.utils.Constants;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CreateCommentActivity extends AppCompatActivity {

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final String TAG = "Create_comment";
    private static final String SUCCESS_RESULT = "Respect";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comment);
        final EditText commentEditText = findViewById(R.id.edit_comment);
        final Button addCommentButton = findViewById(R.id.add_comment);
        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCommentButton.setVisibility(View.VISIBLE);
                if(LoginRepository.getUser() != null) {
                    String message = commentEditText.getText().toString();

                    RequestBody formBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                            "{\n" +
                                    "\"message\":\""+ message +"\",\n" +
                                    "\"userUUID\":\""+ LoginRepository.getUser().getUuid()+"\",\n" +
                                    "\"placeId\":"+ ItemDetailFragment.getmItem().getId()+"\n" +
                                    "}"
                    );

                    Request request = new Request.Builder()
                            .url(Constants.SERVER_URL + "/comments")
                            .post(formBody)
                            .build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        if(!response.body().string().equals(SUCCESS_RESULT)) {
                            Toast.makeText(CreateCommentActivity.this, "Error",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(CreateCommentActivity.this, "Comment: " + message,
                                    Toast.LENGTH_LONG).show();
                        }
                        finish();
                    } catch (IOException e) {
                        Log.v(TAG, e.getMessage());
                        Toast.makeText(CreateCommentActivity.this, "Error",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }

                } else {
                    Toast.makeText(CreateCommentActivity.this, "Authorize please",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
