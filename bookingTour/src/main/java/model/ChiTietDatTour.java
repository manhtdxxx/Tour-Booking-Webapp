package model;

import java.util.Objects;

public class ChiTietDatTour {
	private String maChiTietDatTour;
	private Tour tour;
	private DatTour datTour;
	private int giaVeLucBooking;
	private int soLuongVe;
	private int tongTien;

	public ChiTietDatTour() {
	}

	public ChiTietDatTour(String maChiTietDatTour, Tour tour, DatTour datTour, int giaVeLucBooking, int soLuongVe,
			int tongTien) {
		this.maChiTietDatTour = maChiTietDatTour;
		this.tour = tour;
		this.datTour = datTour;
		this.giaVeLucBooking = giaVeLucBooking;
		this.soLuongVe = soLuongVe;
		this.tongTien = tongTien;
	}

	public String getMaChiTietDatTour() {
		return maChiTietDatTour;
	}

	public void setMaChiTietDatTour(String maChiTietDatTour) {
		this.maChiTietDatTour = maChiTietDatTour;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public DatTour getDatTour() {
		return datTour;
	}

	public void setDatTour(DatTour datTour) {
		this.datTour = datTour;
	}

	public int getGiaVeLucBooking() {
		return giaVeLucBooking;
	}

	public void setGiaVeLucBooking(int giaVeLucBooking) {
		this.giaVeLucBooking = giaVeLucBooking;
	}

	public int getSoLuongVe() {
		return soLuongVe;
	}

	public void setSoLuongVe(int soLuongVe) {
		this.soLuongVe = soLuongVe;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChiTietDatTour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDatTour other = (ChiTietDatTour) obj;
		return Objects.equals(maChiTietDatTour, other.maChiTietDatTour);
	}

	@Override
	public String toString() {
		return "ChiTietDatTour [maChiTietDatTour=" + maChiTietDatTour + ", tour=" + tour + ", datTour=" + datTour
				+ ", giaVeLucBooking=" + giaVeLucBooking + ", soLuongVe=" + soLuongVe + ", tongTien=" + tongTien + "]";
	}

}
