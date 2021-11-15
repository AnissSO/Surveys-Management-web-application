package com.stage.WebApp21.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.stage.WebApp21.model.ArrayListQuestion;
import com.stage.WebApp21.model.PageQuestionnaire;
import com.stage.WebApp21.model.Question;
import com.stage.WebApp21.model.QuestionOption;
import com.stage.WebApp21.model.QuestionOptionUser;
import com.stage.WebApp21.model.QuestionUser;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.QuestionnaireRempli;
import com.stage.WebApp21.model.SubmissionUser;
import com.stage.WebApp21.model.Utilisateur;
import com.stage.WebApp21.service.PageQuestionnaireService;
import com.stage.WebApp21.service.QuestionService;
import com.stage.WebApp21.service.QuestionnaireRempliService;
import com.stage.WebApp21.service.QuestionnaireService;
import com.stage.WebApp21.service.UtilisateurService;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;

@Data
@Controller
public class PageQuestionnaireController {
	
	@Autowired
	private PageQuestionnaireService questionnairePageService;
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private QuestionnaireRempliService questionnaireRempliService;
	
	

	
	@GetMapping("/seeSubmissionsUser/{id}")
	public String seeSubmissionsUser(@PathVariable("id") final BigInteger id, Model model) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		model.addAttribute("questionnaire", q);
		
		Iterable<QuestionnaireRempli> listQuestionnaireRempli = questionnairePageService.getQuestionnairesRemplis(id);
		
		model.addAttribute("listQuestionnaireRempli", listQuestionnaireRempli);
		
		return "listQuestionnaireRempliPageUser";
	}
	
	
	@GetMapping("/seeSubmissions/{id}")
	public String seeSubmissions(@PathVariable("id") final BigInteger id, Model model) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		model.addAttribute("questionnaire", q);
		
		Iterable<QuestionnaireRempli> listQuestionnaireRempli = questionnairePageService.getQuestionnairesRemplis(id);
		
		model.addAttribute("listQuestionnaireRempli", listQuestionnaireRempli);
		
		return "listQuestionnaireRempliPage";
	}
	
	@GetMapping("/showQuestionnaire/{id}")
	public String showQuestionnaire(@PathVariable("id") final BigInteger id, Model model) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		model.addAttribute("questionnaire", q);
		Utilisateur u = utilisateurService.getUser(q.getId_utilisateur());
		model.addAttribute("utilisateur", u);
		
		Iterable<PageQuestionnaire> listQuestionnairePage = questionnairePageService.getQuestionnairePages(id);
		//envoyer liste des questions en attribut du model.addAttribute(listQuestions)
		//à partir de chaque page extraire les questions 
		Iterable<Question> listQuestions = questionService.getQuestions();
		Iterable<QuestionOption> listQuestionOptions = questionService.getQuestionOptions();
		
		model.addAttribute("questionnairePages", listQuestionnairePage);
		model.addAttribute("toutesLesQuestions", listQuestions);
		model.addAttribute("toutesLesOptionsQuestions", listQuestionOptions);
		
		return "formShowQuestionnaire";
	}
	
	@GetMapping("/showQuestionnaireUser/{id}")
	public String showQuestionnaireUser(@PathVariable("id") final BigInteger id, Model model) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		model.addAttribute("questionnaire", q);
		
		model.addAttribute("idUser", q.getId_utilisateur());
		Iterable<PageQuestionnaire> listQuestionnairePage = questionnairePageService.getQuestionnairePages(id);
		//envoyer liste des questions en attribut du model.addAttribute(listQuestions)
		//à partir de chaque page extraire les questions 
		Iterable<Question> listQuestions = questionService.getQuestions();
		Iterable<QuestionOption> listQuestionOptions = questionService.getQuestionOptions();
		
		model.addAttribute("questionnairePages", listQuestionnairePage);
		model.addAttribute("toutesLesQuestions", listQuestions);
		model.addAttribute("toutesLesOptionsQuestions", listQuestionOptions);
		
		return "formShowQuestionnaireUser";
	}
	
	@GetMapping("/showQuestionnaireRempli/{id}")
	public String showQuestionnaireRempli(@PathVariable("id") final BigInteger id, Model model) {
		
		BigInteger idQuestionnaireRemp = id;
		QuestionnaireRempli questionnaireRempli = questionnaireRempliService.getQuestionnaireRempli(id);
		
		ArrayListQuestion arrayListQuestion = new ArrayListQuestion();
		System.out.println("TEST !!!!!!!!!!!");
		Iterable<QuestionUser> listQU = questionnaireService.getQuestionUsers();
		for(QuestionUser qu : listQU) {
			System.out.println("This " + qu);
		}
		System.out.println("FIN TEST !!!!!!!!!!!");
		Iterable<PageQuestionnaire> listQuestionnairePage = questionnairePageService.getQuestionnairePages(questionnaireRempli.getId_questionnaire_definition());
		arrayListQuestion.setListPageQuestionnaire(new ArrayList<PageQuestionnaire>());
		arrayListQuestion.setListQuestions(new ArrayList<Question>());
		arrayListQuestion.setListQuestionOptions(new ArrayList<QuestionOption>());
		
		Questionnaire q = questionnaireService.getQuestionnaire(questionnaireRempli.getId_questionnaire_definition());
		model.addAttribute("questionnaire", q);
		
		for(PageQuestionnaire pq : listQuestionnairePage) {
			arrayListQuestion.getListPageQuestionnaire().add(pq);
			Iterable<Question> listQuest = questionService.getQuestionsDeLaPage(pq.getId_questionnaire_definition_page());
			for(Question ques : listQuest) {
				arrayListQuestion.getListQuestions().add(ques);
				Iterable<QuestionOption> listOptions = questionService.getOptionsQuestion(ques.getId_question());
				if(listOptions != null) {
					for(QuestionOption qo : listOptions) {
						arrayListQuestion.getListQuestionOptions().add(qo);
					}
				}
			}
		}
		System.out.println("DEBUT MACHINE");
		for(PageQuestionnaire pq : arrayListQuestion.getListPageQuestionnaire()) {
			System.out.println(pq);
		}
		for(Question pq : arrayListQuestion.getListQuestions()) {
			System.out.println(pq);
		}
		for(QuestionOption pq : arrayListQuestion.getListQuestionOptions()) {
			System.out.println(pq);
		}
		System.out.println("FIN MACHINE");
		for(Question ques : arrayListQuestion.getListQuestions()) {
			for(QuestionUser quest : questionnaireService.getQuestionUsers()) {
					
					if((quest.getId_question_origni().equals(ques.getId_question())) /*&& (quest.getId_Survey_filled()== idQuestionnaireRemp)*/) {
						if(quest.getId_Survey_filled().equals(idQuestionnaireRemp)) {
						System.out.println("ULTIMATE C'est l'indice" + quest.getIndice());
						ques.setIndice(String.valueOf(quest.getIndice()));
						}
					}
				
				
			}
		}
		
		for(QuestionOption qo : arrayListQuestion.getListQuestionOptions()) {
			for(QuestionOptionUser qopt : questionnaireService.getQuestionOptionsUsers()) {
				if(qo.getId_option().equals(qopt.getId_question_Opt()) /*&& qopt.getId_Quest_Rempli() == id*/) {
					if(qopt.getId_Quest_Rempli().equals(idQuestionnaireRemp)) {
					System.out.println("C'est l'option" + qopt.getId_question_Opt());
					qo.setOption_valeur(String.valueOf(qopt.getOption_valeur()));
					}
				}
			}
		}
		
		model.addAttribute("arrayListQuestion", arrayListQuestion);
		
		System.out.println("ULTIMATEEEEEEEEEE ArrayList !!!!!!!!!!!");
		for(PageQuestionnaire pq : arrayListQuestion.getListPageQuestionnaire()) {
			System.out.println(pq);
		}
		for(Question pq : arrayListQuestion.getListQuestions()) {
			System.out.println(pq);
		}
		for(QuestionOption pq : arrayListQuestion.getListQuestionOptions()) {
			System.out.println(pq);
		}
		return "showSurveyFilled";
	}
	
	
	@GetMapping("/fillQuestionnaireUser/{id}")
	public String fillQuestionnaireUser(@PathVariable("id") final BigInteger id, Model model) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		model.addAttribute("questionnaire", q);
		
		ArrayListQuestion arrayListQuestion = new ArrayListQuestion();
		
		Iterable<PageQuestionnaire> listQuestionnairePage = questionnairePageService.getQuestionnairePages(id);
		arrayListQuestion.setListPageQuestionnaire(new ArrayList<PageQuestionnaire>());
		arrayListQuestion.setListQuestions(new ArrayList<Question>());
		arrayListQuestion.setListQuestionOptions(new ArrayList<QuestionOption>());
		
		for(PageQuestionnaire pq : listQuestionnairePage) {
			arrayListQuestion.getListPageQuestionnaire().add(pq);
			Iterable<Question> listQuest = questionService.getQuestionsDeLaPage(pq.getId_questionnaire_definition_page());
			for(Question ques : listQuest) {
				arrayListQuestion.getListQuestions().add(ques);
				Iterable<QuestionOption> listOptions = questionService.getOptionsQuestion(ques.getId_question());
				if(listOptions != null) {
					for(QuestionOption qo : listOptions) {
						arrayListQuestion.getListQuestionOptions().add(qo);
					}
				}
			}
		}
		for(PageQuestionnaire pq : arrayListQuestion.getListPageQuestionnaire()) {
			System.out.println(pq);
		}
		for(Question pq : arrayListQuestion.getListQuestions()) {
			System.out.println(pq);
		}
		for(QuestionOption pq : arrayListQuestion.getListQuestionOptions()) {
			System.out.println(pq);
		}
		//System.out.println(arrayListQuestion);
		model.addAttribute("arrayListQuestion", arrayListQuestion);
		
		return "formFillQuestionnaireUser";
	}
	
	
	@PostMapping("/fillSurvey")
	public String fillSurvey(@ModelAttribute ArrayListQuestion arrayListQuestion, Model model) throws SQLException {
		BigInteger idQues = null;
		if(arrayListQuestion != null) {
			if(arrayListQuestion.getListPageQuestionnaire() != null) {
				for(PageQuestionnaire pq : arrayListQuestion.getListPageQuestionnaire()) {
					idQues = pq.getId_questionnaire_definition();
					System.out.println("************");
					System.out.println(pq);
					System.out.println("************");
				}
			}
			if(arrayListQuestion.getListQuestions() != null) {
				for(Question pq : arrayListQuestion.getListQuestions()) {
					System.out.println("************");
					System.out.println(pq);
					System.out.println("************");
				}
			}
			if(arrayListQuestion.getListQuestionOptions() != null) {
				for(QuestionOption pq : arrayListQuestion.getListQuestionOptions()) {
					System.out.println("************");
					System.out.println(pq);
					System.out.println("************");
				}
			}
		}else {
			System.out.println("This is null");
		}
		
		Questionnaire q = questionnaireService.getQuestionnaire(idQues);
		model.addAttribute("questionnaire", q);
		
		ArrayListQuestion arrayListQuestionNew = new ArrayListQuestion();
		
		Iterable<PageQuestionnaire> listQuestionnairePage = questionnairePageService.getQuestionnairePages(idQues);
		arrayListQuestionNew.setListPageQuestionnaire(new ArrayList<PageQuestionnaire>());
		arrayListQuestionNew.setListQuestions(new ArrayList<Question>());
		arrayListQuestionNew.setListQuestionOptions(new ArrayList<QuestionOption>());
		
		for(PageQuestionnaire pq : listQuestionnairePage) {
			arrayListQuestionNew.getListPageQuestionnaire().add(pq);
			Iterable<Question> listQuest = questionService.getQuestionsDeLaPage(pq.getId_questionnaire_definition_page());
			for(Question ques : listQuest) {
				arrayListQuestionNew.getListQuestions().add(ques);
				Iterable<QuestionOption> listOptions = questionService.getOptionsQuestion(ques.getId_question());
				if(listOptions != null) {
					for(QuestionOption qo : listOptions) {
						arrayListQuestionNew.getListQuestionOptions().add(qo);
					}
				}
			}
		}
		
		for(Question ques : arrayListQuestionNew.getListQuestions()) {
			for(Question quest : arrayListQuestion.getListQuestions()) {
				if(ques.getId_question().equals(quest.getId_question())) {
					System.out.println("C'est l'indice" + quest.getIndice());
					ques.setIndice(String.valueOf(quest.getIndice()));
				}
			}
		}
		
		for(QuestionOption qo : arrayListQuestionNew.getListQuestionOptions()) {
			for(QuestionOption qopt : arrayListQuestion.getListQuestionOptions()) {
				if(qo.getId_option().equals(qopt.getId_option())) {
					System.out.println("C'est l'option" + qopt.getId_option());
					qo.setOption_valeur(String.valueOf(qopt.getOption_valeur()));
				}
			}
		}
		System.out.println("NEWWW ArrayList !!!!!!!!!!!");
		for(PageQuestionnaire pq : arrayListQuestionNew.getListPageQuestionnaire()) {
			System.out.println(pq);
		}
		for(Question pq : arrayListQuestionNew.getListQuestions()) {
			System.out.println(pq);
		}
		for(QuestionOption pq : arrayListQuestionNew.getListQuestionOptions()) {
			System.out.println(pq);
		}
			
		model.addAttribute("arrayListQuestion", arrayListQuestionNew);
		
		Random r = new Random();
		int low = 10;
		int high = 100;
		int result = r.nextInt(high-low) + low;
		result = result + q.getId().intValue();
		System.out.println("AND STIIIIIIIIIIIIIIILLLLLLLL" + BigInteger.valueOf(result));
		
		QuestionnaireRempli questionnaireRempli = new QuestionnaireRempli();
		questionnaireRempli.setId_questionnaire(BigInteger.valueOf(result));
		questionnaireRempli.setId_questionnaire_definition(q.getId());
		questionnaireRempli.setDate_creation(q.getDate_creation());
		questionnaireRempli.setDate_soumission(LocalDateTime.now());
		questionnaireRempli.setNom_questionnaire(q.getNom());
		
		questionnaireRempliService.saveQuestionnaireRempli(questionnaireRempli);
		for(Question ques : arrayListQuestionNew.getListQuestions()) {
			QuestionUser qUser = new QuestionUser();
			qUser.setId_question_origni(ques.getId_question());
			qUser.setId_Survey_filled(BigInteger.valueOf(result));
			qUser.setQuestion_ordre(ques.getQuestion_ordre());
			qUser.setType(ques.getType());
			qUser.setQuestion_texte(ques.getQuestion_texte());
			qUser.setIndice(ques.getIndice());
			qUser.setId_questionnaire_definition_page(ques.getId_questionnaire_definition_page());
			questionService.saveQuestionUser(qUser);
		}
		
		for(QuestionOption qo : arrayListQuestionNew.getListQuestionOptions()) {
			QuestionOptionUser qoUser = new QuestionOptionUser();
			qoUser.setId_question_Opt(qo.getId_option());
			qoUser.setId_Quest_Rempli(BigInteger.valueOf(result));
			qoUser.setId_question(qo.getId_question());
			qoUser.setOption_texte(qo.getOption_texte());
			qoUser.setOption_valeur(qo.getOption_valeur());
			questionService.saveQuestionOptionUser(qoUser);
		}
		
		
		/*
			 WebDriverManager.chromedriver().setup();
		       WebDriver driver = new ChromeDriver();
		       //driver.getPageSource();
		       driver.get("http://localhost:9000/fillQuestionnaireUser/" + q.getId());
		       driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		       Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE_CHROME,500,true).withName("FullPageScreenshot" + q.getId() + result).save();
		       try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       driver.quit();
		//questionnaireRempliService.saveQuestionnaireRempli(questionnaireRempli);
		
        Connection connection = null;
        PreparedStatement statement = null;
        FileInputStream inputStream = null;

        try {
        File image = new File("E:/ENSIAS2020/Cours//2A//stage 2021//Code Source//WebApp21//screenshots//FullPageScreenshot" + q.getId() + result + ".png");
        inputStream = new FileInputStream(image);

		 /*
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionnaire_schema" + "?useUnicode\\=true&useJDBCCompliantTimezoneShift\\=true&useLegacyDatetimeCode\\=false&serverTimezone\\=UTC", "root", "omarsouiyah1958");
		
			statement = connection.prepareStatement("UPDATE questionnaire set image=? where nomImage=?");
		
			statement.setString(2, "FullPageScreenshot" + q.getId() + result);
		
			statement.setBinaryStream(1, (InputStream) inputStream, (int)(image.length()));
		

			statement.executeUpdate();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: - " + e);
        } finally {
        }
		 */
		//questionnaireRempli.setImage("FullPageScreenshot" + q.getId() + result + ".png");
		
		return "showQuestionnaireFilled";
	}
	
	
	@GetMapping("/addPageQuestionnaire/{id}")
	public String addPageQuestionnaire(@PathVariable("id") final BigInteger id, Model model) {
		PageQuestionnaire page = new PageQuestionnaire();
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		System.out.println("This is ID of questionnaire" + q);
		model.addAttribute("questionnaire", q);
		page.setId_questionnaire_definition(id);
		System.out.println(page);
		
		BigInteger idQuestionnaire = id;
		
		model.addAttribute("pageQuestionnaire", page);
		model.addAttribute("idQuestionnaire", idQuestionnaire);
		return "formNewPageQuestionnaire";
	}
	
	@GetMapping("/addPageQuestionnaireUser/{id}")
	public String addPageQuestionnaireUser(@PathVariable("id") final BigInteger id, Model model) {
		PageQuestionnaire page = new PageQuestionnaire();
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		System.out.println("This is ID of questionnaire" + q);
		model.addAttribute("questionnaire", q);
		page.setId_questionnaire_definition(id);
		System.out.println(page);
		
		BigInteger idQuestionnaire = id;
		
		model.addAttribute("pageQuestionnaire", page);
		model.addAttribute("idQuestionnaire", idQuestionnaire);
		return "formNewPageQuestionnaireUser";
	}

	@PostMapping("/savePageQuestionnaire")
	public ModelAndView savePageQuestionnaire(@ModelAttribute PageQuestionnaire pageQuestionnaire, @ModelAttribute Questionnaire questionnaire) {
		System.out.println(pageQuestionnaire);
		PageQuestionnaire p = pageQuestionnaire;
		Questionnaire q = questionnaire;
		System.out.println("voici le questionnaire " + q);
		System.out.println("hhhhhhhhhhhhhh");
		//p.setId_questionnaire_definition(BigInteger.valueOf(22));
		int nbrePages = 0;
		Iterable<PageQuestionnaire> pagesDuQuestionnaire = questionnairePageService.getQuestionnairePages(p.getId_questionnaire_definition());
		for(PageQuestionnaire pq : pagesDuQuestionnaire) {
			nbrePages++;
		}
		p.setPage_ordre((short)(nbrePages + 1));
		System.out.println(p);
		questionnairePageService.savePageQuestionnaire(p);
		
		System.out.println(p);
		return new ModelAndView("redirect:/showQuestionnaire/" + p.getId_questionnaire_definition()); 
	}
	
	@PostMapping("/savePageQuestionnaireUser")
	public ModelAndView savePageQuestionnaireUser(@ModelAttribute PageQuestionnaire pageQuestionnaire, @ModelAttribute Questionnaire questionnaire) {
		System.out.println(pageQuestionnaire);
		PageQuestionnaire p = pageQuestionnaire;
		Questionnaire q = questionnaire;
		System.out.println("voici le questionnaire " + q);
		System.out.println("hhhhhhhhhhhhhh");
		//p.setId_questionnaire_definition(BigInteger.valueOf(22));
		int nbrePages = 0;
		Iterable<PageQuestionnaire> pagesDuQuestionnaire = questionnairePageService.getQuestionnairePages(p.getId_questionnaire_definition());
		for(PageQuestionnaire pq : pagesDuQuestionnaire) {
			nbrePages++;
		}
		
		p.setPage_ordre((short)(nbrePages + 1));
		System.out.println(p);
		questionnairePageService.savePageQuestionnaire(p);
		
		System.out.println(p);
		return new ModelAndView("redirect:/showQuestionnaireUser/" + p.getId_questionnaire_definition()); 
	}
	
	@GetMapping("/updatePage/{id}")
	public String updatePage(@PathVariable("id") final BigInteger id, Model model) {
		PageQuestionnaire pq = questionnairePageService.getPageQuestionnaire(id);
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(pq.getId_questionnaire_definition());
		
		model.addAttribute("pageQuestionnaire", pq);
		model.addAttribute("questionnaire", questionnaire);
		
		model.addAttribute("pageId", pq.getId_questionnaire_definition_page());
		
		model.addAttribute("questId", pq.getId_questionnaire_definition());
		
		
		return "formUpdatePage";
	}
	
	@GetMapping("/updatePageUser/{id}")
	public String updatePageUser(@PathVariable("id") final BigInteger id, Model model) {
		PageQuestionnaire pq = questionnairePageService.getPageQuestionnaire(id);
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(pq.getId_questionnaire_definition());
		
		model.addAttribute("pageQuestionnaire", pq);
		model.addAttribute("questionnaire", questionnaire);
		
		model.addAttribute("pageId", pq.getId_questionnaire_definition_page());
		
		model.addAttribute("questId", pq.getId_questionnaire_definition());
		
		
		return "formUpdatePageUser";
	}
	
	@PostMapping("/updatePageQuestion")
	public ModelAndView updatePageQuestion(@ModelAttribute PageQuestionnaire pageQuestionnaire) {
		System.out.println(pageQuestionnaire);
		questionnairePageService.savePageQuestionnaire(pageQuestionnaire);
		
		
		return new ModelAndView("redirect:/showQuestionnaire/" + pageQuestionnaire.getId_questionnaire_definition());
	}
	
	@PostMapping("/updatePageQuestionUser")
	public ModelAndView updatePageQuestionUser(@ModelAttribute PageQuestionnaire pageQuestionnaire) {
		System.out.println(pageQuestionnaire);
		questionnairePageService.savePageQuestionnaire(pageQuestionnaire);
		
		
		return new ModelAndView("redirect:/showQuestionnaireUser/" + pageQuestionnaire.getId_questionnaire_definition());
	}
	
	@GetMapping("/deletePage/{id}")
	public ModelAndView deletePage(@PathVariable("id") final BigInteger id) {
		PageQuestionnaire pq = questionnairePageService.getPageQuestionnaire(id);
		Iterable<Question> listQuestionsDeLaPage = questionService.getQuestionsDeLaPage(id);
		
		if(listQuestionsDeLaPage != null) {
			for(Question s: listQuestionsDeLaPage){
				Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(s.getId_question());
				if(listOptionsQuestion != null) {
					for(QuestionOption e: listOptionsQuestion){
						questionService.deleteQuestionOption(e.getId_option());
					}
				}
				questionService.deleteQuestion(s.getId_question());
			}
		}
		questionnairePageService.deletePage(id);
		
		return new ModelAndView("redirect:/showQuestionnaire/"+ pq.getId_questionnaire_definition());	
	}
	
	@GetMapping("/deletePageUser/{id}")
	public ModelAndView deletePageUser(@PathVariable("id") final BigInteger id) {
		PageQuestionnaire pq = questionnairePageService.getPageQuestionnaire(id);
		Iterable<Question> listQuestionsDeLaPage = questionService.getQuestionsDeLaPage(id);
		
		if(listQuestionsDeLaPage != null) {
			for(Question s: listQuestionsDeLaPage){
				Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(s.getId_question());
				if(listOptionsQuestion != null) {
					for(QuestionOption e: listOptionsQuestion){
						questionService.deleteQuestionOption(e.getId_option());
					}
				}
				questionService.deleteQuestion(s.getId_question());
			}
		}
		questionnairePageService.deletePage(id);
		
		return new ModelAndView("redirect:/showQuestionnaireUser/"+ pq.getId_questionnaire_definition());	
	}
}
