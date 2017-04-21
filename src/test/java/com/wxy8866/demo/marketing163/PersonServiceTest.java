package com.wxy8866.demo.marketing163;

import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.entities.PersonExample;
import com.wxy8866.demo.marketing163.services.IPersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context/application-context.xml")
public class PersonServiceTest
{
    private static Log logger = LogFactory.getLog(PersonServiceTest.class);
    @Autowired
    private IPersonService personService;

    @Test
    public void testInsert()
    {

        Person person = new Person();
        person.setUsername("wxy8866");
        person.setNickname("wxy");
        person.setPassword("967351");
        personService.create(person);
        logger.info("Insert completed");

    }

    @Test
    public void testDelete()
    {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andUsernameGreaterThanOrEqualTo("wxy8866").andNicknameEqualTo("wxy");
        personService.deleteByPersomExample(personExample);
    }

    //自定义条件查询
    @Test
    public void testFindByUsername()
    {
        String username = "wxy8866";
        Person person = personService.findByUsername(username);

        if (person != null)
        {

            logger.info("Username: " + username + " Nickname: " + person.getNickname());

        }
    }

    @Test
    public void testUpdateByPrimaryKey()
    {

        String username = "wxy8866";
        Person person = personService.findByUsername(username);

        if (person != null)
        {
            person.setNickname("xingyiwei");
            personService.update(person);
            logger.info("nickname is changed to : " + person.getNickname());
        }


    }

}