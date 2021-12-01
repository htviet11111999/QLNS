package com.viet.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.viet.MODEL.NHANVIEN;
@Controller
public class NHANVIENcontroller {
	
	@GetMapping("/liststaff")
	public String getNHANVIEN(Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<NHANVIEN[]> list = rt.getForEntity("http://localhost:8080/nhanvien",NHANVIEN[].class);
		NHANVIEN[] listNV = list.getBody();
		model.addAttribute("listNV",listNV);
		return "liststaff";
	}
	@RequestMapping("/deleteNV/{maNV}")
	public String deleteNV(@PathVariable("maNV") String maNV) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNV", maNV);
		rt.delete("http://localhost:8080/nhanvien/{maNV}", params);
		return "redirect:../liststaff";
	}
	
	@RequestMapping("/add-sta")
	public String addsta() {
		return "add-sta";
	}
	
	@PostMapping("/nhanvien")
	public String addNhanvien(NHANVIEN nv) {
		RestTemplate rt = new RestTemplate();
		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/nhanvien", nv, String.class));
		return "redirect:liststaff";
	}
	@RequestMapping("/editNV/{maNV}")
	public String edit(@PathVariable("maNV") String maNV,Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNV", maNV);
		NHANVIEN nv = rt.getForObject("http://localhost:8080/nhanvien/{maNV}", NHANVIEN.class, params);
		model.addAttribute("nv",nv);
		model.addAttribute("maNV",nv.getMaNV());
		ResponseEntity<NHANVIEN[]> list = rt.getForEntity("http://localhost:8080/nhanvien", NHANVIEN[].class);
		NHANVIEN[] listNV = list.getBody();
		model.addAttribute("listNV",listNV);
		return "edit-sta";
	}
	@RequestMapping("/editNhanVien/{maNV}")
	public String editNhanvien(NHANVIEN nv,@PathVariable("maNV") String maNV) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNV", maNV);
		rt.put("http://localhost:8080/nhanvien/{maNV}",nv,params);
		return "redirect:../liststaff"; 
	}
}
