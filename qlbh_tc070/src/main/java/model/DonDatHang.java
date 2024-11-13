package model;

import java.sql.Date;
import java.util.Objects;

public class DonDatHang {
	private String soHoaDon;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Date ngayDatHang;
	private String tenHang;
	private int soLuong;
	private int gia;

	public DonDatHang() {
	}

	public DonDatHang(String soHoaDon, KhachHang khachHang, NhanVien nhanVien, Date ngayDatHang, String tenHang,
			int soLuong, int gia) {
		this.soHoaDon = soHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayDatHang = ngayDatHang;
		this.tenHang = tenHang;
		this.soLuong = soLuong;
		this.gia = gia;
	}

	public String getSoHoaDon() {
		return soHoaDon;
	}

	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Date getNgayDatHang() {
		return ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(soHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonDatHang other = (DonDatHang) obj;
		return Objects.equals(soHoaDon, other.soHoaDon);
	}

	@Override
	public String toString() {
		return "DonDatHang [soHoaDon=" + soHoaDon + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", ngayDatHang=" + ngayDatHang + ", tenHang=" + tenHang + ", soLuong=" + soLuong + ", gia=" + gia
				+ "]";
	}

}
