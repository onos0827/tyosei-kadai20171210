package jp.co.onoda.kadai.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
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
		    model.addAttribute("inputForm2", new DataForm2());
			Map<String, Object> result = service.get(eventID);
			List<Map<String, Object>> resultdate = service.getdate(eventID);
			Map<String, String> RadioMap = new LinkedHashMap<String, String>();

			for(int i=0; i<resultdate.size(); i++) {
			String str =resultdate.get(i).get("SET_DATE").toString();
			RadioMap.put(str, "〇");
			RadioMap.put(str, "△");
			RadioMap.put(str, "×");
			}
			model.addAttribute("radio", RadioMap);
			model.addAttribute("map", result);
			model.addAttribute("date", resultdate);
			model.addAttribute("eventID", eventID);
		return "view/answer";
	}

	@RequestMapping("/view/answerRes")
	public String list(@ModelAttribute @Validated DataForm2 answer,String eventID,Model model){
	 	Map<String, Object> result = service.get(eventID);
    	List<Map<String, Object>> dateans = service.getDateAns(eventID);
    	List<Map<String, Object>> resultdate = service.getdate(eventID);
    	List<Map<String, Object>> listanswer = service.getAnswerData(eventID);
     	List<Map<String, Object>> userRemarks = service.getUserRemarks(eventID);
     	List<Map<String, Object>> comments = service.getUserRemarks2(eventID);
     	List<String> com = new ArrayList<>();
    	Map<String, List<String>> map = new LinkedHashMap<>();
    	int count =0;
    	for(int i=0; i<resultdate.size(); i++) {
    		List<String> aaa = new ArrayList<>();
    		for(int p=0; p<userRemarks.size(); p++) {
    			aaa.add(dateans.get(count).values().toString());
    			count++;
    		}
    		map.put(resultdate.get(i).values().toString(), aaa);
    	}

    	for(int i=0; i<comments.size(); i++) {
			com.add(comments.get(i).values().toString());
		}
    	map.put("comments",com );
       	model.addAttribute("listanswer", listanswer);
       	model.addAttribute("map", map);
       	model.addAttribute("userRemarks", userRemarks);
       	model.addAttribute("event", result);
		return "view/answerRes";
	}

    @PostMapping("/view/create")
	public String create(@ModelAttribute @Validated DataForm eventdata, Model model){
    	Map<String, Object> ID = service.create(eventdata);
    	Collection<Object> IDD = ID.values();
    	String URL = "http://localhost:8080/view/answer/" + IDD.toString();
		model.addAttribute("URL", URL);
		model.addAttribute("ID", ID);
		return "view/select";
	}

    @PostMapping("/view/createAnswer")
   	public String createAnswer(@ModelAttribute @Validated DataForm2 answer,String eventID, Model model){
       	service.createAnswer(answer);
       	Map<String, Object> result = service.get(eventID);
    	List<Map<String, Object>> dateans = service.getDateAns(eventID);
    	List<Map<String, Object>> resultdate = service.getdate(eventID);
    	List<Map<String, Object>> listanswer = service.getAnswerData(eventID);
     	List<Map<String, Object>> userRemarks = service.getUserRemarks(eventID);
     	List<Map<String, Object>> comments = service.getUserRemarks2(eventID);
     	List<String> com = new ArrayList<>();
    	Map<String, List<String>> map = new LinkedHashMap<>();
    	int count =0;
    	for(int i=0; i<resultdate.size(); i++) {
    		List<String> aaa = new ArrayList<>();
    		for(int p=0; p<userRemarks.size(); p++) {
    			aaa.add(dateans.get(count).values().toString());
    			count++;
    		}
    		map.put(resultdate.get(i).values().toString(), aaa);
    	}

    	for(int i=0; i<comments.size(); i++) {
			com.add(comments.get(i).values().toString());
		}
    	map.put("comments",com );
       	model.addAttribute("listanswer", listanswer);
       	model.addAttribute("map", map);
       	model.addAttribute("userRemarks", userRemarks);
       	model.addAttribute("event", result);
   		return "view/answerRes";

   	}




}
