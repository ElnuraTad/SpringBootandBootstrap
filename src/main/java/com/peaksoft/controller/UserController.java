package com.peaksoft.controller;

import com.peaksoft.model.User;
import com.peaksoft.service.RoleService;
import com.peaksoft.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping
    public String loginPage() {
        return "/login-page";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "all-users-page";
    }

    @GetMapping("delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("{id}/edit")
    public String getUserByIdForEditUser2(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit-users";
    }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam Map<String, String> form) {
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserMethod(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "user-page";
    }

    @GetMapping("/new")
    public String setUserForCreationUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("role", roleService.getAllRoles());
        return "new-user";
    }

    @PostMapping("/creation")
    public String createNewUser(User user, @RequestParam Map<String, String> form) {
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
}