package com.example.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Board;
import com.example.entity.Item;
import com.example.entity.ItemCate;
import com.example.entity.Member;
import com.example.repository.BoardRepository;
import com.example.repository.ItemCateRepository;
import com.example.repository.ItemRepository;
import com.example.repository.MemberRepository;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	BoardRepository bRepository;

	@Autowired
	ItemCateRepository icRepository;

	@Autowired
	ItemRepository iRepository;

	@Autowired
	MemberRepository mRepository;

	@RequestMapping(value = "/notice_write")
	public String boardwriteGET(Model model) {
		// 현재 로그인 아이디
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		System.out.println("================================" + user.getUsername());

		// jsp로 보내기
		model.addAttribute("currentUser", user.getUsername());

		return "admin/notice_write";
	}

	@RequestMapping(value = "/notice_write", method = RequestMethod.POST)
	public String boardwritePOST(@ModelAttribute Board vo) {
		System.out.println(vo.toString());

		bRepository.save(vo);

		return "redirect:/board/notice";
	}

	@RequestMapping(value = "/cate_insert")
	public String cateinsertGET() {
		return "admin/cate_insert";
	}

	@RequestMapping(value = "/cate_insert", method = RequestMethod.POST)
	public String cateinserPOST(@ModelAttribute ItemCate vo) {
		System.out.println(vo.toString());
		icRepository.save(vo);
		return "redirect:/admin/cate_insert";
	}

	@RequestMapping(value = "/item_insert")
	public String iteminsertGET(Model model) {
		List<ItemCate> cateList = icRepository.findAll();
		model.addAttribute("cateList", cateList);
		return "admin/item_insert";
	}

	@RequestMapping(value = "/item_insert", method = RequestMethod.POST)
	public String iteminserPOST(@ModelAttribute Item vo, @RequestParam("img1") MultipartFile file,
			@RequestParam(value = "itemcateid") String itemcateid) throws IOException {
		if (file.getBytes().length > 0) {
			vo.setItemimg(file.getBytes());
		}

		// 카테고리 정보 : itemcateid를 이용해서 객체를 가져옴
		Optional<ItemCate> itemcate = icRepository.findById(itemcateid);
		vo.setItemcate(itemcate.get());

		System.out.println(vo.toString());
		iRepository.save(vo);

		return "redirect:/admin/item_insert";
	}

	@RequestMapping(value = "/notice_update")
	public String updateGET(Model model, @RequestParam(value = "no") long no) {
		System.out.println(no);
		// 한개 꺼내기
		// Optional의 value는 값이 있을 수도 있고 null 일 수 도 있다.
		Optional<Board> vo1 = bRepository.findAllByNo(no);
		Board vo2 = vo1.get();
		model.addAttribute("vo", vo2);

		return "admin/notice_update";
	}

	@RequestMapping(value = "/notice_update", method = RequestMethod.POST)
	public String updatePOST(@ModelAttribute Board vo, @RequestParam(value = "no") long no,
			@RequestParam(value = "txt") String txt, @RequestParam(value = "page") int page)
			throws UnsupportedEncodingException {
		System.out.println(vo.toString());

		bRepository.save(vo);
		return "redirect:/board/notice_detail?no=" + no + "&txt=" + URLEncoder.encode(txt, "UTF-8") + "&page=" + page;
	}

	@RequestMapping(value = "/member_list")
	public String boardnoticeGET(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "txt", defaultValue = "", required = false) String txt)
			throws UnsupportedEncodingException {

		// 페이지가 0이면 주소창에 검색어와 1페이지로 가게 하기
		if (page == 0) {
			return "redirect:/admin/member_list?txt=" + URLEncoder.encode(txt, "UTF-8") + "&page=1";
		}

		PageRequest pageable = PageRequest.of(page - 1, 10);

		List<Member> list = mRepository.selectQueryWhereMemname(txt, pageable);
		model.addAttribute("list", list);

		long totPages = mRepository.countByNameIgnoreCaseContaining(txt);
		model.addAttribute("totPages", (totPages - 1) / 10 + 1);

		return "admin/member_list";
	}

	@RequestMapping(value = "/member_batch_delete", method = RequestMethod.POST)
	public String batchDeletePOST(@RequestParam(value = "chk[]") List<Long> no) {
		if (!no.isEmpty()) {
			mRepository.memberBatchDelete(no);
		}
		return "redirect:/admin/member_list";
	}

	@RequestMapping(value = "/item_delete", method = RequestMethod.POST)
	public String itemDeletePOST(@RequestParam(value = "no") long no) {
		iRepository.sqlDeleteByNo(no);
		return "redirect:/item/list?cate=all";
	}
}
