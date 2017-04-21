package com.wxy8866.demo.marketing163.web;

import com.wxy8866.demo.marketing163.entities.ContentWithBLOBs;
import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.services.IContentService;
import com.wxy8866.demo.marketing163.services.IPersonService;
import com.wxy8866.demo.marketing163.web.expression.ProductPublicVO;
import com.wxy8866.demo.marketing163.web.expression.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by wxy8866 on 2017/4/13.
 */
@Controller
public class PublishContentController
{

    @Autowired
    private IPersonService personService;

    @Autowired
    private IContentService contentService;

    private static final Logger logger = LoggerFactory.getLogger(PublishContentController.class);

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String viewPublicContent(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal)
    {

        Person person = personService.findByUsername(principal.getName());
        if (person != null)
        {
            UserVO userVO = new UserVO();
            userVO.setUsername(person.getUsername());
            userVO.setUsertype(person.getUsertype().toString());
            model.addAttribute("user", userVO);
        }

        return "public";
    }

    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String submitPublicContent(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal, @RequestParam(name = "title")
            String title, @RequestParam(name = "image") byte[] imagePath, @RequestParam(name = "detail") byte[] detail, @RequestParam(name = "price") Long price,
                                      @RequestParam(name = "summary") String summary)
    {
        Person person = personService.findByUsername(principal.getName());
        if (person != null)
        {
            UserVO userVO = new UserVO();
            userVO.setUsername(person.getUsername());
            userVO.setUsertype(person.getUsertype().toString());
            model.addAttribute("user", userVO);
        }

        logger.debug("accept parameters' value:");
        logger.debug("title: {}", title);
        logger.debug("abstract: {}", summary);
        logger.debug("image path: {}", imagePath);
        logger.debug("details: {}", detail);
        logger.debug("price: {}", price);

        ContentWithBLOBs newContent = new ContentWithBLOBs();
        newContent.setTitle(title);
        newContent.setSummary(summary);
        newContent.setIcon(imagePath);
        newContent.setText(detail);
        newContent.setPrice(price);

        int rowAffected = contentService.create(newContent);

        logger.debug("genereated id is {}", newContent.getId());
        logger.debug("rows affected is {}", rowAffected);

        if (rowAffected == 1)
        {
            logger.debug("insert successful!");
            ProductPublicVO product = new ProductPublicVO();
            product.setId(newContent.getId());
            product.setTitle(title);
            product.setSummary(summary);
            product.setImage(new String(imagePath));
            product.setDetail(new String(detail));
            product.setPrice(price);
            model.addAttribute("product", product);
        }

        return "publicSubmit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editContentView(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal, @RequestParam(name = "id") Integer id)
    {
        Person person = personService.findByUsername(principal.getName());
        if (person != null)
        {
            UserVO userVO = new UserVO();
            userVO.setUsername(person.getUsername());
            userVO.setUsertype(person.getUsertype().toString());
            model.addAttribute("user", userVO);
        }

        ContentWithBLOBs content = contentService.findById(id);
        ProductPublicVO product = new ProductPublicVO();
        product.setId(content.getId());
        product.setTitle(content.getTitle());
        product.setSummary(content.getSummary());
        product.setImage(new String(content.getIcon()));
        product.setDetail(new String(content.getText()));
        product.setPrice(content.getPrice());


        model.addAttribute("product", product);
        return "edit";
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String updateContentSubmit(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal, @RequestParam(name = "id") Integer id, @RequestParam(name = "title")
            String title, @RequestParam(name = "image") byte[] imagePath, @RequestParam(name = "detail") byte[] text, @RequestParam(name = "price") Long price,
                                      @RequestParam(name = "summary") String summary)
    {
        Person person = personService.findByUsername(principal.getName());
        if (person != null)
        {
            UserVO userVO = new UserVO();
            userVO.setUsername(person.getUsername());
            userVO.setUsertype(person.getUsertype().toString());
            model.addAttribute("user", userVO);
        }

        ContentWithBLOBs content = contentService.findById(id);
        content.setTitle(title);
        content.setSummary(summary);
        content.setIcon(imagePath);
        content.setText(text);
        content.setPrice(price);
        int result = contentService.update(content);
        logger.debug("rows affected: {}", result);

        model.addAttribute("product", new ProductPublicVO(id, title, summary, new String(text), new String(imagePath), price));
        return "editSubmit";
    }
}