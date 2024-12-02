package com.GestionStock.stock.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class GenericService<Entity,Type> {
    private final GenericDAO<Entity> genericDAO;

    public GenericService(GenericDAO<Entity> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Transactional
    public Entity create(Entity entity){
        return genericDAO.save(entity);
    }

    @Transactional
    public Entity update(Entity entity, Type slug){
        Entity existingEntity = genericDAO.findByAttribute("slug", slug);

        if (existingEntity == null) {
            throw new IllegalArgumentException("Entity n'existe pas pour cet identifiant");
        }

        return genericDAO.update(entity);
    }

    @Transactional
    public String delete(Type value){
        Entity entity = genericDAO.findByAttribute("slug", value);
        if (entity != null){
            genericDAO.delete(entity);
        }else {
            throw new IllegalArgumentException("L'element que vous voulez supprimé n'existe pas");
        }

        return "Suppression réussie" ;
    }

    public Entity findByValue(Type value, String attributeName){
        return genericDAO.findByAttribute(attributeName, value);
    }

    public Entity findById(Long id){
        return genericDAO.findById(id);
    }

    public List<Entity> findListByAttributeName(Object value, String attributName){
        return genericDAO.findByListAttribute(attributName, value);
    }

    public List<Entity> findAll(){
        return genericDAO.findAll();
    }

    public List<Entity> findDynamicJoinTable(String tableJoinName, String table, String attributeName, Object attributValue){
        return genericDAO.findByDymicTwoTable(tableJoinName, table, attributeName, attributValue);
    }

    public List<Entity> findDynamicByOneTable(String tableJoinName, String attributeName, Object attributValue){
        return genericDAO.findByDymicOneTable(tableJoinName, attributeName, attributValue);
    }
}
