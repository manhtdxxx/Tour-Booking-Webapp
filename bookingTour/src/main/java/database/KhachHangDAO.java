package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				String tenKH = rs.getString("tenKH");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String gioiTinh = rs.getString("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String soDienThoai = rs.getString("soDienThoai");
				String email = rs.getString("email");

				KhachHang kh = new KhachHang(maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai, email);
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
		//
		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaKH());
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maKH = rs.getString("maKH");
					String tenKH = rs.getString("tenKH");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String gioiTinh = rs.getString("gioiTinh");
					Date ngaySinh = rs.getDate("ngaySinh");
					String soDienThoai = rs.getString("soDienThoai");
					String email = rs.getString("email");
					result = new KhachHang(maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai, email);
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
		String sql = "INSERT INTO khachhang (maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaKH());
			st.setString(2, obj.getTenKH());
			st.setString(3, obj.getUsername());
			st.setString(4, obj.getPassword());
			st.setString(5, obj.getGioiTinh());
			st.setDate(6, obj.getNgaySinh());
			st.setString(7, obj.getSoDienThoai());
			st.setString(8, obj.getEmail());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> objs) {
		int result = 0;
		String sql = "INSERT INTO khachhang (maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (KhachHang obj : objs) {
				st.setString(1, obj.getMaKH());
				st.setString(2, obj.getTenKH());
				st.setString(3, obj.getUsername());
				st.setString(4, obj.getPassword());
				st.setString(5, obj.getGioiTinh());
				st.setDate(6, obj.getNgaySinh());
				st.setString(7, obj.getSoDienThoai());
				st.setString(8, obj.getEmail());
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
	public int update(KhachHang obj) {
		int result = 0;
		String sql = "UPDATE khachhang SET tenKH = ?, username = ?, password = ?, gioiTinh = ?, ngaySinh = ?, soDienThoai = ?, email = ? WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getTenKH());
			st.setString(2, obj.getUsername());
			st.setString(3, obj.getPassword());
			st.setString(4, obj.getGioiTinh());
			st.setDate(5, obj.getNgaySinh());
			st.setString(6, obj.getSoDienThoai());
			st.setString(7, obj.getEmail());
			st.setString(8, obj.getMaKH());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkExistence(String username) {
		boolean result = false;
		String sql = "SELECT * FROM khachhang WHERE username=?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, username);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getLastMaKH() {
		String latestMaKH = null;
		String sql = "SELECT maKH FROM khachhang ORDER BY maKH DESC LIMIT 1";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			if (rs.next()) {
				latestMaKH = rs.getString("maKH");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return latestMaKH;
	}

	public String generateNewMaKH() {
		String latestMaKH = getLastMaKH();

		if (latestMaKH == null) {
			return "KH001";
		}
		int num = Integer.parseInt(latestMaKH.substring(2)) + 1;
		latestMaKH = String.format("KH%03d", num);
		return latestMaKH;
	}

	public KhachHang selectByUsernameAndPassword(KhachHang obj) {
		KhachHang result = null;
		String sql = "SELECT * FROM khachhang WHERE username=? and password=?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getUsername());
			st.setString(2, obj.getPassword());

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maKH = rs.getString("maKH");
					String tenKH = rs.getString("tenKH");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String gioiTinh = rs.getString("gioiTinh");
					Date ngaySinh = rs.getDate("ngaySinh");
					String soDienThoai = rs.getString("soDienThoai");
					String email = rs.getString("email");
					String status = rs.getString("status");

					result = new KhachHang(maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai, email,
							status);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int changePassword(KhachHang obj) {
		int result = 0;
		String sql = "UPDATE khachhang SET password = ? WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getPassword());
			st.setString(2, obj.getMaKH());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateInfo(KhachHang obj) {
		String sql = "UPDATE khachhang SET tenKH = ?, gioiTinh = ?, ngaySinh = ?, soDienThoai = ?, email = ? WHERE maKH = ?";
		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, obj.getTenKH());
			stmt.setString(2, obj.getGioiTinh());
			stmt.setDate(3, obj.getNgaySinh());
			stmt.setString(4, obj.getSoDienThoai());
			stmt.setString(5, obj.getEmail());
			stmt.setString(6, obj.getMaKH());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<KhachHang> selectPaginatedData(int offset, int limit) {
		ArrayList<KhachHang> result = new ArrayList<>();
		String sql = "SELECT * FROM khachhang LIMIT ? OFFSET ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, limit);
			st.setInt(2, offset);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String maKH = rs.getString("maKH");
					String tenKH = rs.getString("tenKH");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String gioiTinh = rs.getString("gioiTinh");
					Date ngaySinh = rs.getDate("ngaySinh");
					String soDienThoai = rs.getString("soDienThoai");
					String email = rs.getString("email");
					String status = rs.getString("status");

					KhachHang kh = new KhachHang(maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai,
							email, status);
					result.add(kh);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<KhachHang> selectPaginatedWithSortedData(int offset, int limit, String sortColumn,
			String sortOrder) {
		ArrayList<KhachHang> result = new ArrayList<>();
		String query = "SELECT * FROM KhachHang";

		// Add sorting logic
		if (sortColumn != null && !sortColumn.isEmpty()) {
			query += " ORDER BY " + sortColumn + " " + sortOrder;
		}

		query += " LIMIT ?, ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
			st.setInt(1, offset);
			st.setInt(2, limit);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String maKH = rs.getString("maKH");
					String tenKH = rs.getString("tenKH");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String gioiTinh = rs.getString("gioiTinh");
					Date ngaySinh = rs.getDate("ngaySinh");
					String soDienThoai = rs.getString("soDienThoai");
					String email = rs.getString("email");
					String status = rs.getString("status");

					KhachHang kh = new KhachHang(maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai,
							email, status);
					result.add(kh);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countTotalRecords() {
		int totalRecords = 0;
		String sql = "SELECT COUNT(*) AS total FROM khachhang";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			if (rs.next()) {
				totalRecords = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRecords;
	}

	public int countActiveRecords() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM khachhang WHERE status = 'active'";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int insertIncludingStatus(KhachHang obj) {
		int result = 0;
		String sql = "INSERT INTO khachhang (maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai, email, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaKH());
			st.setString(2, obj.getTenKH());
			st.setString(3, obj.getUsername());
			st.setString(4, obj.getPassword());
			st.setString(5, obj.getGioiTinh());
			st.setDate(6, obj.getNgaySinh());
			st.setString(7, obj.getSoDienThoai());
			st.setString(8, obj.getEmail());
			st.setString(9, obj.getStatus());

			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean toggleCustomerStatus(KhachHang obj) {
		String getStatusQuery = "SELECT status FROM khachhang WHERE maKH = ?";
		String updateStatusQuery = "UPDATE khachhang SET status = ? WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement getStatusStmt = conn.prepareStatement(getStatusQuery);
				PreparedStatement updateStatusStmt = conn.prepareStatement(updateStatusQuery)) {

			// Get the current status
			getStatusStmt.setString(1, obj.getMaKH());
			ResultSet rs = getStatusStmt.executeQuery();

			if (rs.next()) {
				String currentStatus = rs.getString("status");
				String newStatus = "active".equalsIgnoreCase(currentStatus) ? "inactive" : "active";

				// Update the status in the database
				updateStatusStmt.setString(1, newStatus);
				updateStatusStmt.setString(2, obj.getMaKH());
				if (updateStatusStmt.executeUpdate() > 0) {
					// Update the status in the object
					obj.setStatus(newStatus);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<KhachHang> searchByIdOrName(String searchQuery, int offset, int limit, String sortColumn,
			String sortOrder) {
		ArrayList<KhachHang> result = new ArrayList<>();
		String query = "SELECT * FROM KhachHang WHERE maKH LIKE ? OR tenKH LIKE ?";

		// Add sorting logic
		if (sortColumn != null && !sortColumn.isEmpty()) {
			query += " ORDER BY " + sortColumn + " " + sortOrder;
		}

		query += " LIMIT ?, ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
			st.setString(1, "%" + searchQuery + "%");
			st.setString(2, "%" + searchQuery + "%");
			st.setInt(3, offset);
			st.setInt(4, limit);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String maKH = rs.getString("maKH");
					String tenKH = rs.getString("tenKH");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String gioiTinh = rs.getString("gioiTinh");
					Date ngaySinh = rs.getDate("ngaySinh");
					String soDienThoai = rs.getString("soDienThoai");
					String email = rs.getString("email");
					String status = rs.getString("status");

					KhachHang kh = new KhachHang(maKH, tenKH, username, password, gioiTinh, ngaySinh, soDienThoai,
							email, status);
					result.add(kh);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countSearchResults(String query) {
		String sql = "SELECT COUNT(*) FROM KhachHang WHERE maKH LIKE ? OR tenKH LIKE ?";
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

}
