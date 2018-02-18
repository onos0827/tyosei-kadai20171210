package jp.co.onoda.kadai.service;




import java.util.List;
import java.util.Map;

import jp.co.onoda.kadai.form.DataForm;
import jp.co.onoda.kadai.form.DataForm2;

public interface DataService {


	public Map<String, Object> create(DataForm eventdata);

	public Map<String, Object> createAnswer(DataForm2 answer);

	public Map<String, Object> get(String eventID);

	public  List<Map<String, Object>> getdate(String eventID);

	public  List<Map<String, Object>> getAnswerData(String eventID);

	public  List<Map<String, Object>> getAnswerData2(String eventID);

	public  List<Map<String, Object>> getUserRemarks(String eventID);


}