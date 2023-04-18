package com.example.demo2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo2.dto.UserRequest;
import com.example.demo2.entity.User;
import com.example.demo2.service.UserService;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {

    /**
     * ユーザー情報 Service
     */
    @Autowired
    UserService userService;

    /**
     * ユーザー情報一覧画面を表示
     * 
     * @param model Model
     * @return ユーザー情報一覧画面のHTML
     */
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String displayList(Model model) {
        List<User> userlist = userService.searchAll();
        model.addAttribute("userlist", userlist);
        return "user/list";
    }

    /**
     * ユーザー新規登録画面を表示
     * 
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    // 未登録
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String displayAdd(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        // user/add.htmlを表示
        return "user/add";
    }

    /**
     * ユーザー新規登録
     * 
     * @param userRequest リクエストデータ
     * @param model       Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "user/add";
        }
        // ユーザー情報の登録
        userService.create(userRequest);
        return "redirect:/user/list";
    }

}