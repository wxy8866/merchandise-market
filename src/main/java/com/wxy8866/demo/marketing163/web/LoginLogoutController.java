package com.wxy8866.demo.marketing163.web;

import com.wxy8866.demo.marketing163.services.IPersonService;
import com.wxy8866.demo.marketing163.validator.UserValidator;
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


/**
 * Created by wxy8866 on 2017/4/11.
 */
@Controller
public class LoginLogoutController
{
    private static final Logger logger = LoggerFactory.getLogger(LoginLogoutController.class);

    @Autowired
    private IPersonService personService;
    @Autowired
    private UserValidator  userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal, String error, String logout) throws Exception
    {
        logger.trace("in login controller!");
        if (error != null)
        {
            model.addAttribute("error", "Your username and password is invalid.");
            return "/login";
        }

        if (logout != null)
        {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        if (principal != null)
        {
            logger.debug("user is already loggon return landing apge");
            response.sendRedirect("/");
        } else
        {
            logger.debug("user has not logged in return login page");

        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(ModelMap model)
    {
        return "login";
    }

}
