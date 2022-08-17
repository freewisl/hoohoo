package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Member;
import com.example.repository.CartRepository;
import com.example.repository.MemberRepository;
import com.example.security.MyUser;

@Controller
public class HomeController {

	@Autowired
	MemberRepository mRepository;
	
	@Autowired
	CartRepository cRepository;
	
	// 127.0.0.1:9091/ROOT/home
	// 127.0.0.1:9091/ROOT/main
	// 127.0.0.1:9091/ROOT/
	@RequestMapping(value = { "/home", "/main", "/" })
	public String homeGET(Model model, Authentication auth, HttpSession httpSession, HttpServletRequest request) {

		if (auth != null) {
			MyUser user = (MyUser) auth.getPrincipal();
			String id = user.getId();
			Member vo2 = mRepository.findById(id);
			long idnumber = vo2.getNo();
			long cartCnt = cRepository.sqlCountByMemno(idnumber);
			
			httpSession = request.getSession();
			httpSession.setAttribute("cartCnt", cartCnt);
		} else {
			long cartCnt = 0L;
			httpSession = request.getSession();
			httpSession.setAttribute("cartCnt", cartCnt);
		}
		return "index";
	}

	@RequestMapping(value = "/page403")
	public String _403() {
		return "page403";
	}
}
