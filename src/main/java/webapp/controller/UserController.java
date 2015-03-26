package webapp.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webapp.model.Address;
import webapp.model.User;

@Controller
public class UserController {

	//
	// /m/user/register?name=jylee&job=programmer&addrs[0].country=korea&addrs[0].city=seoul&addrs[0].gu=lee
	// /m/user/register?name=jylee&age=18&birthday=2015-03-26&job=programmer&job=car&addrs[0].country=korea&addrs[0].city=seoul&addrs[0].gu=lee&addrs[1].country=usa&addrs[1].city=busan&addrs[1].gu=jong&addrs[2].country=china&addrs[2].city=jeju&addrs[2].gu=nowon
	@RequestMapping("/user/registertest")
	public void register(User user, PrintWriter out) {
		out.println("user.name = " + user.getName() + "<br>");
		out.println("user.age = " + user.getAge() + "<br>");
		out.println("user.birthday = " + user.getBirthday() + "<br>");
		
		String[] job = user.getJob();
		if(job != null)
			for(String j : job)
				out.println("user.job = " + j + "<br>");
		
		List<Address> addrs = user.getAddrs();
		if(addrs != null)
			for(Address a : addrs) {
				out.println("user.addrs.country = " + a.getCountry() + "<br>");
				out.println("user.addrs.city = " + a.getCity() + "<br>");
				out.println("user.addrs.gu = " + a.getGu() + "<br>");
			}
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.GET)
	public String register() {
		
		return "user/register";
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	public String register(User user, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("error", "바인딩 에러");
			return "user/register";
		}
		
		return "user/registersucess";
	}
	
}
