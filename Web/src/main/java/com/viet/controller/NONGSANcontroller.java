package com.viet.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.viet.MODEL.NONGSAN;
import com.viet.MODEL.XUATNS;
import com.viet.MODEL.DANGNHAP;
import com.viet.MODEL.NHANVIEN;
import com.viet.MODEL.NHAPNS;

@Controller
public class NONGSANcontroller {
	@RequestMapping("/")
	public String index(HttpSession session) {
		return "login";
	}
	@PostMapping("/login")
	public String login(String username, String password,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<DANGNHAP[]> list = rt.getForEntity("http://localhost:8080/dangnhap", DANGNHAP[].class);
		DANGNHAP users[] = list.getBody();
		for (DANGNHAP u : users) {
			if(u.getUserName().equalsIgnoreCase(username) && u.getPassWord().equalsIgnoreCase(password)) {
				request.getSession().setAttribute("user", u);
				return "redirect:/admin";
			}
		}
		return "redirect:/";
		
	}
	@RequestMapping("/admin")
	public String getNS(Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<NONGSAN[]> list = rt.getForEntity("http://localhost:8080/nongsan", NONGSAN[].class);
		NONGSAN[] listNS = list.getBody();
		model.addAttribute("listNS",listNS);
		return "admin";
	}
	@RequestMapping("/addproduct")
	public String addpro() {
		return "addproduct";
	}
	@RequestMapping("/addNS")
	public String addNS(NONGSAN ns,Model model) {
		RestTemplate rt = new RestTemplate();
		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/nongsan", ns, String.class));
		return "redirect:admin";
	}
	@RequestMapping("/deleteNS/{maNS}")
	public String deleteNS(@PathVariable("maNS") String maNS) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNS", maNS);
		rt.delete("http://localhost:8080/nongsan/{maNS}", params);
		return "redirect:../admin";
	}
	@RequestMapping("editNS/{maNS}")
	public String editNongSan(@PathVariable("maNS") String maNS,Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNS", maNS);
		NONGSAN ns = rt.getForObject("http://localhost:8080/nongsan/{maNS}", NONGSAN.class, params);
		model.addAttribute("ns",ns);
		model.addAttribute("maNS",ns.getMaNS());
		ResponseEntity<NONGSAN[]> list = rt.getForEntity("http://localhost:8080/nongsan", NONGSAN[].class);
		NONGSAN[] listNS = list.getBody();
		model.addAttribute("listNS",listNS);
		return "editproduct";
	}
	@RequestMapping("/editNongSan/{maNS}")
	public String editNS(@PathVariable("maNS") String maNS,NONGSAN ns) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("maNS", maNS);
		rt.put("http://localhost:8080/nongsan/{maNS}",ns,params);
		return "redirect:../admin"; 
	}
}
