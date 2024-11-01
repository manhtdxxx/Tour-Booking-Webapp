package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.DatTour;
import model.KhachHang;

public class DatTourDAO implements DAO_Interface<DatTour> {

	@Override
	public ArrayList<DatTour> selectAll() {
		ArrayList<DatTour> result = new ArrayList<DatTour>();
		String sql = "SELECT * FROM dattour";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maDatTour = rs.getString("maDatTour");
				String maKH = rs.getString("maKH");
				Timestamp thoiGianDatTour = rs.getTimestamp("thoiGianDatTour");
				String trangThaiThanhToan = rs.getString("trangThaiThanhToan");
				Timestamp thoiGianThanhToan = rs.getTimestamp("thoiGianThanhToan");
				String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
				String ghiChu = rs.getString("ghiChu");

				KhachHang khachHang = new KhachHang();
				khachHang.setMaKH(maKH);
				khachHang = new KhachHangDAO().selectById(khachHang);

				DatTour datTour = new DatTour(maDatTour, khachHang, thoiGianDatTour, trangThaiThanhToan,
						thoiGianThanhToan, hinhThucThanhToan, ghiChu);
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
		String sql = "SELECT * FROM dattour WHERE maDatTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaDatTour());

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maDatTour = rs.getString("maDatTour");
					String maKH = rs.getString("maKH");
					Timestamp thoiGianDatTour = rs.getTimestamp("thoiGianDatTour");
					String trangThaiThanhToan = rs.getString("trangThaiThanhToan");
					Timestamp thoiGianThanhToan = rs.getTimestamp("thoiGianThanhToan");
					String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
					String ghiChu = rs.getString("ghiChu");

					KhachHang khachHang = new KhachHang();
					khachHang.setMaKH(maKH);
					khachHang = new KhachHangDAO().selectById(khachHang);

					result = new DatTour(maDatTour, khachHang, thoiGianDatTour, trangThaiThanhToan, thoiGianThanhToan,
							hinhThucThanhToan, ghiChu);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(DatTour obj) {
		String sql = "INSERT INTO dattour (maDatTour, maKH, thoiGianDatTour, trangThaiThanhToan, thoiGianThanhToan, hinhThucThanhToan, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int rowsInserted = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaDatTour());
			st.setString(2, obj.getKhachHang().getMaKH());
			st.setTimestamp(3, obj.getThoiGianDatTour());
			st.setString(4, obj.getTrangThaiThanhToan());
			st.setTimestamp(5, obj.getThoiGianThanhToan());
			st.setString(6, obj.getHinhThucThanhToan());
			st.setString(7, obj.getGhiChu());

			rowsInserted = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsInserted;
	}

	@Override
	public int insertAll(ArrayList<DatTour> objs) {
		String sql = "INSERT INTO dattour (maDatTour, maKH, thoiGianDatTour, trangThaiThanhToan, thoiGianThanhToan, hinhThucThanhToan, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int rowsInserted = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (DatTour obj : objs) {
				st.setString(1, obj.getMaDatTour());
				st.setString(2, obj.getKhachHang().getMaKH());
				st.setTimestamp(3, obj.getThoiGianDatTour());
				st.setString(4, obj.getTrangThaiThanhToan());
				st.setTimestamp(5, obj.getThoiGianThanhToan());
				st.setString(6, obj.getHinhThucThanhToan());
				st.setString(7, obj.getGhiChu());

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
		String sql = "UPDATE dattour SET maKH = ?, thoiGianDatTour = ?, trangThaiThanhToan = ?, thoiGianThanhToan = ?, hinhThucThanhToan = ?, ghiChu = ? WHERE maDatTour = ?";
		int rowsUpdated = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getKhachHang().getMaKH());
			st.setTimestamp(2, obj.getThoiGianDatTour());
			st.setString(3, obj.getTrangThaiThanhToan());
			st.setTimestamp(4, obj.getThoiGianThanhToan());
			st.setString(5, obj.getHinhThucThanhToan());
			st.setString(6, obj.getGhiChu());
			st.setString(7, obj.getMaDatTour());

			rowsUpdated = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}

}