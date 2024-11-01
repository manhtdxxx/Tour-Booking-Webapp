package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoaiTour;

public class LoaiTourDAO implements DAO_Interface<LoaiTour> {

	@Override
	public ArrayList<LoaiTour> selectAll() {
		ArrayList<LoaiTour> result = new ArrayList<LoaiTour>();
		String sql = "SELECT * FROM loaitour";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maLoaiTour = rs.getString("maLoaiTour");
				String tenLoaiTour = rs.getString("tenLoaiTour");

				LoaiTour loaiTour = new LoaiTour(maLoaiTour, tenLoaiTour);
				result.add(loaiTour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public LoaiTour selectById(LoaiTour obj) {
		LoaiTour result = null;
		String sql = "SELECT * FROM loaitour WHERE maLoaiTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaLoaiTour());
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maLoaiTour = rs.getString("maLoaiTour");
					String tenLoaiTour = rs.getString("tenLoaiTour");

					result = new LoaiTour(maLoaiTour, tenLoaiTour);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(LoaiTour obj) {
		int result = 0;
		String sql = "INSERT INTO loaitour (maLoaiTour, tenLoaiTour) VALUES (?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaLoaiTour());
			st.setString(2, obj.getTenLoaiTour());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<LoaiTour> objs) {
		int result = 0;
		String sql = "INSERT INTO loaitour (maLoaiTour, tenLoaiTour) VALUES (?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (LoaiTour obj : objs) {
				st.setString(1, obj.getMaLoaiTour());
				st.setString(2, obj.getTenLoaiTour());
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
	public int delete(LoaiTour obj) {
		int result = 0;
		String sql = "DELETE FROM loaitour WHERE maLoaiTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaLoaiTour());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAll(ArrayList<LoaiTour> objs) {
		int result = 0;
		String sql = "DELETE FROM loaitour WHERE maLoaiTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false); // Use a transaction to batch delete

			for (LoaiTour obj : objs) {
				st.setString(1, obj.getMaLoaiTour());
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
	public int update(LoaiTour obj) {
		int result = 0;
		String sql = "UPDATE loaitour SET tenLoaiTour = ? WHERE maLoaiTour = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTenLoaiTour());
			st.setString(2, obj.getMaLoaiTour());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
