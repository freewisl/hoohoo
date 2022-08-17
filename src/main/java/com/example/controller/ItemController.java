package com.example.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Item;
import com.example.entity.Member;
import com.example.repository.CartRepository;
import com.example.repository.ItemCateRepository;
import com.example.repository.ItemRepository;
import com.example.repository.MemberRepository;
import com.example.security.MyUser;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

	@Autowired
	ItemRepository iRepository;

	@Autowired
	ItemCateRepository icRepository;

	@Autowired
	MemberRepository mRepository;

	@Autowired
	CartRepository cRepository;

	@RequestMapping(value = "/list")
	public String itemlistallGET(Model model, @RequestParam(value = "cate") String cateid) {
		List<Item> list = null;

		if (cateid.equals("all")) {
			list = iRepository.findAllByOrderByNoDesc();
		} else {
			list = iRepository.selectQueryItemcateOrderByItemNoDesc(cateid);
		}

		// 이미지가 jsp에서 표시가 안되기 때문에 byte[] base64로 인코딩
		for (Item vo : list) {
			// byte[] => String(base64)
			if (vo.getItemimg() != null) {
				String tmp = Base64.getEncoder().encodeToString(vo.getItemimg());
				vo.setBase64(tmp);
				vo.setItemimg(null);
			}
		}

		model.addAttribute("list", list);

		// byte[] => string
		return "item/item_list";
	}

	@RequestMapping(value = "/item_detail")
	public String itemdetailtGET(Model model, @RequestParam(value = "no") long no, HttpSession httpSession,
			HttpServletRequest request, Authentication auth) {

		Item vo = iRepository.findByNo(no);
		if (vo.getItemimg() != null) {
			String tmp = Base64.getEncoder().encodeToString(vo.getItemimg());
			vo.setBase64(tmp);
			vo.setItemimg(null);
		}

		model.addAttribute("vo", vo);

		if (auth != null) {
			MyUser user = (MyUser) auth.getPrincipal();
			String id = user.getId();

			Member vo2 = mRepository.findById(id);

			long idnumber = vo2.getNo();
			long cartCnt = cRepository.sqlCountByMemno(idnumber);

			httpSession = request.getSession();
			httpSession.setAttribute("cartCnt", cartCnt);
		}

		return "item/item_detail";
	}
}
