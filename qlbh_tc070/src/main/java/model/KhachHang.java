package model;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String hoTen;
	private String diaChi;
	private String email;
	private String dienThoai;

	public KhachHang() {
	}

	public KhachHang(String maKH, String hoTen, String diaChi, String email, String dienThoai) {
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.email = email;
		this.dienThoai = dienThoai;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
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
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", email=" + email
				+ ", dienThoai=" + dienThoai + "]";
	}

}
