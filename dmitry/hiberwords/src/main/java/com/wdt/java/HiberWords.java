package com.wdt.java;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class HiberWords {
	private static Session session;

	public HiberWords() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	private int addWord(String wordVal, String descVal) {
		Transaction trns = null;
		int id = -1;
		try {
			trns = session.beginTransaction();
			GenWord genWord = new GenWord();
			genWord.setWord(wordVal);
			genWord.setDesc(descVal);
			session.save(genWord);
			session.getTransaction().commit();
			id = genWord.getId();
			System.out.println(wordVal + " value inserted. id = " + id);
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.clear();
		}
		return id;
	}

	private void deleteWord(GenWord word) {
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			session.delete(word);
			session.getTransaction().commit();
			System.out.println("id=" + word.getId() + " deleted");
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.clear();
		}
	}

	private void updateWord(GenWord word) {
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			session.update(word);
			trns.commit();
			System.out.println("id=" + word.getId() + " updated");
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.clear();
		}
	}

	private void updateDesc(String word, String newDesc) {
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			String hqlUpdate = "update GenWord set description = :desc where word = :word";
			int updatedEntities = session.createQuery(hqlUpdate)
					.setString("desc", newDesc).setString("word", word)
					.executeUpdate();
			trns.commit();
			System.out.println("updated " + updatedEntities + "row(s)");
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.clear();
		}
	}

	@SuppressWarnings("unchecked")
	private void getDesc(String word) {
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			List<GenWord> words = (List<GenWord>) session
					.createQuery("from GenWord where word = :word")
					.setString("word", word).list();
			for (GenWord iter : words) {
				System.out.println(iter.getWord() + " is " + iter.getDesc());
			}
			trns.commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.clear();
		}
	}

	@SuppressWarnings("unchecked")
	private void printWords() {
		System.out.println("==============table content===========");
		List<GenWord> words = session.createCriteria(GenWord.class)
				.addOrder(Order.asc("word")).list();
		for (GenWord iter : words) {
			System.out.println("id=" + iter.getId() + ": " + iter.getWord()
					+ " is " + iter.getDesc());
		}
	}

	public static void main(String[] args) {
		HiberWords hiberWords = new HiberWords();
		int id;
		
		//add row
		hiberWords.addWord("ololo", "oloshechki");
		hiberWords.addWord("yohoho", "pirates");
		id = hiberWords.addWord("karbofos", "suka slon");
		
		//update rows
		GenWord genWord = new GenWord();
		genWord.setId(id);
		genWord.setWord("elephant");
		genWord.setDesc("suka slon");
		hiberWords.updateWord(genWord);
		
		//update field
		hiberWords.updateDesc("elephant", "suka slon also");
		
		//delete row
		hiberWords.addWord("karbofos", "suka slon");
		id = hiberWords.addWord("karbofos", "suka slon");
		genWord.setId(id);
		hiberWords.deleteWord(genWord);
		
		//get field with conditions
		hiberWords.getDesc("elephant");
		
		//printout table content
		hiberWords.printWords();

		session.flush();
		session.close();
	}
}
