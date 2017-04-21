package com.wxy8866.demo.marketing163.web;

import com.wxy8866.demo.marketing163.entities.Content;
import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.services.IContentService;
import com.wxy8866.demo.marketing163.services.IPersonService;
import com.wxy8866.demo.marketing163.services.ITrxService;
import com.wxy8866.demo.marketing163.web.expression.ProductToBuyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxy8866 on 2017/4/14.
 */
@Controller
@RequestMapping(value = "/api")
public class AsynchronousAPIController
{
    private static final Logger logger = LoggerFactory.getLogger(AsynchronousAPIController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private IContentService contentService;

    @Autowired
    private ITrxService trxService;

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> verifyUser(Model model, @RequestParam(name = "userName") String username, @RequestParam(name = "password") String password)
    {
        logger.debug("In login api");
        logger.debug("username is " + username);
        logger.debug("password is " + password);
        Map<String, Object> map = new HashMap<String, Object>();
        Person person = personService.findByUsername(username);
        if (person == null)
        {
            map.put("code", "304");
            map.put("message", "User not exists");
            map.put("result", "false");
        } else if (password.equals(person.getPassword()))
        {
            map.put("code", "200");
            map.put("message", "OK");
            map.put("result", "true");
        } else
        {
            map.put("code", "304");
            map.put("message", "Bad password");
            map.put("result", "false");
        }

        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteContent(Model model, @RequestParam(name = "id") Integer id)
    {
        int result = contentService.delete(contentService.findById(id));
        Map<String, Object> map = new HashMap<String, Object>();

        if (result > 0)
        {
            map.put("code", "200");
            map.put("message", "OK");
            map.put("result", "true");
        } else
        {
            map.put("code", "201");
            map.put("message", "something wrong during delete content");
            map.put("result", "false");
        }
        return map;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> buyProduct(Model model, @RequestBody List<ProductToBuyVO> ProductToBuyVOList, Principal principal)
    {
        boolean finalStatus = true;

        Person person = personService.findByUsername(principal.getName());


        for (ProductToBuyVO product : ProductToBuyVOList)
        {
            Content content = contentService.findById(product.getId());
            Long result = trxService.insertBuyRecord(person.getId(), product.getId(), content.getPrice().intValue(), product.getNumber());
            logger.debug("insert result is {} ", result);
            if (!(result > 0))
            {
                finalStatus = false;
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (finalStatus)
        {
            map.put("code", "200");
            map.put("message", "OK");
            map.put("result", "true");

        } else
        {
            map.put("code", "201");
            map.put("message", "something wrong during buy");
            map.put("result", "false");
        }


        return map;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadPic(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request)
    {

        boolean resultIsGood = true;
        String filePath = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if (!file.isEmpty())
        {
            try
            {
                filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
                        + file.getOriginalFilename();
                logger.debug("saved file path is {}", file);
                file.transferTo(new File(filePath));
            } catch (IOException e)
            {
                resultIsGood = false;
                e.printStackTrace();
            }

        } else
        {
            resultIsGood = false;
        }

        if (resultIsGood)
        {
            map.put("code", "200");
            map.put("message", "OK");
            map.put("result", "upload/" + file.getOriginalFilename());
        } else
        {
            map.put("code", "201");
            map.put("message", "Error when upload files");
            map.put("result", "upload/" + file.getOriginalFilename());
        }
        return map;
    }

    @RequestMapping(path = "/users/{userid}")
    public Person getPerson(@PathVariable("userid") String userid, @RequestParam("userName") String
            username, @RequestParam("password") String md5Password)
    {
        return null;
    }
}
