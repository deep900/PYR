package com.pradheep.data.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.BibleQuiz;
import com.pradheep.dao.model.BibleQuizEng;
import com.pradheep.dao.model.BibleQuizTamil;

public class BibleQuizDataMigration implements InitializingBean {

	@Autowired
	private DAOService daoService;

	private String fileName_eng = "C:\\Software_Development\\PYRWorkspace\\bible_quiz_notes\\master_eng.txt";
	private String fileName_ta = "C:\\Software_Development\\PYRWorkspace\\bible_quiz_notes\\master_ta.txt";

	public BibleQuizDataMigration() {
	}

	private void startMigrationForEnglish() {
		System.out.println("Starting the data migration..");
		File readFile = new File(fileName_eng);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(readFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bReader = new BufferedReader(fileReader);
		String str = "";
		try {
			BibleQuizEng bibleQuizEng = new BibleQuizEng();
			while ((str = bReader.readLine()) != null) {
				if (str.contains("level:")) {
					String level = str.substring(str.indexOf(":") + 1);
					if (level.length() < 3) {
						break;
					}
					bibleQuizEng.setLevel(level);
				}

				if (str.contains("testament:")) {
					String testament = str.substring(str.indexOf(":") + 1);
					if (testament.length() < 3) {
						break;
					}
					bibleQuizEng.setTestament(testament);
				}

				if (str.contains("question:")) {
					String question = str.substring(str.indexOf(":") + 1);
					if (question.length() < 3) {
						break;
					}
					bibleQuizEng.setQuestion(question);
				}
				if (str.contains("choice:")) {
					String choice = str.substring(str.indexOf(":") + 1);
					if (choice.length() < 3) {
						break;
					}
					bibleQuizEng.setChoice(choice);
				}
				if (str.contains("c_answer:")) {
					String c_answer = str.substring(str.indexOf(":") + 1);
					if (c_answer.length() < 3) {
						break;
					}
					bibleQuizEng.setCorrectAnswer(c_answer);
				}
				if (str.contains("reference:")) {
					String reference = str.substring(str.indexOf(":") + 1);
					if (reference.length() < 3) {
						break;
					}
					bibleQuizEng.setBibleReference(reference);
				}
				if (str.equals("#")) {
					saveQuizEntry(bibleQuizEng, "eng");
					bibleQuizEng = new BibleQuizEng();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startMigrationForTamil() {
		System.out.println("Starting the data migration..Tamil");
		File readFile = new File(fileName_ta);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(readFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bReader = new BufferedReader(fileReader);
		String str = "";
		try {
			BibleQuizTamil bibleQuizTa = new BibleQuizTamil();
			while ((str = bReader.readLine()) != null) {
				if (str.contains("level:")) {
					String level = str.substring(str.indexOf(":") + 1);
					if (level.length() < 3) {
						break;
					}
					bibleQuizTa.setLevel(level);
				}

				if (str.contains("testament:")) {
					String testament = str.substring(str.indexOf(":") + 1);
					if (testament.length() < 3) {
						break;
					}
					bibleQuizTa.setTestament(testament);
				}

				if (str.contains("question:")) {
					String question = str.substring(str.indexOf(":") + 1);
					if (question.length() < 3) {
						break;
					}
					bibleQuizTa.setQuestion(question);
				}
				if (str.contains("choice:")) {
					String choice = str.substring(str.indexOf(":") + 1);
					if (choice.length() < 3) {
						break;
					}
					bibleQuizTa.setChoice(choice);
				}
				if (str.contains("c_answer:")) {
					String c_answer = str.substring(str.indexOf(":") + 1);
					if (c_answer.length() < 3) {
						break;
					}
					bibleQuizTa.setCorrectAnswer(c_answer);
				}
				if (str.contains("reference:")) {
					String reference = str.substring(str.indexOf(":") + 1);
					if (reference.length() < 3) {
						break;
					}
					bibleQuizTa.setBibleReference(reference);
				}
				if (str.equals("#")) {
					saveQuizEntry(bibleQuizTa, "ta");
					bibleQuizTa = new BibleQuizTamil();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveQuizEntry(Object quizObj, String language) {
		BibleQuiz bibleQuiz = null;
		if (quizObj instanceof BibleQuiz) {
			bibleQuiz = (BibleQuiz) quizObj;
		}
		if (alreadyExists(bibleQuiz, language)) {
			System.out.println("Record already exists : " + bibleQuiz.getQuestion());
			return;
		}

		if (language.equalsIgnoreCase("ta")) {
			BibleQuizTamil bibleQuizTamil = (BibleQuizTamil) bibleQuiz;
			if (daoService.saveOrUpdateEntity(bibleQuizTamil)) {
				System.out.println("Successfully persisted :" + bibleQuiz.getQuestion());
			} else {
				System.out.println("Unable to persist :" + bibleQuiz.getQuestion());
			}
		} else {
			BibleQuizEng bibleQuizEng = (BibleQuizEng) bibleQuiz;
			if (daoService.saveOrUpdateEntity(bibleQuizEng)) {
				System.out.println("Successfully persisted :" + bibleQuiz.getQuestion());
			} else {
				System.out.println("Unable to persist :" + bibleQuiz.getQuestion());
			}
		}

	}

	private boolean alreadyExists(BibleQuiz bibleQuiz, String language) {
		String question = bibleQuiz.getQuestion();
		Class x = BibleQuizEng.class;
		if (language.equalsIgnoreCase("ta")) {
			x = BibleQuizTamil.class;
		}
		Object obj = null;
		if (question.contains("'")) {
			question = question.substring(0, question.indexOf("'") - 1);
			obj = daoService.getObjectsLike(x, "question", question);
		} else {
			obj = daoService.getObjectsById(x, "question", question);
		}
		if (obj == null) {
			return false;
		} else {
			return true;
		}

	}

	public static void main(String[] args) {

	}

	public void afterPropertiesSet() throws Exception {
		//startMigrationForEnglish();
		//startMigrationForTamil();
	}

}
