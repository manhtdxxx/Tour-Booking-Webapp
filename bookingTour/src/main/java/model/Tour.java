package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Tour {
	private String maTour;
	private LoaiTour loaiTour;
	private String tenTour;
	private String diemXuatPhat;
	private String diemKetThuc;
	private String phuongTienDiChuyen;
	private Timestamp thoiGianXuatPhat;
	private Timestamp thoiGianKetThuc;
	private int giaVe;
	private int soLuongVeToiDa;
	private String moTa;

	public Tour() {
	}

	public Tour(String maTour, LoaiTour loaiTour, String tenTour, String diemXuatPhat, String diemKetThuc,
			String phuongTienDiChuyen, Timestamp thoiGianXuatPhat, Timestamp thoiGianKetThuc, int giaVe,
			int soLuongVeToiDa, String moTa) {
		this.maTour = maTour;
		this.loaiTour = loaiTour;
		this.tenTour = tenTour;
		this.diemXuatPhat = diemXuatPhat;
		this.diemKetThuc = diemKetThuc;
		this.phuongTienDiChuyen = phuongTienDiChuyen;
		this.thoiGianXuatPhat = thoiGianXuatPhat;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.giaVe = giaVe;
		this.soLuongVeToiDa = soLuongVeToiDa;
		this.moTa = moTa;
	}

	public String getMaTour() {
		return maTour;
	}

	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}

	public LoaiTour getLoaiTour() {
		return loaiTour;
	}

	public void setLoaiTour(LoaiTour loaiTour) {
		this.loaiTour = loaiTour;
	}

	public String getTenTour() {
		return tenTour;
	}

	public void setTenTour(String tenTour) {
		this.tenTour = tenTour;
	}

	public String getDiemXuatPhat() {
		return diemXuatPhat;
	}

	public void setDiemXuatPhat(String diemXuatPhat) {
		this.diemXuatPhat = diemXuatPhat;
	}

	public String getDiemKetThuc() {
		return diemKetThuc;
	}

	public void setDiemKetThuc(String diemKetThuc) {
		this.diemKetThuc = diemKetThuc;
	}

	public String getPhuongTienDiChuyen() {
		return phuongTienDiChuyen;
	}

	public void setPhuongTienDiChuyen(String phuongTienDiChuyen) {
		this.phuongTienDiChuyen = phuongTienDiChuyen;
	}

	public Timestamp getThoiGianXuatPhat() {
		return thoiGianXuatPhat;
	}

	public void setThoiGianXuatPhat(Timestamp thoiGianXuatPhat) {
		this.thoiGianXuatPhat = thoiGianXuatPhat;
	}

	public Timestamp getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(Timestamp thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public int getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(int giaVe) {
		this.giaVe = giaVe;
	}

	public int getSoLuongVeToiDa() {
		return soLuongVeToiDa;
	}

	public void setSoLuongVeToiDa(int soLuongVeToiDa) {
		this.soLuongVeToiDa = soLuongVeToiDa;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tour other = (Tour) obj;
		return Objects.equals(maTour, other.maTour);
	}

	@Override
	public String toString() {
		return "Tour [maTour=" + maTour + ", loaiTour=" + loaiTour + ", tenTour=" + tenTour + ", diemXuatPhat="
				+ diemXuatPhat + ", diemKetThuc=" + diemKetThuc + ", phuongTienDiChuyen=" + phuongTienDiChuyen
				+ ", thoiGianXuatPhat=" + thoiGianXuatPhat + ", thoiGianKetThuc=" + thoiGianKetThuc + ", giaVe=" + giaVe
				+ ", soLuongVeToiDa=" + soLuongVeToiDa + ", moTa=" + moTa + "]";
	}

}