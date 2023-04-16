package org.birthday.infrastructure;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class SQLiteFriendRepository implements FriendRepository {

    private final EntityManager entityManager;

    public SQLiteFriendRepository(String persistenceUnitName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Friend> findAll() {
        TypedQuery<Friend> query = entityManager.createQuery("SELECT f FROM Friend f", Friend.class);
        return query.getResultList();
    }
}
