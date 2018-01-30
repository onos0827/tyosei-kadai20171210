package jp.co.onoda.kadai.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.onoda.kadai.db.DataAccessor;
import jp.co.onoda.kadai.form.DataForm;

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
		Map<String, Object> row = accessor.getEventData(eventdata.getEventName());
		return row;
	}

	@Override
	public Map<String, Object> get(String eventName) {
		logger.info("get start");
		Map<String, Object> row = accessor.getEventData(eventName);
		logger.info("get end: {}", row);
		return row;
	}

	@Override
	public List<Map<String, Object>> list() {
		logger.info("list start");
		List<Map<String, Object>> list = accessor.getEventInfo();
		logger.info("list end: {}", list);
		return list;
	}


}
