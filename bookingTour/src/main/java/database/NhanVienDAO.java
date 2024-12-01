package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.NhanVien;

public class NhanVienDAO implements DAOInterface<NhanVien> {

	@Override
	public ArrayList<NhanVien> selectAll() {
		ArrayList<NhanVien> result = new ArrayList<>();
		String sql = "SELECT * FROM nhanvien";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String username = rs.getString("username");
				String password = rs.getString("password");

				NhanVien nv = new NhanVien(maNV, username, password);
				result.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public NhanVien selectById(NhanVien obj) {
		NhanVien nv = null;
		String sql = "SELECT * FROM nhanvien WHERE maNV = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaNV());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String maNV = rs.getString("maNV");
				String username = rs.getString("username");
				String password = rs.getString("password");

				nv = new NhanVien(maNV, username, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nv;
	}

	@Override
	public int insert(NhanVien obj) {
		int result = 0;
		String sql = "INSERT INTO nhanvien (maNV, username, password) VALUES (?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaNV());
			st.setString(2, obj.getUsername());
			st.setString(3, obj.getPassword());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<NhanVien> objs) {
		int result = 0;
		for (NhanVien nv : objs) {
			result += insert(nv);
		}
		return result;
	}

	@Override
	public int delete(NhanVien obj) {
		int result = 0;
		String sql = "DELETE FROM nhanvien WHERE maNV = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaNV());
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAll(ArrayList<NhanVien> objs) {
		int result = 0;
		for (NhanVien nv : objs) {
			result += delete(nv);
		}
		return result;
	}

	@Override
	public int update(NhanVien obj) {
		int result = 0;
		String sql = "UPDATE nhanvien SET username = ?, password = ? WHERE maNV = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getUsername());
			st.setString(2, obj.getPassword());
			st.setString(3, obj.getMaNV());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
