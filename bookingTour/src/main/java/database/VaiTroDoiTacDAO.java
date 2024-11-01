package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DoiTac;
import model.Tour;
import model.VaiTroDoiTac;

public class VaiTroDoiTacDAO implements DAO_Interface<VaiTroDoiTac> {

	@Override
	public ArrayList<VaiTroDoiTac> selectAll() {
		ArrayList<VaiTroDoiTac> result = new ArrayList<VaiTroDoiTac>();
		String sql = "SELECT * FROM vaitrodoitac";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String maTour = rs.getString("maTour");
				String maDoiTac = rs.getString("maDoiTac");
				String vaiTro = rs.getString("vaiTro");

				Tour tour = new Tour();
				tour.setMaTour(maTour);
				tour = new TourDAO().selectById(tour);

				DoiTac doiTac = new DoiTac();
				doiTac.setMaDoiTac(maDoiTac);
				doiTac = new DoiTacDAO().selectById(doiTac);

				VaiTroDoiTac vtdt = new VaiTroDoiTac(tour, doiTac, vaiTro);
				result.add(vtdt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public VaiTroDoiTac selectById(VaiTroDoiTac obj) {
		VaiTroDoiTac result = null;
		String sql = "SELECT * FROM vaitrodoitac WHERE maTour = ? AND maDoiTac = ?";
		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTour().getMaTour());
			st.setString(2, obj.getDoiTac().getMaDoiTac());

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String vaiTro = rs.getString("vaiTro");
					result = new VaiTroDoiTac(obj.getTour(), obj.getDoiTac(), vaiTro);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(VaiTroDoiTac obj) {
		int result = 0;
		String sql = "INSERT INTO vaitrodoitac (maTour, maDoiTac, vaiTro) VALUES (?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTour().getMaTour());
			st.setString(2, obj.getDoiTac().getMaDoiTac());
			st.setString(3, obj.getVaiTro());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<VaiTroDoiTac> objs) {
		int result = 0;
		String sql = "INSERT INTO vaitrodoitac (maTour, maDoiTac, vaiTro) VALUES (?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (VaiTroDoiTac obj : objs) {
				st.setString(1, obj.getTour().getMaTour());
				st.setString(2, obj.getDoiTac().getMaDoiTac());
				st.setString(3, obj.getVaiTro());
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
	public int delete(VaiTroDoiTac obj) {
		int result = 0;
		String sql = "DELETE FROM vaitrodoitac WHERE maTour = ? AND maDoiTac = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTour().getMaTour());
			st.setString(2, obj.getDoiTac().getMaDoiTac());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAll(ArrayList<VaiTroDoiTac> objs) {
		int result = 0;
		String sql = "DELETE FROM vaitrodoitac WHERE maTour = ? AND maDoiTac = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (VaiTroDoiTac obj : objs) {
				st.setString(1, obj.getTour().getMaTour());
				st.setString(2, obj.getDoiTac().getMaDoiTac());
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
	public int update(VaiTroDoiTac obj) {
		int result = 0;
		String sql = "UPDATE vaitrodoitac SET vaiTro = ? WHERE maTour = ? AND maDoiTac = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getVaiTro());
			st.setString(2, obj.getTour().getMaTour());
			st.setString(3, obj.getDoiTac().getMaDoiTac());

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
