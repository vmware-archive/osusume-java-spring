package com.tokyo.beach.application.user;

import com.tokyo.beach.application.session.TokenGenerator;
import com.tokyo.beach.application.token.UserSession;

public interface UserRepository {
    UserSession logon(TokenGenerator generator, String email, String password);
    DatabaseUser create(String email, String password);
}
