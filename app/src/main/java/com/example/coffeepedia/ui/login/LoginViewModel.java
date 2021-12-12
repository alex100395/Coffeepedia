package com.example.coffeepedia.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.provider.ContactsContract;
import android.util.Patterns;

import com.example.coffeepedia.data.LoginRepository;
import com.example.coffeepedia.data.Result;
import com.example.coffeepedia.data.model.LoggedInUser;
import com.example.coffeepedia.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String name, String email, String password, String phone,String age) {
        if (!isNameValid(name))
        {
            loginFormState.setValue(new LoginFormState(R.string.invalid_name, null, null, null, null));
        }
        else if (!isEmailValid(email))
        {
            loginFormState.setValue(new LoginFormState(null,R.string.invalid_email, null, null, null));
        }
        else if (!isPasswordValid(password))
        {
            loginFormState.setValue(new LoginFormState(null, null ,R.string.invalid_password, null, null));
        }
        else if (!isPhoneValid(phone))
        {
            loginFormState.setValue(new LoginFormState(null, null, null,R.string.invalid_phone, null));
        }
        else if (!isAgeValid(age))
        {
            loginFormState.setValue(new LoginFormState(null, null, null, null, R.string.invalid_age));
        }
        else
        {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isNameValid(String name) {
        if (name == null) {
            return false;
        } else if (name.length() >= 4 && name.length() <= 12) {
            return true;
        } else {
            return false;
        }
    }

    // A placeholder username validation check
    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@euc.ac.cy")) {
            return true;
        } else {
            return false;
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        } else if (password.length() >= 4 && password.length() <= 8) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isPhoneValid(String phone) {
        if (phone == null) {
            return false;
        } else if (phone.length() == 8) {
            return true;
        } else {
            return false;
        }
    }
    private boolean isAgeValid(String age) {
        if (age == null) {
            return false;
        } else if (age.length() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
