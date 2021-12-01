package com.viet.NSAPI;


import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viet.DAO.*;
import com.viet.MODEL.CTNHAP;
import com.viet.MODEL.CTXUAT;
import com.viet.MODEL.DANGNHAP;
import com.viet.MODEL.NHANVIEN;
import com.viet.MODEL.NHAPNS;
import com.viet.MODEL.NONGSAN;
import com.viet.MODEL.XUATNS;


@RestController
@ComponentScan()
public class NSAPI {
	
	//============NÔNG SẢN============
	
	@GetMapping(value = "/nongsan",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ArrayList<NONGSAN> getNS() {
		return new DAO().getNongSan();
	}
	
	@PostMapping(value ="/nongsan",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean insertNS( @Valid @RequestBody NONGSAN ns ) {
		return new DAO().insertNongSan(ns);
	}
	
	@DeleteMapping(value = "/nongsan/{maNS}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean deleteNS(@PathVariable("maNS") String maNS) {
		return new DAO().deleteNongSan(maNS);
	}
	@PutMapping(value = "/nongsan/{maNS}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean updateNS(@Valid @RequestBody NONGSAN ns,@PathVariable("maNS") String maNS) {
		return new DAO().updateNongSan(ns, maNS);
	}
	@GetMapping(value = "/nongsan/{maNS}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public NONGSAN getNongSanBangID(@PathVariable("maNS") String maNS) {
		return new DAO().getNongSanBangID(maNS);
	}
	
	
	
	//============NHÂN VIÊN============
	
	
	
	@GetMapping(value = "/nhanvien",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ArrayList<NHANVIEN> getNV() {
		return new DAO().getNhanVien();
	}
	@PostMapping(value = "/nhanvien",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean insertNV( @Valid @RequestBody NHANVIEN nv ) {
		return new DAO().insertNhanVien(nv);
	}

	
	@DeleteMapping(value = "/nhanvien/{maNV}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean deleteNV(@PathVariable("maNV") String maNV) {
		return new DAO().deleteNhanVien(maNV);
	}
	@PutMapping(value = "/nhanvien/{maNV}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean updateVT(@Valid @RequestBody NHANVIEN nv,@PathVariable("maNV") String maNV) {
		return new DAO().updateNhanVien(nv, maNV);
	}
	@GetMapping(value = "/nhanvien/{maNV}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public NHANVIEN getNvById(@PathVariable("maNV") String maNV) {
		return new DAO().getNhanVienBangID(maNV);
	}

	
	//============NHẬP NÔNG SẢN============
	
	
	
	@GetMapping(value = "/nhapnongsan",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ArrayList<NHAPNS> getNNS(){
		return new DAO().getNNS();
	}
	
	@PostMapping(value = "/nhapnongsan",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean insertNNS(@Valid @RequestBody NHAPNS nns){
		return new DAO().insertNNS(nns);
	}
	
	@PutMapping(value = "/nhapnongsan/{maN}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean updateNNS( @PathVariable("maN") String maN) {
		return new DAO().updateNNS(maN);
	}
	
	@DeleteMapping(value = "/nhapnongsan/{maN}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean deletePN(@PathVariable("maN") String maN){
		return new DAO().deleteNNS(maN);
	}

	
	//============CTNHẬP NÔNG SẢN============
	
	
	
	@GetMapping(value = "/ctn/{maN}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ArrayList<CTNHAP> getCTN(@PathVariable("maN") String maN){
		return new DAO().getCTNhap(maN);
	}
	@PostMapping(value = "/ctn",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean insertCTN(@Valid @RequestBody CTNHAP c){
		return new DAO().insertCTNhap(c);
	}
	@DeleteMapping(value = "/ctn/{maNS}/{maN}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean deleteCTN(@PathVariable("maNS") String maNS, @PathVariable("maN") String maN){
		CTNHAP c = new CTNHAP();
		c.setMaNS(maNS);
		c.setMaN(maN);
		return new DAO().deleteCTNhap(c);
	}

	
	//============XUẤT NÔNG SẢN============
	
	
	
	@GetMapping(value = "/xuatnongsan",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ArrayList<XUATNS> getXNS(){
		return new DAO().getXNS();
	}
	@PostMapping(value = "/xuatnongsan",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean insertXNS(@Valid @RequestBody XUATNS xns){
		return new DAO().insertXNS(xns);
	}
	@DeleteMapping(value = "/xuatnongsan/{maX}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean deletePX(@PathVariable("maX") String maX){
		return new DAO().deleteXNS(maX);
	}

	
	//============CTNHẬP NÔNG SẢN============
	
	
	
	@GetMapping(value = "/ctx/{maX}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ArrayList<CTXUAT> getCTX(@PathVariable("maX") String maX){
		return new DAO().getCTXuat(maX);
	}
	@PostMapping(value = "/ctx",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean insertCTX(@Valid @RequestBody CTXUAT c){
		return new DAO().insertCTXuat(c);
	}
	@DeleteMapping(value = "/ctx/{maNS}/{maX}",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean deleteCTX(@PathVariable("maNS") String maNS, @PathVariable("maX") String maX){
		CTXUAT c = new CTXUAT();
		c.setMaNS(maNS);
		c.setMaX(maX);
		return new DAO().deleteCTXuat(c);
	}

	
	//============ĐĂNG NHẬP============
	
	
	
	@GetMapping(value = "/dangnhap",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ArrayList<DANGNHAP> getDangNhap(){
		return new DAO().getDangNhap();
	}
	@PostMapping(value = "/dangky",produces = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public boolean insertDangNhap(@Valid @RequestBody DANGNHAP dn){
		return new DAO().addDangNhap(dn);
	}
}
