package model;

import java.util.Objects;

public class VaiTroDoiTac {
	private Tour tour;
	private DoiTac doiTac;
	private String vaiTro;

	public VaiTroDoiTac() {
	}

	public VaiTroDoiTac(Tour tour, DoiTac doiTac, String vaiTro) {
		this.tour = tour;
		this.doiTac = doiTac;
		this.vaiTro = vaiTro;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public DoiTac getDoiTac() {
		return doiTac;
	}

	public void setDoiTac(DoiTac doiTac) {
		this.doiTac = doiTac;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doiTac, tour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VaiTroDoiTac other = (VaiTroDoiTac) obj;
		return Objects.equals(doiTac, other.doiTac) && Objects.equals(tour, other.tour);
	}

	@Override
	public String toString() {
		return "VaiTroDoiTac [tour=" + tour + ", doiTac=" + doiTac + ", vaiTro=" + vaiTro + "]";
	}

}
