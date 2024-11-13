package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.DonDatHang;
import model.KhachHang;
import model.NhanVien;

public class DonDatHangDAO implements DAOInterface<DonDatHang> {

	@Override
	public ArrayList<DonDatHang> selectAll() {
		ArrayList<DonDatHang> result = new ArrayList<>();
		String sql = "SELECT * FROM dondathang";

		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String soHoaDon = rs.getString("soHoaDon");
				String maKH = rs.getString("maKH");
				String maNV = rs.getString("maNV");
				Date ngayDatHang = rs.getDate("ngayDatHang");
				String tenHang = rs.getString("tenHang");
				int soLuong = rs.getInt("soLuong");
				int gia = rs.getInt("gia");

				KhachHang kh = new KhachHang();
				kh.setMaKH(maKH);
				kh = new KhachHangDAO().selectById(kh);

				NhanVien nv = new NhanVien();
				nv.setMaNV(maNV);
				nv = new NhanVienDAO().selectById(nv);

				DonDatHang dh = new DonDatHang(soHoaDon, kh, nv, ngayDatHang, tenHang, soLuong, gia);
				result.add(dh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DonDatHang selectById(DonDatHang obj) {
		DonDatHang result = null;
		String sql = "SELECT * FROM dondathang WHERE soHoaDon = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getSoHoaDon());
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String soHoaDon = rs.getString("soHoaDon");
					String maKH = rs.getString("maKH");
					String maNV = rs.getString("maNV");
					Date ngayDatHang = rs.getDate("ngayDatHang");
					String tenHang = rs.getString("tenHang");
					int soLuong = rs.getInt("soLuong");
					int gia = rs.getInt("gia");

					KhachHang kh = new KhachHang();
					kh.setMaKH(maKH);
					kh = new KhachHangDAO().selectById(kh);

					NhanVien nv = new NhanVien();
					nv.setMaNV(maNV);
					nv = new NhanVienDAO().selectById(nv);

					result = new DonDatHang(soHoaDon, kh, nv, ngayDatHang, tenHang, soLuong, gia);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(DonDatHang obj) {
		String sql = "INSERT INTO dondathang (soHoaDon, maKH, maNV, ngayDatHang, tenHang, soLuong, gia) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int rowsAffected = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getSoHoaDon());
			st.setString(2, obj.getKhachHang().getMaKH());
			st.setString(3, obj.getNhanVien().getMaNV());
			st.setDate(4, obj.getNgayDatHang());
			st.setString(5, obj.getTenHang());
			st.setInt(6, obj.getSoLuong());
			st.setInt(7, obj.getGia());

			rowsAffected = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int insertAll(ArrayList<DonDatHang> objs) {
		int totalRowsAffected = 0;
		for (DonDatHang dh : objs) {
			totalRowsAffected += insert(dh);
		}
		return totalRowsAffected;
	}

	@Override
	public int delete(DonDatHang obj) {
		String sql = "DELETE FROM dondathang WHERE soHoaDon = ?";
		int rowsAffected = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getSoHoaDon());
			rowsAffected = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int deleteAll(ArrayList<DonDatHang> objs) {
		int totalRowsAffected = 0;
		for (DonDatHang dh : objs) {
			totalRowsAffected += delete(dh);
		}
		return totalRowsAffected;
	}

	@Override
	public int update(DonDatHang obj) {
		String sql = "UPDATE dondathang SET maKH = ?, maNV = ?, ngayDatHang = ?, tenHang = ?, soLuong = ?, gia = ? WHERE soHoaDon = ?";
		int rowsAffected = 0;

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getKhachHang().getMaKH());
			st.setString(2, obj.getNhanVien().getMaNV());
			st.setDate(3, obj.getNgayDatHang());
			st.setString(4, obj.getTenHang());
			st.setInt(5, obj.getSoLuong());
			st.setInt(6, obj.getGia());
			st.setString(7, obj.getSoHoaDon());

			rowsAffected = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public ArrayList<DonDatHang> selectOrdersByCustomerId(String maKH) {
		ArrayList<DonDatHang> result = new ArrayList<>();
		String sql = "SELECT * FROM dondathang WHERE maKH = ?";

		try (Connection conn = JDBCUtil.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, maKH);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String soHoaDon = rs.getString("soHoaDon");
					String maNV = rs.getString("maNV");
					Date ngayDatHang = rs.getDate("ngayDatHang");
					String tenHang = rs.getString("tenHang");
					int soLuong = rs.getInt("soLuong");
					int gia = rs.getInt("gia");

					KhachHang kh = new KhachHang();
					kh.setMaKH(maKH);
					kh = new KhachHangDAO().selectById(kh);

					NhanVien nv = new NhanVien();
					nv.setMaNV(maNV);
					nv = new NhanVienDAO().selectById(nv);

					DonDatHang dh = new DonDatHang(soHoaDon, kh, nv, ngayDatHang, tenHang, soLuong, gia);
					result.add(dh);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
