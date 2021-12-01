package com.viet.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.viet.MODEL.*;

@Controller
public class CHITIETNHAP_XUATcontroller {
	//========================== CHI TIẾT NHẬP NÔNG SẢN =======================================
	@GetMapping("/ctn")
	public String getCT(Model model,String maN) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("maN", maN);
		ResponseEntity<CTNHAP[]> list = rt.getForEntity("http://localhost:8080/ctn/{maN}",CTNHAP[].class,params);
		CTNHAP listNNS[] = list.getBody();
		model.addAttribute("listNNS", listNNS);
		model.addAttribute("maN",maN);
		return "enterDetail";
	}
	@PostMapping("/insertCTN")
	public String insertCTN(CTNHAP c) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject("http://localhost:8080/ctn", c, String.class);
		return "redirect:/ctn/?maN="+ c.getMaN();
	}
	@GetMapping("/deleteCTN")
	public String deleteCT(CTNHAP c) {
		RestTemplate template = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("maN", String.valueOf(c.getMaN()) );
		params.put("maNS", String.valueOf(c.getMaNS()));
		template.delete("http://localhost:8080/ctn/{maNS}/{maN}",params);
		return "redirect:../ctn?maN=" + c.getMaN();
	}
	
	
	//========================== CHI TIẾT XUẤT NÔNG SẢN =======================================
	
	@GetMapping("/ctx")
	public String getCTPX(Model model,String maX) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("maX", maX);
		ResponseEntity<CTXUAT[]> list = rt.getForEntity("http://localhost:8080/ctx/{maX}",CTXUAT[].class,params);
		CTXUAT listXNS[] = list.getBody();
		model.addAttribute("listXNS", listXNS);
		model.addAttribute("maX",maX);
		return "deliveryDetail";
	}
	@PostMapping("/insertCTX")
	public String insertCTPN(CTXUAT c) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<NONGSAN[]> list = rt.getForEntity("http://localhost:8080/nongsan", NONGSAN[].class);
		NONGSAN ns[] = list.getBody();
		for (NONGSAN u : ns) {
			if (c.getMaNS() == u.getMaNS() && c.getSoLuong() <= u.getSoLuong()) {
					rt.postForObject("http://localhost:8080/ctx", c, String.class);
			
			}
		}
		return "redirect:ctx?maX=" + c.getMaX();
	}
	@GetMapping("/deleteCTX")
	public String deleteCTPX(CTXUAT c) {
		RestTemplate template = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("maX", String.valueOf(c.getMaX()));
		params.put("maNS",String.valueOf(c.getMaNS()));
		template.delete("http://localhost:8080/ctx/{maNS}/{maX}",params);
		return "redirect:../ctx?maX=" + c.getMaX();
	}
	
	
	@ModelAttribute("ns")
	public NONGSAN[] getListVT() {
		NONGSAN ns[] = null;
		RestTemplate template = new RestTemplate();
		ResponseEntity<NONGSAN[]> list = template.getForEntity("http://localhost:8080/nongsan", NONGSAN[].class);
		ns = list.getBody();
		return ns;
	}
	
}
