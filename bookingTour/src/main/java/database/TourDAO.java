package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				int giaVe = rs.getInt("giaVe");
				int soLuongVeToiDa = rs.getInt("soLuongVeToiDa");
				String moTa = rs.getString("moTa");

				LoaiTour loaiTour = new LoaiTour();
				loaiTour.setMaLoaiTour(maLoaiTour);
				loaiTour = new LoaiTourDAO().selectById(loaiTour);

				Tour tour = new Tour(maTour, loaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen,
						thoiGianXuatPhat, thoiGianKetThuc, giaVe, soLuongVeToiDa, moTa);
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
					int giaVe = rs.getInt("giaVe");
					int soLuongVeToiDa = rs.getInt("soLuongVeToiDa");
					String moTa = rs.getString("moTa");

					LoaiTour loaiTour = new LoaiTour();
					loaiTour.setMaLoaiTour(maLoaiTour);
					loaiTour = new LoaiTourDAO().selectById(loaiTour);

					result = new Tour(maTour, loaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen,
							thoiGianXuatPhat, thoiGianKetThuc, giaVe, soLuongVeToiDa, moTa);
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
		String sql = "INSERT INTO tour (maTour, maLoaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen, thoiGianXuatPhat, thoiGianKetThuc, giaVe, soLuongVeToiDa, moTa) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getMaTour());
			st.setString(2, obj.getLoaiTour().getMaLoaiTour());
			st.setString(3, obj.getTenTour());
			st.setString(4, obj.getDiemXuatPhat());
			st.setString(5, obj.getDiemKetThuc());
			st.setString(6, obj.getPhuongTienDiChuyen());
			st.setTimestamp(7, obj.getThoiGianXuatPhat());
			st.setTimestamp(8, obj.getThoiGianKetThuc());
			st.setInt(9, obj.getGiaVe());
			st.setInt(10, obj.getSoLuongVeToiDa());
			st.setString(11, obj.getMoTa());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Tour> objs) {
		int result = 0;
		String sql = "INSERT INTO tour (maTour, maLoaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen, thoiGianXuatPhat, thoiGianKetThuc, giaVe, soLuongVeToiDa, moTa) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
				st.setInt(9, obj.getGiaVe());
				st.setInt(10, obj.getSoLuongVeToiDa());
				st.setString(11, obj.getMoTa());

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
		String sql = "UPDATE tour SET maLoaiTour = ?, tenTour = ?, diemXuatPhat = ?, diemKetThuc = ?, phuongTienDiChuyen = ?, thoiGianXuatPhat = ?, thoiGianKetThuc = ?, giaVe = ?, soLuongVeToiDa = ?, moTa = ? "
				+ "WHERE maTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getLoaiTour().getMaLoaiTour());
			st.setString(2, obj.getTenTour());
			st.setString(3, obj.getDiemXuatPhat());
			st.setString(4, obj.getDiemKetThuc());
			st.setString(5, obj.getPhuongTienDiChuyen());
			st.setTimestamp(6, obj.getThoiGianXuatPhat());
			st.setTimestamp(7, obj.getThoiGianKetThuc());
			st.setInt(8, obj.getGiaVe());
			st.setInt(9, obj.getSoLuongVeToiDa());
			st.setString(10, obj.getMoTa());
			st.setString(11, obj.getMaTour());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
