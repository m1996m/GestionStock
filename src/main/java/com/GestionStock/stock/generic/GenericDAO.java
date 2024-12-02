package com.GestionStock.stock.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.Setter;

import java.util.List;

public class GenericDAO <Entity> {
    @Setter
    private Class<Entity> type;

    public GenericDAO(Class<Entity> type) {
        this.type = type;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Entity save(Entity entity){
        entityManager.persist(entity);

        return entity;
    }

    public Entity update(Entity entity){
        return entityManager.merge(entity);
    }

    public void delete(Entity entity){
        entityManager.remove(entity);
    }

    public Entity findById(Long id){
        return entityManager.find(type, id);
    }

    public Entity findByAttribute(String attributeName, Object value) {
        String jpql = "SELECT e FROM " + type.getSimpleName() + " e WHERE e." + attributeName + " = :value";
        return entityManager.createQuery(jpql, type)
                .setParameter("value", value)
                .getSingleResult();
    }

    public List<Entity> findByListAttribute(String attributeName, Object value) {
        String jpql = "SELECT e FROM " + type.getSimpleName() + " e WHERE e." + attributeName + " = :value";
        return entityManager.createQuery(jpql, type)
                .setParameter("value", value)
                .getResultList();
    }

    public List<Entity> findAll(){
        return entityManager.createQuery("SELECT t FROM " + type.getSimpleName() + " t", type).getResultList();
    }

    public List<Entity> findByDymicTwoTable(String joinTable, String table, String attributName, Object attributValue){
        String request = "SELECT e FROM " + type.getSimpleName() + " e JOIN e." + joinTable + " j WHERE j."+ table+"." + attributName + "=:attributValue";

        TypedQuery<Entity> query = entityManager.createQuery(request, type);
        query.setParameter("attributValue", attributValue);

        return query.getResultList();
    }

    public List<Entity> findByDymicOneTable(String joinTable, String attributName, Object attributValue){
        String request = "SELECT e FROM " + type.getSimpleName() + " e JOIN e." + joinTable + " j WHERE j." + attributName + "=:attributValue";

        TypedQuery<Entity> query = entityManager.createQuery(request, type);
        query.setParameter("attributValue", attributValue);

        return query.getResultList();
    }

}
