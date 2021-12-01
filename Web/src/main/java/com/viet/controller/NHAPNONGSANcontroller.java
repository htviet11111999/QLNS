package com.viet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.viet.MODEL.CTNHAP;
import com.viet.MODEL.DANGNHAP;
import com.viet.MODEL.NHANVIEN;
import com.viet.MODEL.NHAPNS;

@Controller
public class NHAPNONGSANcontroller {
	@GetMapping("/enter")
	public String getNNS(Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<NHAPNS[]> list = rt.getForEntity("http://localhost:8080/nhapnongsan", NHAPNS[].class);
		NHAPNS[] listNNS = list.getBody();
		model.addAttribute("listNNS",listNNS);
		return "enter";
	}
	
	@RequestMapping("/deleteNNS/{maN}")
	public String deleteNNS(Model model,@PathVariable("maN") String maN) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maN", maN);
		ResponseEntity<CTNHAP[]> list = rt.getForEntity("http://localhost:8080/ctn/{maN}",CTNHAP[].class,params);
		CTNHAP listNNS[] = list.getBody();
		if(listNNS.length == 0) {
		rt.delete("http://localhost:8080/nhapnongsan/{maN}",params);
		}
		return "redirect:../enter";
	}
	
	@RequestMapping("/addenter")
	public String addentero() {
		return "addenter";
	}
	
	@ModelAttribute("nv")
	public NHANVIEN getNV(HttpSession session) {
		DANGNHAP l = (DANGNHAP) session.getAttribute("user");
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNV",String.valueOf(l.getMaNV()) );
		NHANVIEN nv = rt.getForObject("http://localhost:8080/nhanvien/{maNV}", NHANVIEN.class,params);
		return nv;
	}
	@RequestMapping("insertNNS")
	public String insertNNS(NHAPNS nns) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject("http://localhost:8080/nhapnongsan",nns,String.class);
		return "redirect:enter";
	}

}
