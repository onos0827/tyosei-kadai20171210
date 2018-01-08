package jp.co.onoda.kadai.service;




import java.util.List;
import java.util.Map;

import jp.co.onoda.kadai.form.DataForm;

public interface DataService {


	public Map<String, Object> create(DataForm eventdata);

	public Map<String, Object> get(String eventName);

	public List<Map<String, Object>> list();


}