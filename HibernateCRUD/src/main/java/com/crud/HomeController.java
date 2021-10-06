package com.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.Param;

import com.crud.beans.Details;
import com.crud.dao.DetailsDao;

@Controller
public class HomeController {
	
	@Autowired
	private DetailsDao dao;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("details") Details details) {
		int i = dao.save(details);
		System.out.println(i);
		return "redirect:/";
	}
	
	@GetMapping("/all-users/{firstPage}")
	public String getAll(@PathVariable("firstPage") int pageNo, Model m) {
		
		List<Details> list = dao.getAllUsers();	
		List<Details> page = dao.getAllUsersOne(pageNo);
		long totalPages = dao.totalPages();
		totalPages = (totalPages/8)+1;
		System.out.println(totalPages);
		m.addAttribute("details", page);
		m.addAttribute("pageNo", pageNo);
		m.addAttribute("totalPages", totalPages);
		
		return "users";
	}
	
	@GetMapping("/update-details/{id}")
	public String updateForm(@PathVariable("id") int id, Model m) {
		Details id2 = dao.getId(id);
		m.addAttribute("user", id2);
		return "update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute("details") Details details, Model m) {
		System.out.println(details);
		dao.updateDetails(details);
		return "redirect:/all-users/1";
		
	}
	
	@GetMapping("/delete-details/{id}")
	public String delete(@PathVariable("id") int id, Model m) {
		dao.deleteDetails(id);
		return "redirect:/all-users/1";
	}

}
