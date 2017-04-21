package com.wxy8866.demo.marketing163.services.impl;

import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.entities.PersonExample;
import com.wxy8866.demo.marketing163.mybatis.mappers.PersonMapper;
import com.wxy8866.demo.marketing163.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wxy8866 on 2017/3/14.
 */
@Service
@Transactional
public class PersonService implements IPersonService
{
    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person findByUsername(String username)
    {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andUsernameGreaterThanOrEqualTo(username);
        return personMapper.selectByExample(personExample).get(0);
    }

    @Override
    public int create(Person entity)
    {
        return personMapper.insert(entity);
    }

    @Override
    public int update(Person entity)
    {
        return personMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Person findById(Serializable id)
    {
        return personMapper.selectByPrimaryKey((Integer) id);
    }

    @Override
    public List<Person> findAll()
    {
        return personMapper.selectByExample(null);
    }

    @Override
    public int delete(Person entity)
    {
        return personMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public void deleteByPersomExample(PersonExample personExample)
    {
        personMapper.deleteByExample(personExample);
    }

    @Override
    public int deleteAll()
    {
        return personMapper.deleteByExample(null);
    }
}
