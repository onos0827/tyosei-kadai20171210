package jp.co.onoda.kadai.controller;

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

	@RequestMapping("/view/answer/{eventName}")
	public String view(@PathVariable(value="eventName") String eventName, Model model){
			Map<String, Object> result = service.get(eventName);
			model.addAttribute("map", result);
			model.addAttribute("inputForm2", new DataForm2());
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
    	String URL = "http://localhost:8080/view/answer" + ID.toString();
		model.addAttribute("URL", URL);
		return "view/select";
	}

    @PostMapping("/view/createAtd")
   	public String createAtd(@ModelAttribute @Validated DataForm atdresult, Model model){
       	Map<String, Object> result = service.create(atdresult);
   		model.addAttribute("map", result);
   		return "view/answer";

   	}




}
