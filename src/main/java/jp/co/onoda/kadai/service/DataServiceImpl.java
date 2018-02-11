package jp.co.onoda.kadai.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.onoda.kadai.db.DataAccessor;
import jp.co.onoda.kadai.form.DataForm;
import jp.co.onoda.kadai.form.DataForm2;

@Service
public class DataServiceImpl implements DataService {

	final static Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

	@Autowired
	DataAccessor accessor;

	@Override
	public Map<String, Object> create(DataForm eventdata){
		 accessor.insertEventDATA(
				eventdata.getEventId(), eventdata.getEventName(), eventdata.getRemarks());
		 accessor.insertEventDATE(
					eventdata.getEventId(), eventdata.getEventDate());
		Map<String, Object> row = accessor.getEventData(eventdata.getEventName(), eventdata.getRemarks());
		return row;
	}

	@Override
	public Map<String, Object> get(String eventID) {
		Map<String, Object> row = accessor.getEventData2(eventID);
		return row;
	}

	@Override
	public List<Map<String, Object>> getdate(String eventID) {
		List<Map<String, Object>> listdate = accessor.getEventDate(eventID);
		return listdate;
	}



	@Override
	public Map<String, Object> createAnswer(DataForm2 answer){
		 accessor.insertUserDATA(
				 answer.geteventID(), answer.getuserID(), answer.getuserName(),answer.getuserRemarks());

		accessor.insertAnswerDATA(
				 answer.geteventID(), answer.getuserID(), answer.geteventDate(),answer.getRadio());

		 return null;

	}



}
