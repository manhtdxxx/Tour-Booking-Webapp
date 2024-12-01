package model;

import java.util.Objects;

public class ChiTietDatTour {
	private String maChiTietDatTour;
	private Tour tour;
	private DatTour datTour;
	private long giaVeLucBooking;
	private int soLuongVeDat;
	private long tongTien;

	public ChiTietDatTour() {
	}

	public ChiTietDatTour(String maChiTietDatTour, Tour tour, DatTour datTour, long giaVeLucBooking, int soLuongVeDat,
			long tongTien) {
		this.maChiTietDatTour = maChiTietDatTour;
		this.tour = tour;
		this.datTour = datTour;
		this.giaVeLucBooking = giaVeLucBooking;
		this.soLuongVeDat = soLuongVeDat;
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

	public long getGiaVeLucBooking() {
		return giaVeLucBooking;
	}

	public void setGiaVeLucBooking(long giaVeLucBooking) {
		this.giaVeLucBooking = giaVeLucBooking;
	}

	public int getSoLuongVeDat() {
		return soLuongVeDat;
	}

	public void setSoLuongVeDat(int soLuongVe) {
		this.soLuongVeDat = soLuongVe;
	}

	public long getTongTien() {
		return tongTien;
	}

	public void setTongTien(long tongTien) {
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
				+ ", giaVeLucBooking=" + giaVeLucBooking + ", soLuongVeDat=" + soLuongVeDat + ", tongTien=" + tongTien
				+ "]";
	}

}
