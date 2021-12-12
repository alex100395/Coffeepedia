package com.example.coffeepedia.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeepedia.MainNavActivity;
import com.example.coffeepedia.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private String file = "coffeepedia.txt";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        final EditText name = findViewById(R.id.et1);
        final EditText email = findViewById(R.id.et2);
        final EditText password = findViewById(R.id.et3);
        final EditText phone = findViewById(R.id.et4);
        final EditText age = findViewById(R.id.et5);


        name.requestFocus();
        age.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "60")});


        phone.setText("99");
        Selection.setSelection(phone.getText(), phone.getText().length());
        phone.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("99")) {
                    phone.setText("99");
                    Selection.setSelection(phone.getText(),
                            phone.getText().length());

                }
            }

        });

        try
        {
            FileInputStream fin = openFileInput(file);
            DataInputStream in = new DataInputStream(fin);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int i = 0;
            String lines[] = new String[5];
            String strLine;

            while((strLine = br.readLine()) != null)
            {
                lines[i] = strLine;
                i++;
            }
            name.setText(lines[0]);
            email.setText(lines[1]);
            password.setText(lines[2]);
            phone.setText(lines[3]);
            age.setText(lines[4]);

            in.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(getBaseContext(), "something went wrong", Toast.LENGTH_LONG).show();
        }

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getNameError() != null) {
                    name.setError(getString(loginFormState.getNameError()));
                }
                if (loginFormState.getEmailError() != null) {
                    email.setError(getString(loginFormState.getEmailError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    password.setError(getString(loginFormState.getPasswordError()));
                }
                if (loginFormState.getPhoneError() != null) {
                    phone.setError(getString(loginFormState.getPhoneError()));
                }
                if (loginFormState.getAgeError() != null) {
                    age.setError(getString(loginFormState.getAgeError()));
                }

                }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {

                    goToMain();

                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(name.getText().toString(),
                                                email.getText().toString(),
                                                password.getText().toString(),
                                                phone.getText().toString(),
                                                age.getText().toString());
            }
        };


        name.addTextChangedListener(afterTextChangedListener);
        email.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);
        phone.addTextChangedListener(afterTextChangedListener);
        age.addTextChangedListener(afterTextChangedListener);

        password.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(email.getText().toString(),
                                         password.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(email.getText().toString(),
                                     password.getText().toString());
            }
        });
    }

    private void goToMain()
    {
        EditText name = findViewById(R.id.et1);
        EditText email = findViewById(R.id.et2);
        EditText password = findViewById(R.id.et3);
        EditText phone = findViewById(R.id.et4);
        EditText age = findViewById(R.id.et5);

        String nameData = name.getText().toString() + "\n";
        String emailData = email.getText().toString() + "\n";
        String passwordData = password.getText().toString() + "\n";
        String numberData = phone.getText().toString() + "\n";
        String ageDate = age.getText().toString() + "\n";

        try
        {
            FileOutputStream fout = openFileOutput(file, 0);
            fout.write(nameData.getBytes());
            fout.write(emailData.getBytes());
            fout.write(passwordData.getBytes());
            fout.write(numberData.getBytes());
            fout.write(ageDate.getBytes());
            fout.close();
            Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(getBaseContext(), "something went wrong", Toast.LENGTH_LONG).show();
        }

        Intent in = new Intent(getApplicationContext(), MainNavActivity.class);
        startActivity(in);
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public class InputFilterMinMax implements InputFilter {

        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
