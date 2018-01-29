package jp.co.onoda.kadai.controller;

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

	@RequestMapping("/view/answer/{eventName}")
	public String view(@PathVariable(value="eventName") String eventName, Model model){
			Map<String, Object> result = service.get(eventName);
			model.addAttribute("map", result);
			model.addAttribute("inputForm2", new DataForm2());
		return "view/answer";
	}

	@RequestMapping("/view/list")
	public String list(Model model){
		List<Map<String, Object>> result = service.list();
		model.addAttribute("list", result);
		return "view/list";
	}

    @PostMapping("/view/create")
	public String create(@ModelAttribute @Validated DataForm eventdata, Model model){
    	Map<String, Object> result = service.create(eventdata);
    	//Map<String, Object> date = service.createdate(eventdata);
		model.addAttribute("map", result);
		//model.addAttribute("map", date);
		return "view/select";
	}

    @PostMapping("/view/createAtd")
   	public String createAtd(@ModelAttribute @Validated DataForm atdresult, Model model){
       	Map<String, Object> result = service.create(atdresult);
   		model.addAttribute("map", result);
   		return "view/answer";

   	}




}
