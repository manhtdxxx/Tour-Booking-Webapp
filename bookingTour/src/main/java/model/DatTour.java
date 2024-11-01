package model;

import java.sql.Timestamp;
import java.util.Objects;

public class DatTour {
	private String maDatTour;
	private KhachHang khachHang;
	private Timestamp thoiGianDatTour;
	private String trangThaiThanhToan;
	private Timestamp thoiGianThanhToan;
	private String hinhThucThanhToan;
	private String ghiChu;

	public DatTour() {
	}

	public DatTour(String maDatTour, KhachHang khachHang, Timestamp thoiGianDatTour, String trangThaiThanhToan,
			Timestamp thoiGianThanhToan, String hinhThucThanhToan, String ghiChu) {
		this.maDatTour = maDatTour;
		this.khachHang = khachHang;
		this.thoiGianDatTour = thoiGianDatTour;
		this.trangThaiThanhToan = trangThaiThanhToan;
		this.thoiGianThanhToan = thoiGianThanhToan;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.ghiChu = ghiChu;
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

	public String getTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(String trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	public Timestamp getThoiGianThanhToan() {
		return thoiGianThanhToan;
	}

	public void setThoiGianThanhToan(Timestamp thoiGianThanhToan) {
		this.thoiGianThanhToan = thoiGianThanhToan;
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

	@Override
	public String toString() {
		return "DatTour [maDatTour=" + maDatTour + ", khachHang=" + khachHang + ", thoiGianDatTour=" + thoiGianDatTour
				+ ", trangThaiThanhToan=" + trangThaiThanhToan + ", thoiGianThanhToan=" + thoiGianThanhToan
				+ ", hinhThucThanhToan=" + hinhThucThanhToan + ", ghiChu=" + ghiChu + "]";
	}

}
