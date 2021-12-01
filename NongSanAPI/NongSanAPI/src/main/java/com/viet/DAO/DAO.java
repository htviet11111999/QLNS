package com.viet.DAO;

import java.sql.*;
import java.util.ArrayList;

import com.viet.MODEL.CTNHAP;
import com.viet.MODEL.CTXUAT;
import com.viet.MODEL.DANGNHAP;
import com.viet.MODEL.NHANVIEN;
import com.viet.MODEL.NHAPNS;
import com.viet.MODEL.NONGSAN;
import com.viet.MODEL.XUATNS;



public class DAO {
	Connection con = null;
	public DAO() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QLNS;username=sa;password=12");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//=========================CÁC XỬ LÝ CỦA NÔNG SẢN====================================
	
	
	//LẤY THÔNG TIN NÔNG SẢN BẰNG MÃ
	
	public NONGSAN getNongSanBangID(String maNS) {
		NONGSAN ns = new NONGSAN();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM NONGSAN WHERE MANS = ?");
			ps.setString(1, maNS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ns.setMaNS(rs.getString(1));
				ns.setTenNS(rs.getString(2));
				ns.setSoLuong(rs.getInt(3));
				ns.setDonGia(rs.getInt(4));
				ns.setMoTa(rs.getString(5));
				ns.setImage(rs.getString(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ns;
	}
	
	//LẤY DANH SÁCH CÁC NÔNG SẢN
	
	public ArrayList<NONGSAN> getNongSan(){
		ArrayList<NONGSAN> listNS = new ArrayList<NONGSAN>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM NONGSAN");
			while(rs.next()) {
				NONGSAN n = new NONGSAN();
				n.setMaNS(rs.getString(1));
				n.setTenNS(rs.getString(2));
				n.setSoLuong(rs.getInt(3));
				n.setDonGia(rs.getInt(4));
				n.setMoTa(rs.getString(5));
				n.setImage(rs.getString(6));
				listNS.add(n);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listNS;
	}
	
	
	//XÓA NÔNG SẢN THEO MÃ
	
	public boolean deleteNongSan(String maNS) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM NONGSAN WHERE MANS = ?");
			ps.setString(1, maNS);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	//THÊM NÔNG SẢN
	
	public boolean insertNongSan(NONGSAN ns) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO NONGSAN VALUES (?,?,?,?,?,?)");
			ps.setString(1, ns.getMaNS());
			ps.setString(2, ns.getTenNS());
			ps.setInt(3, ns.getSoLuong());
			ps.setInt(4, ns.getDonGia());
			ps.setString(5, ns.getMoTa());
			ps.setString(6, ns.getImage());
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	//CẬP NHẬT THÔNG TIN NÔNG SẢN THEO MÃ
	
	
	public boolean updateNongSan(NONGSAN ns, String maNS) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE NONGSAN SET MANS = ?,TENNS = ?,SOLUONG = ?,DONGIA = ?,MOTA = ?,HINHANH = ? WHERE MANS = ?");
			ps.setString(1, ns.getMaNS());
			ps.setString(2, ns.getTenNS());
			ps.setInt(3, ns.getSoLuong());
			ps.setInt(4, ns.getDonGia());
			ps.setString(5, ns.getMoTa());
			ps.setString(6, ns.getImage());
			ps.setString(7, maNS);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	
	//=========================CÁC XỬ LÝ CỦA NHÂN VIÊN====================================
	
	//LẤY THÔNG TIN NHÂN VIÊN THEO MÃ
	
	public NHANVIEN getNhanVienBangID(String maNV) {
		NHANVIEN nv = new NHANVIEN();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM NHANVIEN WHERE MANV = ?");
			ps.setString(1, maNV);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				nv.setMaNV(rs.getString(1));
				nv.setTenNV(rs.getString(2));
				nv.setGioiTinh(rs.getInt(3));
				nv.setDiaChi(rs.getString(4));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}
	
	//LẤY DANH SÁCH NHÂN VIÊN
	
	public ArrayList<NHANVIEN> getNhanVien(){
		ArrayList<NHANVIEN> listNV = new ArrayList<NHANVIEN>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM NHANVIEN");
			while(rs.next()) {
				NHANVIEN nv = new NHANVIEN();
				nv.setMaNV(rs.getString(1));
				nv.setTenNV(rs.getString(2));
				nv.setGioiTinh(rs.getInt(3));
				nv.setDiaChi(rs.getString(4));
				listNV.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listNV;
	}
	
	//XÓA NHÂN VIÊN THEO MÃ
	
	public boolean deleteNhanVien(String maNV) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM NHANVIEN WHERE MANV = ?");
			ps.setString(1, maNV);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//THÊM NHÂN VIÊN
	
	public boolean insertNhanVien(NHANVIEN nv) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO NHANVIEN VALUES (?,?,?,?)");
			ps.setString(1, nv.getMaNV());
			ps.setString(2, nv.getTenNV());
			ps.setInt(3, nv.getGioiTinh());
			ps.setString(4,nv.getDiaChi());
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//CẬP NHẬT THÔNG TIN NHÂN VIÊN THEO MÃ
	
	
	public boolean updateNhanVien(NHANVIEN nv, String maNV) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE NHANVIEN SET MANV = ?,TENNV = ?,GIOITINH = ?,DIACHI = ? WHERE MANV = ?");
			ps.setString(1, nv.getMaNV());
			ps.setString(2, nv.getTenNV());
			ps.setInt(3, nv.getGioiTinh());
			ps.setString(4,nv.getDiaChi());
			ps.setString(5, maNV);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	//=========================CÁC XỬ LÝ CỦA NHẬP NÔNG SẢN====================================
	public ArrayList<NHAPNS> getNNS(){
		ArrayList<NHAPNS> listNNS = new ArrayList<NHAPNS>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT NNS.MAN,NNS.TENKH, NNS.SODT, NNS.DIACHI, NNS.NGAY,NNS.TIENTHANHTOAN,NNS.TRANGTHAI, NNS.MANV,NV.TENNV FROM NHANVIEN NV, NHAPNS NNS WHERE NV.MANV = NNS.MANV ");
			while(rs.next()) {
				NHAPNS nns = new NHAPNS();
				nns.setMaN(rs.getString(1));
				nns.setTenKH(rs.getString(2));
				nns.setSoDT(rs.getString(3));
				nns.setDiaChi(rs.getString(4));
				nns.setNgay(rs.getString(5));
				nns.setTienThanhToan(rs.getInt(6));
				nns.setTrangThai(rs.getInt(7));
				nns.setMaNV(rs.getString(8));
				nns.setTenNV(rs.getString(9));
				listNNS.add(nns);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listNNS;
	}
	
	
	public boolean insertNNS(NHAPNS nns) {
		String sql = "INSERT INTO NHAPNS VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nns.getMaN());
			ps.setString(2, nns.getTenKH());
			ps.setString(3, nns.getSoDT());
			ps.setString(4, nns.getDiaChi());
			ps.setString(5, nns.getNgay());
			ps.setString(6, nns.getMaNV());
			ps.setInt(7, nns.getTienThanhToan());
			ps.setInt(8, nns.getTrangThai());
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean deleteNNS(String maN) {
		String sql = "DELETE FROM CTNHAP WHERE MAN = ?; "
				+ "DELETE FROM NHAPNS WHERE MAN = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maN);
			ps.setString(2, maN);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateNNS(String maN) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE NHAPNS SET TRANGTHAI = 1 WHERE MAN = ?");
	
			ps.setString(1, maN);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	//=========================CÁC XỬ LÝ CỦA CTNHẬP NÔNG SẢN====================================
	public ArrayList<CTNHAP> getCTNhap(String maN){
		ArrayList<CTNHAP> list = new ArrayList<CTNHAP>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT C.MANS ,NS.TENNS , C.MAN, C.SOLUONG, C.DONGIA FROM CTNHAP C, NONGSAN NS WHERE C.MANS = NS.MANS AND MAN = ?");
			ps.setString(1, maN);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CTNHAP c = new CTNHAP();
				c.setMaNS(rs.getString(1));
				c.setTenNS(rs.getString(2));
				c.setMaN(rs.getString(3));
				c.setSoLuong(rs.getInt(4));
				c.setDonGia(rs.getInt(5));
				list.add(c);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public boolean insertCTNhap(CTNHAP c) {
		String sql = "INSERT INTO CTNHAP VALUES (?,?,?,?); "
				+" UPDATE NONGSAN SET SOLUONG = SOLUONG + ? WHERE MANS = ? "
				+" UPDATE NHAPNS SET TIENTHANHTOAN = TIENTHANHTOAN + ? WHERE MAN = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getMaNS());
			ps.setString(2, c.getMaN());
			ps.setInt(3, c.getSoLuong());
			ps.setInt(4, c.getDonGia());
			ps.setInt(5, c.getSoLuong());
			ps.setString(6, c.getMaNS());
			ps.setInt(7, c.getDonGia()*c.getSoLuong());
			ps.setString(8, c.getMaN());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCTNhap(CTNHAP c) {
		String sql = "DELETE FROM CTNHAP WHERE (MANS = ? AND MAN = ?); "
				+" UPDATE NONGSAN SET SOLUONG = SOLUONG - ? WHERE MANS = ?"
				+" UPDATE NHAPNS SET TIENTHANHTOAN = TIENTHANHTOAN - ? WHERE MAN = ? ";
		try {
			PreparedStatement ps1 = con.prepareStatement("SELECT SOLUONG,DONGIA FROM CTNHAP WHERE (MANS = ? AND MAN = ?)");
			ps1.setString(1,c.getMaNS());
			ps1.setString(2, c.getMaN());
			ResultSet rs = ps1.executeQuery();
			
	        PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getMaNS());
			ps.setString(2, c.getMaN());
			while(rs.next()) {
				int sl = rs.getInt(1);
				ps.setInt(3, sl);
				int dg = rs.getInt(2);
				ps.setInt(5, dg*sl);
				
			}
			
			ps.setString(4, c.getMaNS());
			
			ps.setString(6, c.getMaN());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	//=========================CÁC XỬ LÝ CỦA XUẤT NÔNG SẢN====================================
	public ArrayList<XUATNS> getXNS(){
		ArrayList<XUATNS> listXNS = new ArrayList<XUATNS>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT XNS.MAX,XNS.NGAY,XNS.MANV,NV.TENNV FROM NHANVIEN NV, XUATNS XNS WHERE NV.MANV = XNS.MANV ");
			while(rs.next()) {
				XUATNS xns = new XUATNS();
				xns.setMaX(rs.getString(1));
				xns.setNgay(rs.getString(2));
				xns.setMaNV(rs.getString(3));
				xns.setTenNV(rs.getString(4));
				listXNS.add(xns);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listXNS;
	}
	
	
	public boolean insertXNS(XUATNS xns) {
		String sql = "INSERT INTO XUATNS VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, xns.getMaX());
			ps.setString(2, xns.getNgay());
			ps.setString(3, xns.getMaNV());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean deleteXNS(String maX) {
		String sql = "DELETE FROM CTXUAT WHERE MAX = ?; "
				+ "DELETE FROM XUATNS WHERE MAX = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maX);
			ps.setString(2, maX);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	//=========================CÁC XỬ LÝ CỦA CTXUẤT NÔNG SẢN====================================
	public ArrayList<CTXUAT> getCTXuat(String maX){
		ArrayList<CTXUAT> list = new ArrayList<CTXUAT>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT C.MANS, NS.TENNS, C.MAX,C.SOLUONG FROM CTXUAT C,NONGSAN NS WHERE C.MANS = NS.MANS AND MAX = ?");
			ps.setString(1, maX);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CTXUAT c = new CTXUAT();
				c.setMaNS(rs.getString(1));
				c.setTenNS(rs.getString(2));
				c.setMaX(rs.getString(3));
				c.setSoLuong(rs.getInt(4));
				list.add(c);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	public boolean insertCTXuat(CTXUAT c) {
		String sql = "INSERT INTO CTXUAT VALUES (?,?,?);"
				+ " UPDATE NONGSAN SET SOLUONG = SOLUONG - ? WHERE MANS = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getMaNS());
			ps.setString(2, c.getMaX());
			ps.setInt(3, c.getSoLuong());
			ps.setInt(4, c.getSoLuong());
			ps.setString(5, c.getMaNS());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteCTXuat(CTXUAT c) {
		String sql = "DELETE FROM CTXUAT WHERE (MANS = ? AND MAX = ?); "
				+ "	UPDATE NONGSAN SET SOLUONG = SOLUONG + ? WHERE MANS = ?";
		try {
			PreparedStatement ps1 = con.prepareStatement("SELECT SOLUONG FROM CTXUAT WHERE (MANS = ? AND MAX = ?)");
			ps1.setString(1,c.getMaNS());
			ps1.setString(2, c.getMaX());
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getMaNS());
			ps.setString(2, c.getMaX());
			while(rs.next()) {
				int sl = rs.getInt(1);
				ps.setInt(3, sl);
			}
			ps.setString(4, c.getMaNS());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	//================================ĐĂNG NHẬP=========================================
	public ArrayList<DANGNHAP> getDangNhap(){
		ArrayList<DANGNHAP> list = new ArrayList<DANGNHAP>();
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM DANGNHAP");	
			while(rs.next()) {
				DANGNHAP dn = new DANGNHAP();
				dn.setMaNV(rs.getString(1));
				dn.setUserName(rs.getString(2));
				dn.setPassWord(rs.getString(3));
				dn.setRule(rs.getInt(4));
				list.add(dn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public boolean addDangNhap(DANGNHAP dn) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO DANGNHAP VALUES (?,?,?,?)");
			ps.setString(1, dn.getMaNV());
			ps.setString(2, dn.getUserName());
			ps.setString(3, dn.getPassWord());
			ps.setInt(4, 0);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
