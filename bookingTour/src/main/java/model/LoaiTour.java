package model;

import java.util.Objects;

public class LoaiTour {
	private String maLoaiTour;
	private String tenLoaiTour;

	public LoaiTour() {
	}

	public LoaiTour(String maLoaiTour, String tenLoaiTour) {
		this.maLoaiTour = maLoaiTour;
		this.tenLoaiTour = tenLoaiTour;
	}

	public String getMaLoaiTour() {
		return maLoaiTour;
	}

	public void setMaLoaiTour(String maLoaiTour) {
		this.maLoaiTour = maLoaiTour;
	}

	public String getTenLoaiTour() {
		return tenLoaiTour;
	}

	public void setTenLoaiTour(String tenLoaiTour) {
		this.tenLoaiTour = tenLoaiTour;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiTour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiTour other = (LoaiTour) obj;
		return Objects.equals(maLoaiTour, other.maLoaiTour);
	}

	@Override
	public String toString() {
		return "LoaiTour [maLoaiTour=" + maLoaiTour + ", tenLoaiTour=" + tenLoaiTour + "]";
	}
	
	
	
	

}
