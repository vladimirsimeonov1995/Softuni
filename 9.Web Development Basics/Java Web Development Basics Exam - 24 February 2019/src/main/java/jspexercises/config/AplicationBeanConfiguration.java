package jspexercises.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class AplicationBeanConfiguration {

    @Produces
    public EntityManager entityManager(){
        return Persistence
                .createEntityManagerFactory("exodia")
                .createEntityManager();
    }

    @Produces
    protected ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
