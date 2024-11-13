package database;

import java.sql.Connection;
import java.sql.Date;
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
				String hoTen = rs.getString("hoTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String noiSinh = rs.getString("noiSinh");
				String dienThoai = rs.getString("dienThoai");

				NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, noiSinh, dienThoai);
				result.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public NhanVien selectById(NhanVien obj) {
		NhanVien result = null;
		String sql = "SELECT * FROM nhanvien WHERE maNV = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaNV());
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String maNV = rs.getString("maNV");
					String hoTen = rs.getString("hoTen");
					Date ngaySinh = rs.getDate("ngaySinh");
					String noiSinh = rs.getString("noiSinh");
					String dienThoai = rs.getString("dienThoai");

					result = new NhanVien(maNV, hoTen, ngaySinh, noiSinh, dienThoai);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(NhanVien obj) {
		String sql = "INSERT INTO nhanvien (maNV, hoTen, ngaySinh, noiSinh, dienThoai) VALUES (?, ?, ?, ?, ?)";
		int rowsAffected = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaNV());
			st.setString(2, obj.getHoTen());
			st.setDate(3, obj.getNgaySinh());
			st.setString(4, obj.getNoiSinh());
			st.setString(5, obj.getDienThoai());

			rowsAffected = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int insertAll(ArrayList<NhanVien> objs) {
		int totalRowsAffected = 0;
		for (NhanVien nv : objs) {
			totalRowsAffected += insert(nv);
		}
		return totalRowsAffected;
	}

	@Override
	public int delete(NhanVien obj) {
		String sql = "DELETE FROM nhanvien WHERE maNV = ?";
		int rowsAffected = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getMaNV());
			rowsAffected = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int deleteAll(ArrayList<NhanVien> objs) {
		int totalRowsAffected = 0;
		for (NhanVien nv : objs) {
			totalRowsAffected += delete(nv);
		}
		return totalRowsAffected;
	}

	@Override
	public int update(NhanVien obj) {
		String sql = "UPDATE nhanvien SET hoTen = ?, ngaySinh = ?, noiSinh = ?, dienThoai = ? WHERE maNV = ?";
		int rowsAffected = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getHoTen());
			st.setDate(2, obj.getNgaySinh());
			st.setString(3, obj.getNoiSinh());
			st.setString(4, obj.getDienThoai());
			st.setString(5, obj.getMaNV());

			rowsAffected = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

}
