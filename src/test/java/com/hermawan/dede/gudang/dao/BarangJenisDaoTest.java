package com.hermawan.dede.gudang.dao;

import com.hermawan.dede.gudang.entity.BarangJenis;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/BarangJenis.sql"
)
public class BarangJenisDaoTest {

    @Autowired
    private BarangJenisDao bjd;

    @Autowired
    private DataSource ds;

    @Test
    public void testInsert() throws SQLException {
        BarangJenis bj = new BarangJenis();
        bj.setKodeJenisBarang("AA");
        bj.setNamaJenisBarang("Peralatan");
        bjd.save(bj);

        String sql = "select count(*) as jumlah from t_mst_barang_jenis where kode_jenis_barang='AA'";

        Connection c = ds.getConnection();
        ResultSet rs = c.createStatement().executeQuery(sql);
        Assert.assertTrue(rs.next());

        Long jumlahRow = rs.getLong("jumlah");
        Assert.assertEquals(1L, jumlahRow.longValue());

        c.close();
    }

    @Test
    public void hitung() {
        Long jumlah = bjd.count();
        Assert.assertEquals(3L, jumlah.longValue());
    }

    @Test
    public void testCariById() {
        BarangJenis bj = bjd.findOne("AA1");
        Assert.assertEquals("Jenis1", bj.getNamaJenisBarang());
    }

    @After
    public void hapusData() throws SQLException {
        String sql = "delete from t_mst_barang_jenis where kode_jenis_barang = 'AA'";
        Connection c = ds.getConnection();
        c.createStatement().executeUpdate(sql);
    }

}
