package com.hermawan.dede.gudang.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HaloController {

    @RequestMapping("/halo")
    public Map<String, Object> halo(@RequestParam(required = false)String nama) {
        Map<String, Object> hasil = new HashMap<String, Object>();
        hasil.put("waktu ", new Date());
        hasil.put("nama", nama);
        return hasil;
    }
}
