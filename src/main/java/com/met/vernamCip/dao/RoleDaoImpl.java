/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.dao;

import com.met.vernamCip.model.Role;
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
public class RoleDaoImpl implements RoleDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> root = cq.from(Role.class);
        cq.select(root).where(cb.equal(root.get("role"), name));

        Query query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<Role> res = query.getResultList();

        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    } 
    
}
