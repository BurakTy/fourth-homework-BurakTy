package com.buraktuysuz.fourthhomework.service.entityService;

import com.buraktuysuz.fourthhomework.entitiy.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository> {

    private D dao;

    public List<E> findAll(){
        return dao.findAll();
    }

    public E findById(Long id){
        Optional<E> optionalE = dao.findById(id);
        E e = null;
        if (optionalE.isPresent()){
            e = optionalE.get();
        }
        return e;
    }

    public E save(E e){
        return (E) dao.save(e);
    }

    public void delete(E e){
        dao.delete(e);
    }

    public D getDao() {
        return dao;
    }
}
