package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ChiTietDatTour;
import model.DatTour;
import model.Tour;

public class ChiTietDatTourDAO implements DAOInterface<ChiTietDatTour> {

	@Override
	public ArrayList<ChiTietDatTour> selectAll() {
		ArrayList<ChiTietDatTour> result = new ArrayList<ChiTietDatTour>();
		String sql = "SELECT * FROM chitietdattour";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maChiTietDatTour = rs.getString("maChiTietDatTour");
				String maTour = rs.getString("maTour");
				String maDatTour = rs.getString("maDatTour");
				long giaVeLucBooking = rs.getLong("giaVeLucBooking");
				int soLuongVeDat = rs.getInt("soLuongVeDat");
				long tongTien = rs.getLong("tongTien");

				Tour tour = new Tour();
				tour.setMaTour(maTour);
				tour = new TourDAO().selectById(tour);

				DatTour datTour = new DatTour();
				datTour.setMaDatTour(maDatTour);
				datTour = new DatTourDAO().selectById(datTour);

				ChiTietDatTour ctdt = new ChiTietDatTour(maChiTietDatTour, tour, datTour, giaVeLucBooking, soLuongVeDat,
						tongTien);
				result.add(ctdt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ChiTietDatTour selectById(ChiTietDatTour obj) {
		ChiTietDatTour result = null;
		String sql = "SELECT * FROM chitietdattour WHERE maChiTietDatTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaChiTietDatTour());

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maTour = rs.getString("maTour");
					String maDatTour = rs.getString("maDatTour");
					long giaVeLucBooking = rs.getLong("giaVeLucBooking");
					int soLuongVeDat = rs.getInt("soLuongVeDat");
					long tongTien = rs.getLong("tongTien");

					Tour tour = new Tour();
					tour.setMaTour(maTour);
					tour = new TourDAO().selectById(tour);

					DatTour datTour = new DatTour();
					datTour.setMaDatTour(maDatTour);
					datTour = new DatTourDAO().selectById(datTour);

					result = new ChiTietDatTour(obj.getMaChiTietDatTour(), tour, datTour, giaVeLucBooking, soLuongVeDat,
							tongTien);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(ChiTietDatTour obj) {
		int result = 0;
		String sql = "INSERT INTO chitietdattour (maChiTietDatTour, maTour, maDatTour, giaVeLucBooking, soLuongVeDat, tongTien) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaChiTietDatTour());
			st.setString(2, obj.getTour().getMaTour());
			st.setString(3, obj.getDatTour().getMaDatTour());
			st.setLong(4, obj.getGiaVeLucBooking());
			st.setInt(5, obj.getSoLuongVeDat());
			st.setLong(6, obj.getTongTien());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<ChiTietDatTour> objs) {
		int result = 0;
		String sql = "INSERT INTO chitietdattour (maChiTietDatTour, maTour, maDatTour, giaVeLucBooking, soLuongVeDat, tongTien) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (ChiTietDatTour obj : objs) {
				st.setString(1, obj.getMaChiTietDatTour());
				st.setString(2, obj.getTour().getMaTour());
				st.setString(3, obj.getDatTour().getMaDatTour());
				st.setLong(4, obj.getGiaVeLucBooking());
				st.setInt(5, obj.getSoLuongVeDat());
				st.setLong(6, obj.getTongTien());
				//
				st.addBatch();
			}

			int[] results = st.executeBatch();
			conn.commit();

			for (int count : results) {
				result += count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(ChiTietDatTour obj) {
		int result = 0;
		String sql = "DELETE FROM chitietdattour WHERE maChiTietDatTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaChiTietDatTour());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAll(ArrayList<ChiTietDatTour> objs) {
		int result = 0;
		String sql = "DELETE FROM chitietdattour WHERE maChiTietDatTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (ChiTietDatTour obj : objs) {
				st.setString(1, obj.getMaChiTietDatTour());
				//
				st.addBatch();
			}

			int[] results = st.executeBatch();
			conn.commit();

			for (int count : results) {
				result += count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(ChiTietDatTour obj) {
		int result = 0;
		String sql = "UPDATE chitietdattour SET maTour = ?, maDatTour = ?, giaVeLucBooking = ?, soLuongVeDat = ?, tongTien = ? WHERE maChiTietDatTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTour().getMaTour());
			st.setString(2, obj.getDatTour().getMaDatTour());
			st.setLong(3, obj.getGiaVeLucBooking());
			st.setInt(4, obj.getSoLuongVeDat());
			st.setLong(5, obj.getTongTien());
			st.setString(6, obj.getMaChiTietDatTour());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getLastMaChiTietDatTour() {
		String latestMaChiTietDatTour = null;
		String sql = "SELECT maChiTietDatTour FROM chitietdattour ORDER BY maChiTietDatTour DESC LIMIT 1";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			if (rs.next()) {
				latestMaChiTietDatTour = rs.getString("maChiTietDatTour");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return latestMaChiTietDatTour;
	}

	public String generateNewMaChiTietDatTour() {
		String latestMaChiTietDatTour = getLastMaChiTietDatTour();

		if (latestMaChiTietDatTour == null) {
			return "CTDT001";
		}
		int num = Integer.parseInt(latestMaChiTietDatTour.substring(4)) + 1;
		latestMaChiTietDatTour = String.format("CTDT%03d", num);
		return latestMaChiTietDatTour;
	}

	public int getTotalSoLuongVeDatByMaTour(String maTour) {
		int totalSoLuongVeDat = 0;
		String sql = "SELECT SUM(soLuongVeDat) AS totalSoLuongVeDat FROM chitietdattour WHERE maTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, maTour);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					totalSoLuongVeDat = rs.getInt("totalSoLuongVeDat");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalSoLuongVeDat;
	}

	public ArrayList<ChiTietDatTour> selectChiTietDatToursByMaDatTour(String maDatTour) {
		ArrayList<ChiTietDatTour> result = new ArrayList<>();
		String sql = "SELECT * FROM chitietdattour WHERE maDatTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, maDatTour);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String maChiTietDatTour = rs.getString("maChiTietDatTour");
					String maTour = rs.getString("maTour");
					long giaVeLucBooking = rs.getLong("giaVeLucBooking");
					int soLuongVeDat = rs.getInt("soLuongVeDat");
					long tongTien = rs.getLong("tongTien");

					Tour tour = new Tour();
					tour.setMaTour(maTour);
					tour = new TourDAO().selectById(tour);

					DatTour datTour = new DatTour();
					datTour.setMaDatTour(maDatTour);
					datTour = new DatTourDAO().selectById(datTour);

					ChiTietDatTour ctdt = new ChiTietDatTour(maChiTietDatTour, tour, datTour, giaVeLucBooking,
							soLuongVeDat, tongTien);
					result.add(ctdt);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
