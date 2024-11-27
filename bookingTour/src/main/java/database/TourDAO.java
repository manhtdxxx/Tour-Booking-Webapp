package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.LoaiTour;
import model.Tour;

public class TourDAO implements DAO_Interface<Tour> {

	@Override
	public ArrayList<Tour> selectAll() {
		ArrayList<Tour> result = new ArrayList<Tour>();
		String sql = "SELECT * FROM tour";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maTour = rs.getString("maTour");
				String maLoaiTour = rs.getString("maLoaiTour");
				String tenTour = rs.getString("tenTour");
				String diemXuatPhat = rs.getString("diemXuatPhat");
				String diemKetThuc = rs.getString("diemKetThuc");
				String phuongTienDiChuyen = rs.getString("phuongTienDiChuyen");
				Timestamp thoiGianXuatPhat = rs.getTimestamp("thoiGianXuatPhat");
				Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
				int soLuongVeToiDa = rs.getInt("soLuongVeToiDa");
				int soLuongVeHienCo = rs.getInt("soLuongVeHienCo");
				long giaVeHienTai = rs.getLong("giaVeHienTai");
				long giaVeLucTruoc = rs.getLong("giaVeLucTruoc");
				String moTa = rs.getString("moTa");
				String fileName = rs.getString("fileName");

				LoaiTour loaiTour = new LoaiTour();
				loaiTour.setMaLoaiTour(maLoaiTour);
				loaiTour = new LoaiTourDAO().selectById(loaiTour);

				Tour tour = new Tour(maTour, loaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen,
						thoiGianXuatPhat, thoiGianKetThuc, giaVeHienTai, giaVeLucTruoc, soLuongVeToiDa, soLuongVeHienCo,
						moTa, fileName);
				result.add(tour);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Tour selectById(Tour obj) {
		Tour result = null;
		String sql = "SELECT * FROM tour WHERE maTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaTour());

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maTour = rs.getString("maTour");
					String maLoaiTour = rs.getString("maLoaiTour");
					String tenTour = rs.getString("tenTour");
					String diemXuatPhat = rs.getString("diemXuatPhat");
					String diemKetThuc = rs.getString("diemKetThuc");
					String phuongTienDiChuyen = rs.getString("phuongTienDiChuyen");
					Timestamp thoiGianXuatPhat = rs.getTimestamp("thoiGianXuatPhat");
					Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
					int soLuongVeToiDa = rs.getInt("soLuongVeToiDa");
					int soLuongVeHienCo = rs.getInt("soLuongVeHienCo");
					long giaVeHienTai = rs.getLong("giaVeHienTai");
					long giaVeLucTruoc = rs.getLong("giaVeLucTruoc");
					String moTa = rs.getString("moTa");
					String fileName = rs.getString("fileName");

					LoaiTour loaiTour = new LoaiTour();
					loaiTour.setMaLoaiTour(maLoaiTour);
					loaiTour = new LoaiTourDAO().selectById(loaiTour);

					result = new Tour(maTour, loaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen,
							thoiGianXuatPhat, thoiGianKetThuc, giaVeHienTai, giaVeLucTruoc, soLuongVeToiDa,
							soLuongVeHienCo, moTa, fileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(Tour obj) {
		int result = 0;
		String sql = "INSERT INTO tour (maTour, maLoaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen, thoiGianXuatPhat, thoiGianKetThuc, soLuongVeToiDa, soLuongVeHienCo, giaVeHienTai, giaVeLucTruoc, moTa, fileName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaTour());
			st.setString(2, obj.getLoaiTour().getMaLoaiTour());
			st.setString(3, obj.getTenTour());
			st.setString(4, obj.getDiemXuatPhat());
			st.setString(5, obj.getDiemKetThuc());
			st.setString(6, obj.getPhuongTienDiChuyen());
			st.setTimestamp(7, obj.getThoiGianXuatPhat());
			st.setTimestamp(8, obj.getThoiGianKetThuc());
			st.setInt(9, obj.getSoLuongVeToiDa());
			st.setInt(10, obj.getSoLuongVeHienCo());
			st.setLong(11, obj.getGiaVeHienTai());
			st.setLong(12, obj.getGiaVeLucTruoc());
			st.setString(13, obj.getMoTa());
			st.setString(14, obj.getFileName());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Tour> objs) {
		int result = 0;
		String sql = "INSERT INTO tour (maTour, maLoaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen, thoiGianXuatPhat, thoiGianKetThuc, soLuongVeToiDa, soLuongVeHienCo, giaVeHienTai, giaVeLucTruoc, moTa, fileName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);

			for (Tour obj : objs) {
				st.setString(1, obj.getMaTour());
				st.setString(2, obj.getLoaiTour().getMaLoaiTour());
				st.setString(3, obj.getTenTour());
				st.setString(4, obj.getDiemXuatPhat());
				st.setString(5, obj.getDiemKetThuc());
				st.setString(6, obj.getPhuongTienDiChuyen());
				st.setTimestamp(7, obj.getThoiGianXuatPhat());
				st.setTimestamp(8, obj.getThoiGianKetThuc());
				st.setInt(9, obj.getSoLuongVeToiDa());
				st.setInt(10, obj.getSoLuongVeHienCo());
				st.setLong(11, obj.getGiaVeHienTai());
				st.setLong(12, obj.getGiaVeLucTruoc());
				st.setString(13, obj.getMoTa());
				st.setString(14, obj.getFileName());

				st.addBatch();
			}

			int[] results = st.executeBatch();
			conn.commit();

			for (int count : results) {
				result += count;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Tour obj) {
		int result = 0;
		String sql = "DELETE FROM tour WHERE maTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaTour());
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAll(ArrayList<Tour> objs) {
		int result = 0;
		String sql = "DELETE FROM tour WHERE maTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (Tour obj : objs) {
				st.setString(1, obj.getMaTour());
				st.addBatch();
			}

			int[] results = st.executeBatch();
			conn.commit();

			for (int count : results) {
				result += count;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Tour obj) {
		int result = 0;
		String sql = "UPDATE tour SET maLoaiTour = ?, tenTour = ?, diemXuatPhat = ?, diemKetThuc = ?, "
				+ "phuongTienDiChuyen = ?, thoiGianXuatPhat = ?, thoiGianKetThuc = ?, "
				+ "soLuongVeToiDa = ?, soLuongVeHienCo = ?, giaVeHienTai = ?, giaVeLucTruoc = ?, "
				+ "moTa = ?, fileName = ? WHERE maTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getLoaiTour().getMaLoaiTour());
			st.setString(2, obj.getTenTour());
			st.setString(3, obj.getDiemXuatPhat());
			st.setString(4, obj.getDiemKetThuc());
			st.setString(5, obj.getPhuongTienDiChuyen());
			st.setTimestamp(6, obj.getThoiGianXuatPhat());
			st.setTimestamp(7, obj.getThoiGianKetThuc());
			st.setInt(8, obj.getSoLuongVeToiDa());
			st.setInt(9, obj.getSoLuongVeHienCo());
			st.setLong(10, obj.getGiaVeHienTai());
			st.setLong(11, obj.getGiaVeLucTruoc());
			st.setString(12, obj.getMoTa());
			st.setString(13, obj.getFileName());
			st.setString(14, obj.getMaTour());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getLastMaTour() {
		String lastMaTour = null;
		String sql = "SELECT maTour FROM tour ORDER BY maTour DESC LIMIT 1";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			if (rs.next()) {
				lastMaTour = rs.getString("maTour");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastMaTour;
	}

	public String generateNewMaTour() {
		String latestMaTour = getLastMaTour();

		if (latestMaTour == null) {
			return "T001";
		}
		int num = Integer.parseInt(latestMaTour.substring(1)) + 1;
		latestMaTour = String.format("T%03d", num);
		return latestMaTour;
	}

	public int countTotalTours() {
		int totalTours = 0;
		String sql = "SELECT COUNT(*) AS total FROM tour";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			if (rs.next()) {
				totalTours = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalTours;
	}

	public int countTotalTourTypes() {
		int totalTourTypes = 0;
		String sql = "SELECT COUNT(*) AS total FROM loaiTour";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			if (rs.next()) {
				totalTourTypes = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalTourTypes;
	}

	public ArrayList<Tour> selectPaginatedWithSortedData(int offset, int limit, String sortColumn, String sortOrder) {
		ArrayList<Tour> result = new ArrayList<>();
		String query = "SELECT t.*, lt.tenLoaiTour FROM tour t " + "JOIN loaiTour lt ON t.maLoaiTour = lt.maLoaiTour";

		if (sortColumn != null && !sortColumn.isEmpty()) {
			if (sortColumn.equals("tenLoaiTour")) {
				query += " ORDER BY lt." + sortColumn + " " + sortOrder;
			} else {
				query += " ORDER BY t." + sortColumn + " " + sortOrder;
			}
		}

		query += " LIMIT ?, ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
			st.setInt(1, offset);
			st.setInt(2, limit);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String maTour = rs.getString("maTour");
					String maLoaiTour = rs.getString("maLoaiTour");
					String tenTour = rs.getString("tenTour");
					String diemXuatPhat = rs.getString("diemXuatPhat");
					String diemKetThuc = rs.getString("diemKetThuc");
					String phuongTienDiChuyen = rs.getString("phuongTienDiChuyen");
					Timestamp thoiGianXuatPhat = rs.getTimestamp("thoiGianXuatPhat");
					Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
					int soLuongVeToiDa = rs.getInt("soLuongVeToiDa");
					int soLuongVeHienCo = rs.getInt("soLuongVeHienCo");
					long giaVeHienTai = rs.getLong("giaVeHienTai");
					long giaVeLucTruoc = rs.getLong("giaVeLucTruoc");
					String moTa = rs.getString("moTa");
					String fileName = rs.getString("fileName");
					String tenLoaiTour = rs.getString("tenLoaiTour");

					LoaiTour loaiTour = new LoaiTour();
					loaiTour.setMaLoaiTour(maLoaiTour);
					loaiTour.setTenLoaiTour(tenLoaiTour);

					Tour tour = new Tour(maTour, loaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen,
							thoiGianXuatPhat, thoiGianKetThuc, giaVeHienTai, giaVeLucTruoc, soLuongVeToiDa,
							soLuongVeHienCo, moTa, fileName);
					result.add(tour);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Tour> searchByIdOrName(String searchQuery, int offset, int limit, String sortColumn,
			String sortOrder) {
		ArrayList<Tour> result = new ArrayList<>();
		String query = "SELECT t.*, lt.tenLoaiTour FROM tour t " + "JOIN loaiTour lt ON t.maLoaiTour = lt.maLoaiTour "
				+ "WHERE t.maTour LIKE ? OR t.tenTour LIKE ? OR lt.tenLoaiTour LIKE ?";

		if (sortColumn != null && !sortColumn.isEmpty()) {
			if (sortColumn.equals("tenLoaiTour")) {
				query += " ORDER BY lt." + sortColumn + " " + sortOrder;
			} else {
				query += " ORDER BY t." + sortColumn + " " + sortOrder;
			}
		}

		query += " LIMIT ?, ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
			st.setString(1, "%" + searchQuery + "%");
			st.setString(2, "%" + searchQuery + "%");
			st.setString(3, "%" + searchQuery + "%");
			st.setInt(4, offset);
			st.setInt(5, limit);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String maTour = rs.getString("maTour");
					String maLoaiTour = rs.getString("maLoaiTour");
					String tenTour = rs.getString("tenTour");
					String diemXuatPhat = rs.getString("diemXuatPhat");
					String diemKetThuc = rs.getString("diemKetThuc");
					String phuongTienDiChuyen = rs.getString("phuongTienDiChuyen");
					Timestamp thoiGianXuatPhat = rs.getTimestamp("thoiGianXuatPhat");
					Timestamp thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc");
					int soLuongVeToiDa = rs.getInt("soLuongVeToiDa");
					int soLuongVeHienCo = rs.getInt("soLuongVeHienCo");
					long giaVeHienTai = rs.getLong("giaVeHienTai");
					long giaVeLucTruoc = rs.getLong("giaVeLucTruoc");
					String moTa = rs.getString("moTa");
					String fileName = rs.getString("fileName");
					String tenLoaiTour = rs.getString("tenLoaiTour");

					LoaiTour loaiTour = new LoaiTour();
					loaiTour.setMaLoaiTour(maLoaiTour);
					loaiTour.setTenLoaiTour(tenLoaiTour);

					Tour tour = new Tour(maTour, loaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen,
							thoiGianXuatPhat, thoiGianKetThuc, giaVeHienTai, giaVeLucTruoc, soLuongVeToiDa,
							soLuongVeHienCo, moTa, fileName);
					result.add(tour);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countSearchResults(String query) {
		String sql = "SELECT COUNT(*) FROM tour WHERE maTour LIKE ? OR tenTour LIKE ?";
		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, "%" + query + "%");
			st.setString(2, "%" + query + "%");

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updateFileName(Tour obj) {
		int result = 0;
		String sql = "UPDATE tour SET fileName = ? WHERE maTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getFileName());
			st.setString(2, obj.getMaTour());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean tourExists(String tenTour, String fileName) {
		String query = "SELECT COUNT(*) FROM tour WHERE tenTour = ? OR fileName = ?";
		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
			st.setString(1, tenTour);
			st.setString(2, fileName);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
