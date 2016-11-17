package com.hermawan.dede.gudang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_mst_barang")
public class Barang {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column (nullable = false, unique = true)
    private String kodeBarang;
    
    @Column (nullable = false)
    private String namaBarang;
    
    @ManyToOne
    @JoinColumn(name = "kode_jenis_barang", nullable = false)
    private BarangJenis barangJenis;
    
    private boolean aktif;

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public BarangJenis getBarangJenis() {
        return barangJenis;
    }

    public void setBarangJenis(BarangJenis barangJenis) {
        this.barangJenis = barangJenis;
    }
    
    
}
