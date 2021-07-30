package com.example.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";

    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        if(user.getMoneyForDeposit() != 0.0 && user.getMoneyForWithdraw() == 0.0){
            if(user.getMoneyForDeposit()>5.0 && user.getMoneyForDeposit()<10000.0){
                user.setBalance(user.getBalance()+user.getMoneyForDeposit());
                user.setMoneyForDeposit(0.0);
                service.save(user);
                ra.addFlashAttribute("message","The user (ID: " + user.getId() + ") deposit successfully.");
            }else{
                user.setMoneyForDeposit(0.0);
                service.save(user);
                ra.addFlashAttribute("message","The user (ID: " + user.getId() + ") deposit failure.");
            }
        }else if(user.getMoneyForDeposit() == 0.0 && user.getMoneyForWithdraw() != 0.0){
            if(user.getMoneyForWithdraw() <= user.getBalance()){
                user.setBalance(user.getBalance()-user.getMoneyForWithdraw());
                user.setMoneyForWithdraw(0.0);
                service.save(user);
                ra.addFlashAttribute("message","The user (ID: " + user.getId() + ") withdraw successfully.");
            }else{
                user.setMoneyForWithdraw(0.0);
                service.save(user);
                ra.addFlashAttribute("message","The user (ID: " + user.getId() + ") withdraw failure.");
            }
        }else{
            service.save(user);
            ra.addFlashAttribute("message","The user (ID: " + user.getId() + ") has been saved successfully.");
        }

        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }
    @GetMapping("/users/deposit/{id}")
    public String userDeposit(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Deposit Money (ID: " + id + ")");
            return "user_deposit";
        } catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/users/withdraw/{id}")
    public String userWithdraw(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Withdraw Money (ID: " + id + ")");
            return "user_withdraw";
        } catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
}
