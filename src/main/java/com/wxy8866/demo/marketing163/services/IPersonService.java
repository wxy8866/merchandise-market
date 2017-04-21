package com.wxy8866.demo.marketing163.services;

import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.entities.PersonExample;

/**
 * Created by wxy8866 on 2017/3/20.
 */
public interface IPersonService extends IBaseService<Person>
{
    public Person findByUsername(String username);

    public void deleteByPersomExample(PersonExample personExample);
}
