package jp.co.onoda.kadai.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.onoda.kadai.form.DataForm;
import jp.co.onoda.kadai.form.DataForm2;
import jp.co.onoda.kadai.service.DataService;

@Controller
public class DataController {

	@Autowired
	DataService service;

	//@RequestMapping("/")
	//public String index(){
		//return "index";
	//}

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("inputForm", new DataForm());
		return "index";
	}

	@RequestMapping("/view/answer/[{eventID}]")
	public String view(@PathVariable(value="eventID") String eventID, Model model){
			Map<String, Object> result = service.get(eventID);
			List<Map<String, Object>> resultdate = service.getdate(eventID);
			model.addAttribute("map", result);
			model.addAttribute("date", resultdate);
			model.addAttribute("inputForm2", new DataForm2());
			model.addAttribute("eventID", eventID);
		return "view/answer";
	}

	@RequestMapping("/view/answerRes")
	public String list(Model model){
		model.addAttribute("resultForm", new DataForm());
		return "view/answerRes";
	}

    @PostMapping("/view/create")
	public String create(@ModelAttribute @Validated DataForm eventdata, Model model){
    	Map<String, Object> ID = service.create(eventdata);
    	Collection<Object> IDD = ID.values();
    	String URL = "http://localhost:8080/view/answer/" + IDD.toString();
		model.addAttribute("URL", URL);
		return "view/select";
	}

    @PostMapping("/view/createAnswer")
   	public String createAnswer(@ModelAttribute @Validated DataForm2 answer, DataForm eventdata, Model model){
       	Map<String, Object> result = service.createAnswer(answer);
   		model.addAttribute("map", result);
   		return "view/answer";

   	}




}
