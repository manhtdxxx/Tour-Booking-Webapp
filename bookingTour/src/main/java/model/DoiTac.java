package model;

import java.util.Objects;

public class DoiTac {
	private String maDoiTac;
	private String tenDoiTac;
	private String diaChi;
	private String soDienThoai;
	private String email;
	private String moTa;

	public DoiTac() {
	}

	public DoiTac(String maDoiTac, String tenDoiTac, String diaChi, String soDienThoai, String email, String moTa) {
		this.maDoiTac = maDoiTac;
		this.tenDoiTac = tenDoiTac;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.moTa = moTa;
	}

	public String getMaDoiTac() {
		return maDoiTac;
	}

	public void setMaDoiTac(String maDoiTac) {
		this.maDoiTac = maDoiTac;
	}

	public String getTenDoiTac() {
		return tenDoiTac;
	}

	public void setTenDoiTac(String tenDoiTac) {
		this.tenDoiTac = tenDoiTac;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDoiTac);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoiTac other = (DoiTac) obj;
		return Objects.equals(maDoiTac, other.maDoiTac);
	}

	@Override
	public String toString() {
		return "DoiTac [maDoiTac=" + maDoiTac + ", tenDoiTac=" + tenDoiTac + ", diaChi=" + diaChi + ", soDienThoai="
				+ soDienThoai + ", email=" + email + ", moTa=" + moTa + "]";
	}

}
