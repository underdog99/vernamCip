/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.dao;

import com.met.vernamCip.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mladen
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(int id) {
        User u = entityManager.find(User.class, id);
        return u;
    }

    @Override
    public User findUserByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("email"), email));

        Query query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<User> res = query.getResultList();

        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveUser(User u) {
        entityManager.persist(u);
        System.out.println("Gotovo cuvanje!");
    }

}
