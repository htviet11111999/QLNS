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

import com.viet.MODEL.CTXUAT;
import com.viet.MODEL.DANGNHAP;
import com.viet.MODEL.NHANVIEN;
import com.viet.MODEL.XUATNS;

@Controller
public class XUATNONGSANcontroller {
	@GetMapping("/delivery")
	public String getXNS(Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<XUATNS[]> list = rt.getForEntity("http://localhost:8080/xuatnongsan", XUATNS[].class);
		XUATNS[] listXNS = list.getBody();
		model.addAttribute("listXNS",listXNS);
		return "delivery";
	}
	
	@RequestMapping("/deleteXNS/{maX}")
	public String deleteXNS(Model model,@PathVariable("maX") String maX) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maX", maX);
		ResponseEntity<CTXUAT[]> list = rt.getForEntity("http://localhost:8080/ctx/{maX}",CTXUAT[].class,params);
		CTXUAT listXNS[] = list.getBody();
		if(listXNS.length == 0) {
		rt.delete("http://localhost:8080/xuatnongsan/{maX}",params);
		}
		return "redirect:../delivery";
	}
	
	@RequestMapping("/adddelivery")
	public String adddeli() {
		return "adddelivery";
	}
	@ModelAttribute("nv")
	public NHANVIEN getNV(HttpSession session) {
		DANGNHAP l = (DANGNHAP) session.getAttribute("user");
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNV", String.valueOf(l.getMaNV()));
		NHANVIEN nv = rt.getForObject("http://localhost:8080/nhanvien/{maNV}", NHANVIEN.class,params);
		return nv;
	}
	
	@RequestMapping("insertXNS")
	public String insertXNS(XUATNS nns) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject("http://localhost:8080/xuatnongsan",nns,String.class);
		return "redirect:delivery";
	}
}
