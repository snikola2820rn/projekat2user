package com.rafrn2820.project2.UserService.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ROLE_CLIENT")
public class Client extends User{

    private String passport;
    private Long time;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
