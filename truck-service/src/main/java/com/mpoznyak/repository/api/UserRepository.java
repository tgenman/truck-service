package com.mpoznyak.repository.api;

import com.mpoznyak.model.User;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:34
 */
public interface UserRepository {

    void add(User user);

    void add(Iterable<User> users);

    void remove(User user);

    void update(User user);

    List<User> queryAll();
}
