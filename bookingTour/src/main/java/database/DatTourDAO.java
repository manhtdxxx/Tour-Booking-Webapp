package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.DatTour;
import model.KhachHang;

public class DatTourDAO implements DAOInterface<DatTour> {

	@Override
	public ArrayList<DatTour> selectAll() {
		ArrayList<DatTour> result = new ArrayList<DatTour>();
		String sql = "SELECT maDatTour, maKH, thoiGianDatTour, hinhThucThanhToan, ghiChu, trangThaiDatTour FROM dattour";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maDatTour = rs.getString("maDatTour");
				String maKH = rs.getString("maKH");
				Timestamp thoiGianDatTour = rs.getTimestamp("thoiGianDatTour");
				String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
				String ghiChu = rs.getString("ghiChu");
				String trangThaiDatTour = rs.getString("trangThaiDatTour");

				KhachHang khachHang = new KhachHang();
				khachHang.setMaKH(maKH);
				khachHang = new KhachHangDAO().selectById(khachHang);

				DatTour datTour = new DatTour(maDatTour, khachHang, thoiGianDatTour, hinhThucThanhToan, ghiChu,
						trangThaiDatTour);
				result.add(datTour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DatTour selectById(DatTour obj) {
		DatTour result = null;
		String sql = "SELECT maDatTour, maKH, thoiGianDatTour, hinhThucThanhToan, ghiChu, trangThaiDatTour FROM dattour WHERE maDatTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaDatTour());

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maDatTour = rs.getString("maDatTour");
					String maKH = rs.getString("maKH");
					Timestamp thoiGianDatTour = rs.getTimestamp("thoiGianDatTour");
					String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
					String ghiChu = rs.getString("ghiChu");
					String trangThaiDatTour = rs.getString("trangThaiDatTour");

					KhachHang khachHang = new KhachHang();
					khachHang.setMaKH(maKH);
					khachHang = new KhachHangDAO().selectById(khachHang);

					result = new DatTour(maDatTour, khachHang, thoiGianDatTour, hinhThucThanhToan, ghiChu,
							trangThaiDatTour);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(DatTour obj) {
		String sql = "INSERT INTO dattour (maDatTour, maKH, thoiGianDatTour, hinhThucThanhToan, ghiChu, trangThaiDatTour) VALUES (?, ?, ?, ?, ?, ?)";
		int rowsInserted = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaDatTour());
			st.setString(2, obj.getKhachHang().getMaKH());
			st.setTimestamp(3, obj.getThoiGianDatTour());
			st.setString(4, obj.getHinhThucThanhToan());
			st.setString(5, obj.getGhiChu());
			st.setString(6, obj.getTrangThaiDatTour()); // Insert the status

			rowsInserted = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsInserted;
	}

	@Override
	public int insertAll(ArrayList<DatTour> objs) {
		String sql = "INSERT INTO dattour (maDatTour, maKH, thoiGianDatTour, hinhThucThanhToan, ghiChu, trangThaiDatTour) VALUES (?, ?, ?, ?, ?, ?)";
		int rowsInserted = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (DatTour obj : objs) {
				st.setString(1, obj.getMaDatTour());
				st.setString(2, obj.getKhachHang().getMaKH());
				st.setTimestamp(3, obj.getThoiGianDatTour());
				st.setString(4, obj.getHinhThucThanhToan());
				st.setString(5, obj.getGhiChu());
				st.setString(6, obj.getTrangThaiDatTour()); // Insert the status

				st.addBatch();
			}

			int[] results = st.executeBatch();
			conn.commit();

			for (int result : results) {
				rowsInserted += result;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsInserted;
	}

	@Override
	public int delete(DatTour obj) {
		String sql = "DELETE FROM dattour WHERE maDatTour = ?";
		int rowsDeleted = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaDatTour());

			rowsDeleted = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public int deleteAll(ArrayList<DatTour> objs) {
		String sql = "DELETE FROM dattour WHERE maDatTour = ?";
		int totalDeleted = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (DatTour obj : objs) {
				st.setString(1, obj.getMaDatTour());

				st.addBatch();
			}

			int[] results = st.executeBatch();
			conn.commit();

			for (int result : results) {
				totalDeleted += result;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalDeleted;
	}

	@Override
	public int update(DatTour obj) {
		String sql = "UPDATE dattour SET maKH = ?, thoiGianDatTour = ?, hinhThucThanhToan = ?, ghiChu = ?, trangThaiDatTour = ? WHERE maDatTour = ?";
		int rowsUpdated = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getKhachHang().getMaKH());
			st.setTimestamp(2, obj.getThoiGianDatTour());
			st.setString(3, obj.getHinhThucThanhToan());
			st.setString(4, obj.getGhiChu());
			st.setString(5, obj.getTrangThaiDatTour());
			st.setString(6, obj.getMaDatTour());

			rowsUpdated = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}

	private String getLastMaDatTour() {
		String latestMaDatTour = null;
		String sql = "SELECT maDatTour FROM dattour ORDER BY maDatTour DESC LIMIT 1";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			if (rs.next()) {
				latestMaDatTour = rs.getString("maDatTour");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return latestMaDatTour;
	}

	public String generateNewMaDatTour() {
		String latestMaDatTour = getLastMaDatTour();

		if (latestMaDatTour == null) {
			return "DT001";
		}
		int num = Integer.parseInt(latestMaDatTour.substring(2)) + 1;
		latestMaDatTour = String.format("DT%03d", num);
		return latestMaDatTour;
	}

	public ArrayList<DatTour> selectDatToursByMaKH(String maKH) {
		ArrayList<DatTour> result = new ArrayList<DatTour>();
		String sql = "SELECT * FROM dattour WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, maKH);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String maDatTour = rs.getString("maDatTour");
					Timestamp thoiGianDatTour = rs.getTimestamp("thoiGianDatTour");
					String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
					String ghiChu = rs.getString("ghiChu");
					String trangThaiDatTour = rs.getString("trangThaiDatTour");

					KhachHang khachHang = new KhachHang();
					khachHang.setMaKH(maKH);
					khachHang = new KhachHangDAO().selectById(khachHang);

					DatTour datTour = new DatTour(maDatTour, khachHang, thoiGianDatTour, hinhThucThanhToan, ghiChu,
							trangThaiDatTour);
					result.add(datTour);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
