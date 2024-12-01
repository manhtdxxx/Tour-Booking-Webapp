package model;

import java.sql.Timestamp;
import java.util.Objects;

public class DatTour {
	private String maDatTour;
	private KhachHang khachHang;
	private Timestamp thoiGianDatTour;
	private String hinhThucThanhToan;
	private String ghiChu;
	private String trangThaiDatTour;

	public DatTour() {
	}

	public DatTour(String maDatTour, KhachHang khachHang, Timestamp thoiGianDatTour, String hinhThucThanhToan,
			String ghiChu, String trangThaiDatTour) {
		this.maDatTour = maDatTour;
		this.khachHang = khachHang;
		this.thoiGianDatTour = thoiGianDatTour;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.ghiChu = ghiChu;
		this.trangThaiDatTour = trangThaiDatTour;
	}

	public String getMaDatTour() {
		return maDatTour;
	}

	public void setMaDatTour(String maDatTour) {
		this.maDatTour = maDatTour;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Timestamp getThoiGianDatTour() {
		return thoiGianDatTour;
	}

	public void setThoiGianDatTour(Timestamp thoiGianDatTour) {
		this.thoiGianDatTour = thoiGianDatTour;
	}

	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}

	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getTrangThaiDatTour() {
		return trangThaiDatTour;
	}

	public void setTrangThaiDatTour(String trangThaiDatTour) {
		this.trangThaiDatTour = trangThaiDatTour;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDatTour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatTour other = (DatTour) obj;
		return Objects.equals(maDatTour, other.maDatTour);
	}
}
