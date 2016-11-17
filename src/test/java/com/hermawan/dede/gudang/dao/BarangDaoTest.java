package com.hermawan.dede.gudang.dao;

import com.hermawan.dede.gudang.entity.Barang;
import com.hermawan.dede.gudang.entity.BarangJenis;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/data/BarangJenis.sql", "/data/Barang.sql"}
)
public class BarangDaoTest {

    @Autowired
    private BarangDao bjd;

    @Test
    public void testCariByBarangJenis() {
        BarangJenis bj = new BarangJenis();
        bj.setKodeJenisBarang("AA3");

        PageRequest page = new PageRequest(0, 5);

        Page<Barang> hasilQuery = bjd.findByBarangJenis(bj, page);
        Assert.assertEquals(1L, hasilQuery.getTotalElements());

        Barang b = hasilQuery.getContent().get(0);
        Assert.assertNotNull(b);
        Assert.assertEquals("Jenis3", b.getBarangJenis().getNamaJenisBarang());
    }

    @Test
    public void testCariBerdasarkanNamaBarangJenis() {

        PageRequest page = new PageRequest(0, 5);
        Page<Barang> hasilQuery = bjd.cariBerdasarkanNamaBarangJenis("Jenis2", page);
        Assert.assertEquals(2L, hasilQuery.getTotalElements());
        
        Barang b = hasilQuery.getContent().get(0);
        Assert.assertEquals("AA2", b.getBarangJenis().getKodeJenisBarang());
        System.out.println(b);
    }

}
