package com.hermawan.dede.gudang.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_mst_barang_jenis")
public class BarangJenis {

    @Id
    @Column(length = 3)
    private String kodeJenisBarang;

    @Column(nullable = false)
    private String namaJenisBarang;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "barangJenis"
    )
    private List<Barang> daftarBarang = new ArrayList<Barang>();

    public String getKodeJenisBarang() {
        return kodeJenisBarang;
    }

    public void setKodeJenisBarang(String kodeJenisBarang) {
        this.kodeJenisBarang = kodeJenisBarang;
    }

    public String getNamaJenisBarang() {
        return namaJenisBarang;
    }

    public void setNamaJenisBarang(String namaJenisBarang) {
        this.namaJenisBarang = namaJenisBarang;
    }

    public List<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    public void setDaftarBarang(List<Barang> daftarBarang) {
        this.daftarBarang = daftarBarang;
    }

}
