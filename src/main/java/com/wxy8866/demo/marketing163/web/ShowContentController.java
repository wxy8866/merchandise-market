package com.wxy8866.demo.marketing163.web;

/**
 * Created by wxy8866 on 2017/4/13.
 */

import com.wxy8866.demo.marketing163.entities.ContentWithBLOBs;
import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.entities.Trx;
import com.wxy8866.demo.marketing163.services.IContentService;
import com.wxy8866.demo.marketing163.services.IPersonService;
import com.wxy8866.demo.marketing163.services.ITrxService;
import com.wxy8866.demo.marketing163.web.expression.ProductShowVO;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowContentController
{
    @Autowired
    private IPersonService personService;

    @Autowired
    private IContentService contentService;

    @Autowired
    private ITrxService trxService;

    private static final Logger logger = LoggerFactory.getLogger(ShowContentController.class);

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String showContent(ModelMap model, Principal principal, @RequestParam(name = "id") Integer productID)
    {
        //When user 1) is buyer and 2) has not bought this item
        boolean isCounterNeedToBeDisplayed = false;
        Person person = null;
        if (principal != null)
        {
            person = personService.findByUsername(principal.getName());
            if (person != null)
            {
                UserVO userVO = new UserVO();
                userVO.setUsername(person.getUsername());
                userVO.setUsertype(person.getUsertype().toString());
                model.addAttribute("user", userVO);
            }
        }


        ContentWithBLOBs content = contentService.findById(productID);

        ProductShowVO product = new ProductShowVO();
        product.setId(content.getId());
        product.setTitle(content.getTitle());
        product.setSummary(content.getSummary());
        product.setDetail(new String(content.getText()));
        product.setImage(new String(content.getIcon()));
        product.setPrice(content.getPrice());
        product.setSaleNum(trxService.getSaleNum(content.getId()));

        if (person != null)
        {
            List<Trx> transaction = trxService.getUserContentRecords(person.getId(), content.getId());
            if (transaction != null && transaction.size() > 0)
            {
                //System only limit you to buy an item once but many. Therefor, the price is the same in transaction list.
                Trx trx = transaction.get(0);
                product.setBuyPrice(trx.getPrice());
                product.setBuyNum(Long.valueOf(transaction.size()));
            } else if (person.getUsertype() == 0)
            {
                isCounterNeedToBeDisplayed = true;
                //Default value just for page display
                product.setBuyNum(1L);
            }
            product.setIsBuy(trxService.isUserBoughtContent(person.getId(), content.getId()));
        }
        product.setIsSell(trxService.isContentSold(content.getId()));

        model.addAttribute("product", product);
        model.addAttribute("isCounter", isCounterNeedToBeDisplayed);
        return "show";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showContentList(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal)
    {
        Person person = null;
        if (principal != null)
        {
            logger.debug("User is loggin: " + principal.getName());
            person = personService.findByUsername(principal.getName());
            if (person != null)
            {
                UserVO userVO = new UserVO();
                userVO.setUsername(person.getUsername());
                userVO.setUsertype(person.getUsertype().toString());
                model.addAttribute("user", userVO);
            }
        } else
        {
            logger.debug("principal is null");
        }

        List<ContentWithBLOBs> allContent = contentService.findAll();
        List<ProductShowVO> productList = new ArrayList<ProductShowVO>();
        for (ContentWithBLOBs content : allContent)
        {
            ProductShowVO product = new ProductShowVO();
            product.setId(content.getId());
            product.setTitle(content.getTitle());
            product.setImage(new String(content.getIcon()));
            product.setPrice(content.getPrice());
            product.setIsSell(trxService.isContentSold(content.getId()));
            if (person != null)
            {
                product.setIsBuy(trxService.isUserBoughtContent(person.getId(), content.getId()));
            }
            productList.add(product);
        }

        model.addAttribute("productList", productList);
        return "index";
    }
}
