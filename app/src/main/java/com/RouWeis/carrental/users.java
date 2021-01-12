package com.RouWeis.carrental;

import androidx.versionedparcelable.VersionedParcelize;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class users {
   private String name;
    private String email;
    private String password;
    private String tel;

    public users() {
    }

    public users(String name, String email, String password,String tel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
