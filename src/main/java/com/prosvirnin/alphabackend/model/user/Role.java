package com.prosvirnin.alphabackend.model.user;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

public enum Role {
    User, Interviewer, CompanyOwner, Admin;

    public static List<Role> getAllLessRoles(Role role){
        return Arrays.stream(Role.values()).limit(role.ordinal() + 1).toList();
    }
}
