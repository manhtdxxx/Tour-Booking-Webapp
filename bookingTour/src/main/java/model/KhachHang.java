package model;

import java.sql.Date;
import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String username;
	private String password;
	private String gioiTinh;
	private Date ngaySinh;
	private String soDienThoai;
	private String email;
	private String status;

	public KhachHang() {
	}

	public KhachHang(String maKH, String tenKH, String username, String password, String gioiTinh, Date ngaySinh,
			String soDienThoai, String email) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.username = username;
		this.password = password;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
	}

	// Constructor includes "status" column
	public KhachHang(String maKH, String tenKH, String username, String password, String gioiTinh, Date ngaySinh,
			String soDienThoai, String email, String status) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.username = username;
		this.password = password;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.status = status;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", username=" + username + ", password=" + password
				+ ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", email="
				+ email + "]";
	}

}
