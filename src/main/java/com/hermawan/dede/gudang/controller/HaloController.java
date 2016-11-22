package com.hermawan.dede.gudang.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HaloController {

    @RequestMapping("/halo")
    public void haloHtml(@RequestParam(required = false) String nama, Model model) {
        model.addAttribute("waktu", new Date());
        model.addAttribute("nama", nama);
    }
}
