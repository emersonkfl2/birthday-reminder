package org.birthday.domain;

import java.util.List;

public interface FriendRepository {
    List<Friend> findAll();
}
