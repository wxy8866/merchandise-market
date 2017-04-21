package com.wxy8866.demo.marketing163.web;

import com.wxy8866.demo.marketing163.entities.ContentWithBLOBs;
import com.wxy8866.demo.marketing163.entities.Person;
import com.wxy8866.demo.marketing163.entities.Trx;
import com.wxy8866.demo.marketing163.services.IContentService;
import com.wxy8866.demo.marketing163.services.IPersonService;
import com.wxy8866.demo.marketing163.services.ITrxService;
import com.wxy8866.demo.marketing163.web.expression.ProductBoughtVO;
import com.wxy8866.demo.marketing163.web.expression.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wxy8866 on 2017/4/13.
 */
@Controller

public class ShowAccountController
{
    private static final Logger logger = LoggerFactory.getLogger(ShowAccountController.class);

    @Autowired
    private IPersonService personService;

    @Autowired
    private ITrxService trxService;

    @Autowired
    private IContentService contentService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccount(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal)
    {
        Person person = personService.findByUsername(principal.getName());
        if (person != null)
        {
            UserVO userVO = new UserVO();
            userVO.setUsername(person.getUsername());
            userVO.setUsertype(person.getUsertype().toString());
            model.addAttribute("user", userVO);

            List<Trx> userBuyHistory = trxService.getUserBuyHistory(person.getId());

            List<ProductBoughtVO> productBoughtList = new ArrayList<ProductBoughtVO>();

            Set<Integer> contentIdSet = new HashSet<Integer>();

            for (Trx record : userBuyHistory)
            {
                if (contentIdSet.contains(record.getContentid()))
                {
                    continue;
                }

                ContentWithBLOBs content = contentService.findById(record.getContentid());
                ProductBoughtVO product = new ProductBoughtVO();
                product.setId(content.getId());
                product.setTitle(content.getTitle());
                product.setImage(new String(content.getIcon()));
                product.setBuyPrice(record.getPrice());
                product.setBuyNum(Long.valueOf(trxService.getUserContentRecords(person.getId(), content.getId()).size()));
                product.setBuyTime(record.getTime());
                productBoughtList.add(product);
                contentIdSet.add(record.getContentid());
            }

            model.addAttribute("buyList", productBoughtList);
        }
        return "account";
    }

    @RequestMapping(value = "/settleAccount", method = RequestMethod.GET)
    public String showShoppingCart(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal)
    {
        Person person = personService.findByUsername(principal.getName());
        if (person != null)
        {
            UserVO userVO = new UserVO();
            userVO.setUsername(person.getUsername());
            userVO.setUsertype(person.getUsertype().toString());
            model.addAttribute("user", userVO);
        }
        return "settleAccount";
    }
}
