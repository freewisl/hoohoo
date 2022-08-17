package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.example.entity.Board;
import com.example.entity.QaBoard;
import com.example.entity.QaBoardReply;
import com.example.repository.BoardRepository;
import com.example.repository.QaBoardRepository;
import com.example.repository.QaReplyRepository;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardRepository bRepository;
	
	@Autowired
	QaBoardRepository qaRepository;
	
	@Autowired
	QaReplyRepository qrRepository;

	@RequestMapping(value = "/notice")
	public String boardnoticeGET(Model model, HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "txt", defaultValue = "", required = false) String txt)
			throws UnsupportedEncodingException {

		httpSession = request.getSession();
		httpSession.setAttribute("CURRNO", 0L);

		// 페이지가 0이면 주소창에 검색어와 1페이지로 가게 하기
		if (page == 0) {
			return "redirect:/board/notice?txt=" + URLEncoder.encode(txt, "UTF-8") + "&page=1";
		}

		PageRequest pageable = PageRequest.of(page - 1, 10);

		List<Board> list = bRepository.countQueryWhereNoticeno(txt, pageable);
		// List<Board> list = bRepository.findAll();
		model.addAttribute("list", list);

		long totPages = bRepository.countByTitleIgnoreCaseContaining(txt);
		model.addAttribute("totPages", (totPages - 1) / 10 + 1);

		return "board/notice_list";
	}

	@RequestMapping(value = "/notice_detail")
	public String noticedetailGET(Model model, @RequestParam(value = "no") long no, HttpSession httpSession,
			HttpServletRequest request) {

		httpSession = request.getSession();

		Long currno = (Long) httpSession.getAttribute("CURRNO");

		if (currno != null) {
			if (currno != no) {
				bRepository.sqlUpdateHitByNo(no);
				httpSession.setAttribute("CURRNO", no);
			}
		}
		
		Board vo = bRepository.findByNo(no);
		model.addAttribute("vo", vo);
		
		Board prev = bRepository.sqlPrevByNo(no);
		Board next = bRepository.sqlNextByNo(no);
		
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		System.out.println("prev = " + prev);
		System.out.println("next = " + next);

		return "board/notice_detail";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(@RequestParam(value = "no") long no) {
		
		/*
		 * if(rRepository.selectQueryWhereBrdno(no) == null) {
		 * bRepository.sqlDeleteByNo(no); bRepository.sqlUpdateRepcntAll(); } else {
		 * rRepository.sqlDeleteByBrdno(no); bRepository.sqlDeleteByNo(no);
		 * bRepository.sqlUpdateRepcntAll(); }
		 */
		bRepository.sqlDeleteByNo(no);
		
		return "redirect:/board/notice";
	}
	
	@RequestMapping(value = "/qalist")
	public String qaboardGET(Model model, HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "txt", defaultValue = "", required = false) String txt)
			throws UnsupportedEncodingException {

		httpSession = request.getSession();
		httpSession.setAttribute("CURRNO", 0L);

		// 페이지가 0이면 주소창에 검색어와 1페이지로 가게 하기
		if (page == 0) {
			return "redirect:/board/qalist?txt=" + URLEncoder.encode(txt, "UTF-8") + "&page=1";
		}

		PageRequest pageable = PageRequest.of(page - 1, 10);

		List<Board> list = bRepository.countQueryWhereNoticeno(txt, pageable);
		// List<Board> list = bRepository.findAll();
		model.addAttribute("list", list);

		long totPages = bRepository.countByTitleIgnoreCaseContaining(txt);
		model.addAttribute("totPages", (totPages - 1) / 10 + 1);

		return "board/qa_list";
	}
	
	@RequestMapping(value = "/qa_write")
	public String boardwriteGET(Model model) {
		// 현재 로그인 아이디
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		System.out.println("================================"+user.getUsername());
		
		// jsp로 보내기
		model.addAttribute("currentUser", user.getUsername());
		
		return "board/qa_write";
	}
	
	@RequestMapping(value = "/qa_write", method = RequestMethod.POST)
	public String boardwritePOST(@ModelAttribute QaBoard vo) {
	System.out.println(vo.toString());
	
	qaRepository.save(vo);
	
	return "redirect:/board/qa_list";
	}
	
	@RequestMapping(value = "/qa_list")
	public String qalistGET(Model model, @RequestParam(value = "txt", defaultValue = "", required = false) String txt,
			@RequestParam(value = "page", defaultValue = "0") int page,
			HttpSession httpSession, HttpServletRequest request) throws UnsupportedEncodingException {

		httpSession = request.getSession();
		httpSession.setAttribute("CURRNO", 0L);

		// 댓글갯수 업데이트
		qaRepository.sqlUpdateRepcntAll();

		// 페이지가 0이면 주소창에 검색어와 1페이지로 가게 하기
		if (page == 0) {
			return "redirect:/board/qa_list?txt=" + URLEncoder.encode(txt, "UTF-8") + "&page=1";
		}

		PageRequest pageable = PageRequest.of(page - 1, 10);

		List<QaBoard> list1 = qaRepository.countQueryWherQano(txt, pageable);
		model.addAttribute("list1", list1);

		long totPages = qaRepository.countByTitleIgnoreCaseContaining(txt);
		model.addAttribute("totPages", (totPages - 1) / 10 + 1);

		return "board/qa_list";
	}
	
	@RequestMapping(value = "/qa_detail")
	public String qadetailGET(Model model, @RequestParam(value = "no") long no, HttpSession httpSession,
			HttpServletRequest request) {

		// 세션생성해서 받아오기
		httpSession = request.getSession();
		
		Long currno = (Long) httpSession.getAttribute("CURRNO");
		
		if (currno != null) {
			if(currno != no) {
				qaRepository.sqlUpdateHitByNo(no);
				httpSession.setAttribute("CURRNO", no);
			}
		}
		// hit가 1이면 DB에 1증가시키고 HIT에 0 넣어서 새로고침해도 1증가 안되게하기
		/*
		 * if (hit == 1) { qaRepository.sqlUpdateHitByNo(no);
		 * httpSession.setAttribute("HIT", 0); }
		 */
		
		QaBoard vo = qaRepository.findByNo(no);
		model.addAttribute("vo", vo);
		
		QaBoard prev = qaRepository.sqlPrevByNo(no);
		QaBoard next = qaRepository.sqlNextByNo(no);
		
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		System.out.println("prev = " + prev);
		System.out.println("next = " + next);
		
		List<QaBoardReply> reply = qrRepository.selectQueryWhereQano(no);
		
		model.addAttribute("reply", reply);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		System.out.println("================================" + user.getUsername());

		model.addAttribute("currentUser", user.getUsername());

		return "board/qa_detail";
	}

	@RequestMapping(value = "/qa_delete", method = RequestMethod.POST)
	public String qadeletePOST(@RequestParam(value = "no") long no) {

		if (qrRepository.selectQueryWhereQano(no).isEmpty()) {
			qaRepository.sqlDeleteByNo(no);
			qaRepository.sqlUpdateRepcntAll();
		} else {
			qrRepository.sqlDeleteByQano(no);
			qaRepository.sqlDeleteByNo(no);
			qaRepository.sqlUpdateRepcntAll();
		}
		 
		return "redirect:/board/qa_list";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String replyPOST(@ModelAttribute QaBoardReply vo, @RequestParam(value = "brdno") long no) {
		qaRepository.sqlUpdateRepcntByNo(no);
		
		System.out.println(vo.toString());

		Optional<QaBoard> board = qaRepository.findById(no);
		vo.setBoard(board.get());

		qrRepository.save(vo);

		return "redirect:/board/qa_detail?no=" + no;
	}
	
	@RequestMapping(value = "/repdelete", method = RequestMethod.POST)
	public String deletereplyPOST(@RequestParam(value="no") long no, @RequestParam(value = "repno") long repno) {
		qrRepository.sqlDeleteByRepno(repno);
		qaRepository.sqlUpdateRepcntByNo(no);
		
		return "redirect:/board/qa_detail?no=" + no;
	}
	
	@RequestMapping(value = "/qa_update")
	public String qaupdateGET(Model model, @RequestParam(value = "no") long no) {
		System.out.println(no);
		// 한개 꺼내기
		// Optional의 value는 값이 있을 수도 있고 null 일 수 도 있다.
		Optional<QaBoard> vo1 = qaRepository.findAllByNo(no);
		QaBoard vo2 = vo1.get();
		model.addAttribute("vo", vo2);
		
		return "board/qa_update";
	}

	@RequestMapping(value = "/qa_update", method = RequestMethod.POST)
	public String qaupdatePOST(@ModelAttribute QaBoard vo, @RequestParam(value = "no") long no,
			@RequestParam(value = "txt") String txt, @RequestParam(value = "page") int page)
			throws UnsupportedEncodingException {
		System.out.println(vo.toString());

		qaRepository.save(vo);
		return "redirect:/board/qa_detail?no=" + no + "&txt=" + URLEncoder.encode(txt, "UTF-8") + "&page=" + page;
	}
}
