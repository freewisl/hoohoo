package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Member;
import com.example.repository.MemberRepository;
import com.example.security.MyUser;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	MemberRepository mRepository;

	@Autowired
	BCryptPasswordEncoder bcpe;

	@RequestMapping(value = "/login")
	public String loginGET() {
		return "member/login";
	}

	@RequestMapping(value = "/join")
	public String joinGET() {
		return "member/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(Model model, @ModelAttribute Member vo, RedirectAttributes rttr) throws IOException {

		// 아이디 중복체크 db에서 id가 일치하는 데이터가 있으면 1반환
		if (mRepository.sqlEqualById(vo.getId()) == 1) {

			model.addAttribute("message", "아이디 중복입니다");
			model.addAttribute("location", "/member/join");
			return "alert";

			// PrintWriter out = response.getWriter();
			// out.println("<script>alert('아이디 중복입니다.'); location.href='./join';</script>");
			// out.flush(); 리턴 포함
			// return "redirect:/member/join"; 리턴 2번해서 오류가 났음
		}

		String changePw = bcpe.encode(vo.getPw());
		vo.setPw(changePw);

		mRepository.save(vo);
		rttr.addFlashAttribute("id", vo.getId());
		System.out.println(mRepository.sqlEqualById(vo.getId()));

		return "redirect:/member/join_result";
	}

	@RequestMapping(value = "/join_result")
	public String joinresultGET(Model model, @ModelAttribute("id") String id) {
		Member vo = mRepository.findById(id);
		model.addAttribute("vo", vo);
		return "member/join_result";
	}

	@RequestMapping(value = "/mypage")
	public String mypageGET(Model model, Authentication auth) {
		MyUser user = (MyUser) auth.getPrincipal();
		String id = user.getId();
		String pw = user.getPw();

		System.out.println("현재아이디 = " + id + "//////" + "db비밀번호 = " + pw);

		Member vo = mRepository.findById(id);

		model.addAttribute("vo", vo);

		return "member/mypage";
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.POST)
	public String mypagePOST(Model model, Authentication auth, @ModelAttribute("id") String id,
			@ModelAttribute("currpw") String currpw, @ModelAttribute("pw") String newpw, @ModelAttribute Member vo) {
		MyUser user = (MyUser) auth.getPrincipal();
		String dbPw = user.getPw();

		System.out.println(bcpe.matches(currpw, dbPw));

		// 새 비밀번호 입력안했을때
		if (newpw.equals(null)) {
			if (bcpe.matches(currpw, dbPw)) { // 현재 비밀번호와 db에 등록되어있는 비밀번호가 같을때
				System.out.println("=======비번일치");
				model.addAttribute("message", "회원정보가 수정되었습니다.");
				model.addAttribute("location", "/");

				vo.setPw(dbPw);

				mRepository.save(vo);

				return "alert";
			} else { // 현재 비밀번호와 db에 등록되어있는 비밀번호가 다를때
				System.out.println("=======비번불일치");

				model.addAttribute("message", "현재 비밀번호를 확인해주세요.");
				model.addAttribute("location", "/member/mypage");

				return "alert";
			}
		} else { // 새 비밀번호 입력했을때
			if (bcpe.matches(currpw, dbPw)) {
				System.out.println("=======비번일치");
				model.addAttribute("message", "회원정보가 수정되었습니다.");
				model.addAttribute("location", "/");

				String changePw = bcpe.encode(vo.getPw());
				vo.setPw(changePw);

				mRepository.save(vo);

				return "alert";
			} else {
				System.out.println("=======비번불일치");

				model.addAttribute("message", "현재 비밀번호를 확인해주세요.");
				model.addAttribute("location", "/member/mypage");

				return "alert";
			}
		}
		// return "redirect:/member/mypage";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(Model model, Authentication auth, @ModelAttribute("currpw") String currpw,
			HttpSession httpSession, HttpServletRequest request) {
		MyUser user = (MyUser) auth.getPrincipal();
		String id = user.getId();
		String dbPw = user.getPw();
		System.out.println("현재 접속 ID = " + id);

		if (id != null) {
			if (bcpe.matches(currpw, dbPw)) { // 현재 비밀번호와 db에 등록되어있는 비밀번호가 같을때
				System.out.println("=======비번일치");
				mRepository.sqlDeleteById(id); // db에서 아이디삭제
				// httpSession.invalidate(); // 로그인 세션끊기 ajax로 2번끊음

				model.addAttribute("message", "그동안 이용해 주셔서 감사합니다.");
				model.addAttribute("location", "/");

				return "member/logout_alert";

			} else { // 현재 비밀번호와 db에 등록되어있는 비밀번호가 다를때
				System.out.println("=======비번불일치");

				model.addAttribute("message", "현재 비밀번호를 확인해주세요.");
				model.addAttribute("location", "/member/mypage");

				return "alert";
			}

		}
		return "redirect:/";
	}
}
