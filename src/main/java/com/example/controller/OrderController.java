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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Cart;
import com.example.entity.Item;
import com.example.entity.Member;
import com.example.repository.CartRepository;
import com.example.repository.ItemRepository;
import com.example.repository.MemberRepository;
import com.example.security.MyUser;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	MemberRepository mRepository;

	@Autowired
	ItemRepository iRepository;

	@Autowired
	CartRepository cRepository;

	@RequestMapping(value = "/cart_list")
	public String cartListGET(Model model, Authentication auth, HttpSession httpSession, HttpServletRequest request) {
		MyUser user = (MyUser) auth.getPrincipal();
		String id = user.getId();
		Member vo1 = mRepository.findById(id);
		long idnumber = vo1.getNo();

		System.out.println(idnumber);

		List<Cart> list = cRepository.sqlSelectByMemno(idnumber);

		for (Cart vo : list) {
			if (vo.getItem().getItemimg() != null) {
				String tmp = Base64.getEncoder().encodeToString(vo.getItem().getItemimg());
				vo.getItem().setBase64(tmp);
				vo.getItem().setItemimg(null);
			}
		}

		model.addAttribute("list", list);

		long cartCnt = cRepository.sqlCountByMemno(idnumber);

		httpSession = request.getSession();
		httpSession.setAttribute("cartCnt", cartCnt);

		return "order/cart_list";
	}

	@RequestMapping(value = "/cart_insert", method = RequestMethod.POST)
	public String cartInsertPOST(Model model, @RequestParam("no") long no, @RequestParam("ordercnt") long cnt,
			Authentication auth, HttpSession httpSession, HttpServletRequest request) {

		if (auth == null) {
			return "redirect:/member/login";
		}

		Cart vo = new Cart();
		vo.setCnt(cnt);

		Item vo1 = iRepository.getOne(no);
		vo.setItem(vo1);

		MyUser user = (MyUser) auth.getPrincipal();
		String id = user.getId();

		Member vo2 = mRepository.findById(id);
		vo.setMember(vo2);

		if (cRepository.sqlCountByItemnoAndMemno(vo1.getNo(), vo2.getNo()) >= 1) {
			System.out.println("중복되는 ITEM 있음");
			Cart vo3 = cRepository.sqlSelectByItemno(vo1.getNo());
			vo.setNo(vo3.getNo());

			cRepository.sqlUpdateById(vo);

			return "redirect:/item/item_detail?no=" + no;
		}

		cRepository.save(vo);

		long idnumber = vo2.getNo();
		long cartCnt = cRepository.sqlCountByMemno(idnumber);

		httpSession = request.getSession();
		httpSession.setAttribute("cartCnt", cartCnt);

		return "redirect:/item/item_detail?no=" + no;
	}

	@RequestMapping(value = "/cart_batch_delete", method = RequestMethod.POST)
	public String cartDeletePOST(@RequestParam(value = "chk[]") List<Long> no) {

		if (!no.isEmpty()) {
			cRepository.cartBatchDelete(no);
		}

		return "redirect:/order/cart_list";
	}

	@RequestMapping(value = "/cart_empty", method = RequestMethod.POST)
	public String cartEmptyPOST(Authentication auth) {
		System.out.println("ddddddddd");
		MyUser user = (MyUser) auth.getPrincipal();
		String id = user.getId();

		Member vo = mRepository.findById(id);

		long idnumber = vo.getNo();

		cRepository.cartEmptyDelete(idnumber);

		return "redirect:/order/cart_list";
	}
}
