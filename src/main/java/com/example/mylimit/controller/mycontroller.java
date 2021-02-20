package com.example.mylimit.controller;

import com.example.mylimit.bean.China;
import com.example.mylimit.mymapper.Chinamapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class mycontroller {
    @Autowired
    Chinamapper chinamapper;

    @RequestMapping("/getAll")
    public PageInfo<China> getAllUser(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        ArrayList<China> all = chinamapper.getAll();
        System.out.println(all);
        PageInfo<China> pageInfo = new PageInfo<>(all);
        model.addAttribute("pageInfo",pageInfo);
        return pageInfo;
    }
    @RequestMapping("/getone")
    public ArrayList<China> get(){
        ArrayList<China> all = chinamapper.getAll();
        System.out.println(all);
        return chinamapper.getOne();
    }

}
