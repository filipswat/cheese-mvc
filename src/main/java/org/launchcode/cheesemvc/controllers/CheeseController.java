package org.launchcode.cheesemvc.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;
import java.util.HashMap;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    @RequestMapping(value = "")

    public String index(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDesc, Model model) {
        if (Pattern.matches("[a-zA-z]([a-zA-z\\s])*", cheeseName)) {
            cheeses.put(cheeseName, cheeseDesc);
            return "redirect:";
        } else {
            model.addAttribute("errorMessage", "Name must start with a letter and can only contain letters and spaces.");
            return "cheese/add";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        return "cheese/remove";
    }

    @RequestMapping(value ="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(HttpServletRequest request) {
        String[] cheeseName = request.getParameterValues("cheeseName");

        if (cheeseName != null) {
            for (String cheese : cheeseName) {
                cheeses.remove(cheese);
            }
        }

        return "redirect:";
    }
}
