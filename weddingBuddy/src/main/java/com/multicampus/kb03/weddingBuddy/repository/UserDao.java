package com.multicampus.kb03.weddingBuddy.repository;

import java.util.List;

import com.multicampus.kb03.weddingBuddy.dto.Planner;
import com.multicampus.kb03.weddingBuddy.dto.User;

public interface UserDao {
	// insert 회원가입(추가/삽입)
	public int insert(User user) throws Exception;
	/*
	 * // update 수정 public int update(User user) throws Exception;
	 * 
	 * // delete 삭제 public void delete(int user_id) throws Exception;
	 */

	/*
	 * // count public int count() throws Exception;
	 * 
	 * // selectOne 회원찾기 public User selectOne(int user_id) throws Exception;
	 * 
	 * // selectAll 회원전체보기 public List<User> selectAll() throws Exception;
	 */

	// 로그인
	//public User readUserWithIDPW(User vo) throws Exception;

	public User readUserWithIDPW(String account_id, String password) throws Exception;
	 // selectOne 회원찾기 
	public User selectOne(String user_id) throws Exception;
	// 누구랑 채팅했는지
	public List<Planner> chattingWithSomeone(int user_id) throws Exception;

}
