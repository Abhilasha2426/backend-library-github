package com.capgemini.librarymanagementsystem.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public Issue requestBook(BookInfo book, int userId) {
		
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String viewDetails = "from BookIfo where bookId =: bookId";
		Query query = manager.createQuery(viewDetails);
		query.setParameter("bookId", book.getBookId());
		BookInfo books = (BookInfo) query.getSingleResult();
		
		Issue reg = new Issue();
		reg.setBookId(books.getBookId());
		
		Date date = new Date();
		reg.setIssueDate(date);
		
		reg.setUserId(userId);
		
		manager.persist(reg);
		transaction.commit();
		
		return reg;
	}
}
