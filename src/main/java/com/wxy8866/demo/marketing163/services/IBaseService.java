package com.wxy8866.demo.marketing163.services;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wxy8866 on 2017/3/16.
 */

public interface IBaseService<E>
{
    public int create(E entity);

    public int update(E entity);

    public E findById(Serializable id);

    public List<E> findAll();

    public int delete(E entity);

    public int deleteAll();

}


