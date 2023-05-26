package com.multicampus.kb03.weddingBuddy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.multicampus.kb03.weddingBuddy.dto.Message;
import com.multicampus.kb03.weddingBuddy.dto.User;
import com.multicampus.kb03.weddingBuddy.service.ChatService;
import com.multicampus.kb03.weddingBuddy.service.UserService;

@Controller
public class MessageController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private ChatService service;
	@Autowired
	private UserService userService;

	/*
	 * //�޼�����
	 * 
	 * @RequestMapping("/chat/{to_id}") public ModelAndView messageBox(@PathVariable
	 * String to_id) throws Exception {
	 * 
	 * ModelAndView mav = new ModelAndView();
	 * 
	 * List<Message> messagebox = service.messagebox(to_id);
	 * 
	 * mav.addObject("box", messagebox); mav.setViewName("talker_list");
	 * 
	 * System.out.println("controller"); return mav; }
	 */

	// ���δ� �޽��� �ְ�����
	@RequestMapping("/chat/{to_id}/{from_id}")
	public ModelAndView messageList(@PathVariable int to_id, @PathVariable int from_id, Message msg, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();

		msg.setTo_id(to_id);
		msg.setFrom_id(from_id);

		List<Message> chatlist = service.chatList(msg);

		logger.info("chatlist ũ�� " + chatlist.size());
		logger.info("chatting " + chatlist);
		
		HttpSession session = request.getSession();
		if(chatlist.size() != 0) {
			int chatting_id = chatlist.get(0).getChatting_id();
			session.setAttribute("chatting_id", chatting_id);
		}
		mav.setViewName("chat");
		mav.addObject("list", chatlist);
		mav.addObject("to_id", to_id);
		mav.addObject("from_id", from_id);

		return mav;
	}

	// �޽��� ������
	@RequestMapping(value = "/chat/send", method = RequestMethod.POST)
	public ModelAndView messagesend(@ModelAttribute Message m, HttpServletRequest request,  HttpSession session) throws Exception {

		//logger.info("To_id" + m.getTo_id());
		logger.info("From_id" + m.getFrom_id());
		logger.info("Message content" + m.getChat_content());
		logger.info("chatting_id" + m.getChatting_id());
		logger.info("Message" + m);

		
		String account_id = UserSession.getLoginUserId(request.getSession());
		User returnVo = userService.selectOne(account_id);

		int to_id = returnVo.getUser_id();
		int chatting_id = (int) session.getAttribute("chatting_id");
		m.setTo_id(to_id);
		m.setChatting_id(chatting_id);
//		m.setTo_id(m.getTo_id)
		service.messagesend(m);

		int from_id = m.getFrom_id();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/chat/" + to_id + "/" + from_id);

		return mav;
	}

	// rest �̿��Ͽ� �޼��� �ޱ�
	@ResponseBody
	@GetMapping(value = "/chat/{to_id}/{from_id}/list.json", produces = "application/json")
	public List<Message> getMessage(@PathVariable int to_id, @PathVariable int from_id, Message m)
			throws Exception {

		//logger.info("������ �Դ�");
		m.setTo_id(to_id);
		m.setFrom_id(from_id);

		List<Message> messagelist = service.chatList(m);

		return messagelist;
	}

}