package com.example.coffeepedia.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LoginFormState {
    @Nullable
    private Integer nameError;
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer phoneError;
    @Nullable
    private Integer ageError;

    private boolean isDataValid;

    LoginFormState(@Nullable Integer nameError,@Nullable Integer emailError, @Nullable Integer passwordError, @Nullable Integer phoneError, @Nullable Integer ageError) {
        this.nameError = nameError;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.phoneError = phoneError;
        this.ageError = ageError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.nameError = null;
        this.emailError = null;
        this.passwordError = null;
        this.phoneError = null;
        this.ageError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getNameError() {
        return nameError;
    }

    @Nullable
    Integer getEmailError() {
        return emailError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getPhoneError() {
        return phoneError;
    }

    @Nullable
    Integer getAgeError() {
        return ageError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}

//class LoginFormState {
//    @Nullable
//    private Integer usernameError;
//    @Nullable
//    private Integer passwordError;
//    private boolean isDataValid;
//
//    LoginFormState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
//        this.usernameError = usernameError;
//        this.passwordError = passwordError;
//        this.isDataValid = false;
//    }
//
//    LoginFormState(boolean isDataValid) {
//        this.usernameError = null;
//        this.passwordError = null;
//        this.isDataValid = isDataValid;
//    }
//
//    @Nullable
//    Integer getUsernameError() {
//        return usernameError;
//    }
//
//    @Nullable
//    Integer getPasswordError() {
//        return passwordError;
//    }
//
//    boolean isDataValid() {
//        return isDataValid;
//    }
//}
