package fr.weiwei.test.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
public abstract class AbstractDao<PK extends Serializable, T> {
     
    private final Class<T> persistentClass;
     
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
     
    @PersistenceContext
    EntityManager entityManager;
     
    protected EntityManager getEntityManager(){
        return this.entityManager;
    }
    
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
	}

    protected T getByKey(PK key) {
        return (T) entityManager.find(persistentClass, key);
    }
 
    protected void persist(T entity) {
        entityManager.persist(entity);
    }
     
    protected void update(T entity) {
        entityManager.merge(entity);
    }
 
    protected void delete(T entity) {
        entityManager.remove(entity);
    }
 
}
