## Akses Database dengan Spring Data JPA ##
### Setup Project ###
* 'pom.xml' : tambahkan parent project ke spring boot
'''xml
	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
'''

* 'pom.xml' : tambahkan dependensi Spring Data JPA
'''xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
'''

* 'pom.xml' : tambahkan dependensi database (misal : MySQL)
'''xml
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency>
'''

* 'src/main/resources/application.properties' : Konfigurasi koneksi database
'''
	spring.datasource.url=jdbc:mysql://localhost/aplikasi_gudang
	spring.datasource.username=gudanguser
	spring.datasource.password=gudangpaswd

	spring.jpa.generate-ddl=true
'''

* Entity class dengan annotation '@Entity'. Contoh
'''java
	@Entity
	@Table(name = "t_mst_barang_jenis")
	public class BarangJenis {

		@Id
		private String id;

		@Column (nullable = false, unique = true)
		private String kodeJenisBarang;

		@Column (nullable = false)
		private String namaJenisBarang;

	}
'''

* Siapkan user dan password untuk koneksi database
'''
	grant all on aplikasi_gudang.* to gudanguser@localhost identified by 'gudangpaswd'
'''

* Buat database
'''
	create database aplikasi_gudang
'''

* Jalankan projectnya
'''
	mvn clean package
'''


### Test Kode Program Akses Database ###

* Siapkan sample data dalam file sql
'''sql
delete from t_mst_barang_jenis;

insert into t_mst_barang_jenis (kode_jenis_barang,nama_jenis_barang)
values ('AA1','Jenis1');

insert into t_mst_barang_jenis (kode_jenis_barang,nama_jenis_barang)
values ('AA2','Jenis2');

insert into t_mst_barang_jenis (kode_jenis_barang,nama_jenis_barang)
values ('AA3','Jenis3');
'''

* Panggil file SQL dari dalam JUnit
'''java
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/BarangJenis.sql"
)
public class BarangJenisDaoTest {
'''

* Lakukan pengetesan dalam method '@Test'
	* contoh tes insert
	'''java
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
	'''
	
	* contoh test select by id
	'''java
		@Test
		public void testCariById() {
			BarangJenis bj = bjd.findOne("AA1");
			Assert.assertEquals("Jenis1", bj.getNamaJenisBarang());
		}
	'''
	
	* contoh test select count
	'''java
		@Test
		public void hitung() {
			Long jumlah = bjd.count();
			Assert.assertEquals(3L, jumlah.longValue());
		}
	'''
	
* Bersihkan sisa test insert dengan method '@After'
'''java
	@After
    public void hapusData() throws SQLException {
        String sql = "delete from t_mst_barang_jenis where kode_jenis_barang = 'AA'";
        Connection c = ds.getConnection();
        c.createStatement().executeUpdate(sql);
    }
'''	