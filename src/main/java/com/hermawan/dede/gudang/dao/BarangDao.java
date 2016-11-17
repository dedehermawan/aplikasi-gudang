package com.hermawan.dede.gudang.dao;

import com.hermawan.dede.gudang.entity.Barang;
import com.hermawan.dede.gudang.entity.BarangJenis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BarangDao extends PagingAndSortingRepository<Barang, String> {

    public Page<Barang> findByBarangJenis(BarangJenis bj, Pageable p);

    @Query ("select x from Barang x where x.barangJenis.namaJenisBarang = :nm") 
    /*
        Barang = nama class, 
        barangJenis = variabel pada class Barang, 
        namaJenisBarang = variabel pada class BarangJenis
    */
    public Page<Barang> cariBerdasarkanNamaBarangJenis(@Param("nm") String namaBarangJenis, Pageable p);
}
