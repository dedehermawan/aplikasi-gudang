package com.hermawan.dede.gudang.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hermawan.dede.gudang.dao.BarangJenisDao;
import com.hermawan.dede.gudang.entity.BarangJenis;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BarangJenisController {

    @Autowired
    BarangJenisDao bjd;

    @RequestMapping(value = "/barangjenis", method = RequestMethod.GET)
    @JsonIgnore
    public Page<BarangJenis> cariBarangJenis(Pageable page) {
        return bjd.findAll(page);
    }

    @RequestMapping(value = "/barangjenis", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void simpanBarangJenis(@RequestBody @Valid BarangJenis bj) {
        bjd.save(bj);
    }

    @RequestMapping(value = "/barangjenis/{kodeBarangJenis}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateBarangJenis(@PathVariable("kodeBarangJenis") String kodeBarangJenis, @RequestBody @Valid BarangJenis bj) {
        bj.setKodeJenisBarang(kodeBarangJenis);
        bjd.save(bj);
    }

    @RequestMapping(value = "/barangjenis/{kodeBarangJenis}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BarangJenis> cariBarangJenisByKodeBarangJenis(@PathVariable("kodeBarangJenis") String kodeBarangJenis) {
        BarangJenis hasil = bjd.findOne(kodeBarangJenis);
        if (hasil == null) {
            return new ResponseEntity<BarangJenis>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<BarangJenis>(hasil, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/barangjenis/{kodeBarangJenis}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusBarangJenis(@PathVariable("kodeBarangJenis") String kodeBarangJenis) {
        bjd.delete(kodeBarangJenis);
    }

}
