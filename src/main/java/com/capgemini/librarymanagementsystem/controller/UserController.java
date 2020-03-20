package com.capgemini.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;
import com.capgemini.librarymanagementsystem.response.LibraryManagementSystemResponse;
import com.capgemini.librarymanagementsystem.service.UserService;

@RestController
//@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/requestBook")
	public Issue requestBook(@RequestBody BookInfo book, int userId) {

		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		Issue req = service.requestBook(book, userId);

		if (req != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return req;
	}// end of requestBook

}
