package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhachHang;

public class KhachHangDAO implements DAOInterface<KhachHang> {

	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> result = new ArrayList<>();
		String sql = "SELECT * FROM khachhang";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maKH = rs.getString("maKH");
				String hoTen = rs.getString("hoTen");
				String diaChi = rs.getString("diaChi");
				String email = rs.getString("email");
				String dienThoai = rs.getString("dienThoai");

				KhachHang kh = new KhachHang(maKH, hoTen, diaChi, email, dienThoai);
				result.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public KhachHang selectById(KhachHang obj) {
		KhachHang result = null;
		String sql = "SELECT * FROM khachhang WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaKH());
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maKH = rs.getString("maKH");
					String hoTen = rs.getString("hoTen");
					String diaChi = rs.getString("diaChi");
					String email = rs.getString("email");
					String dienThoai = rs.getString("dienThoai");

					result = new KhachHang(maKH, hoTen, diaChi, email, dienThoai);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(KhachHang obj) {
		int result = 0;
		String sql = "INSERT INTO khachhang (maKH, hoTen, diaChi, email, dienThoai) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaKH());
			st.setString(2, obj.getHoTen());
			st.setString(3, obj.getDiaChi());
			st.setString(4, obj.getEmail());
			st.setString(5, obj.getDienThoai());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> objs) {
		int result = 0;
		String sql = "INSERT INTO khachhang (maKH, hoTen, diaChi, email, dienThoai) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);
			for (KhachHang obj : objs) {
				st.setString(1, obj.getMaKH());
				st.setString(2, obj.getHoTen());
				st.setString(3, obj.getDiaChi());
				st.setString(4, obj.getEmail());
				st.setString(5, obj.getDienThoai());
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
	public int delete(KhachHang obj) {
		int result = 0;
		String sql = "DELETE FROM khachhang WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaKH());
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAll(ArrayList<KhachHang> objs) {
		int result = 0;
		String sql = "DELETE FROM khachhang WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (KhachHang obj : objs) {
				st.setString(1, obj.getMaKH());
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
	public int update(KhachHang obj) {
		int result = 0;
		String sql = "UPDATE khachhang SET hoTen = ?, diaChi = ?, email = ?, dienThoai = ? WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getHoTen());
			st.setString(2, obj.getDiaChi());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getDienThoai());
			st.setString(5, obj.getMaKH());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
