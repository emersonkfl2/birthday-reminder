package org.birthday.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;

import java.util.List;

public class JPAFriendRepository implements FriendRepository {
    private EntityManagerFactory emf;

    public JPAFriendRepository(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    @Override
    public List<Friend> getFriends() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Friend> query = em.createQuery("SELECT f FROM Friend f", Friend.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
