package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.DoiTac;

public class DoiTacDAO implements DAO_Interface<DoiTac> {

	@Override
	public ArrayList<DoiTac> selectAll() {
		ArrayList<DoiTac> result = new ArrayList<DoiTac>();
		String sql = "SELECT * FROM doitac";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery(sql);) {

			while (rs.next()) {
				String maDoiTac = rs.getString("maDoiTac");
				String tenDoiTac = rs.getString("tenDoiTac");
				String diaChi = rs.getString("diaChi");
				String soDienThoai = rs.getString("soDienThoai");
				String email = rs.getString("email");
				String moTa = rs.getString("moTa");

				DoiTac doiTac = new DoiTac(maDoiTac, tenDoiTac, diaChi, soDienThoai, email, moTa);
				result.add(doiTac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DoiTac selectById(DoiTac obj) {
		DoiTac result = null;
		String sql = "SELECT * FROM doitac WHERE maDoiTac = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaDoiTac());
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maDoiTac = rs.getString("maDoiTac");
					String tenDoiTac = rs.getString("tenDoiTac");
					String diaChi = rs.getString("diaChi");
					String soDienThoai = rs.getString("soDienThoai");
					String email = rs.getString("email");
					String moTa = rs.getString("moTa");

					result = new DoiTac(maDoiTac, tenDoiTac, diaChi, soDienThoai, email, moTa);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(DoiTac obj) {
		int result = 0;
		String sql = "INSERT INTO doitac (maDoiTac, tenDoiTac, diaChi, soDienThoai, email, moTa) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaDoiTac());
			st.setString(2, obj.getTenDoiTac());
			st.setString(3, obj.getDiaChi());
			st.setString(4, obj.getSoDienThoai());
			st.setString(5, obj.getEmail());
			st.setString(6, obj.getMoTa());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<DoiTac> objs) {
		int result = 0;
		String sql = "INSERT INTO doitac (maDoiTac, tenDoiTac, diaChi, soDienThoai, email, moTa) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (DoiTac obj : objs) {
				st.setString(1, obj.getMaDoiTac());
				st.setString(2, obj.getTenDoiTac());
				st.setString(3, obj.getDiaChi());
				st.setString(4, obj.getSoDienThoai());
				st.setString(5, obj.getEmail());
				st.setString(6, obj.getMoTa());
				//
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
	public int delete(DoiTac obj) {
		int result = 0;
		String sql = "DELETE FROM doitac WHERE maDoiTac = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaDoiTac());
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAll(ArrayList<DoiTac> objs) {
		int result = 0;
		String sql = "DELETE FROM doitac WHERE maDoiTac = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (DoiTac obj : objs) {
				st.setString(1, obj.getMaDoiTac());
				//
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
	public int update(DoiTac obj) {
		int result = 0;
		String sql = "UPDATE doitac SET tenDoiTac = ?, diaChi = ?, soDienThoai = ?, email = ?, moTa = ? WHERE maDoiTac = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTenDoiTac());
			st.setString(2, obj.getDiaChi());
			st.setString(3, obj.getSoDienThoai());
			st.setString(4, obj.getEmail());
			st.setString(5, obj.getMoTa());
			st.setString(6, obj.getMaDoiTac());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
