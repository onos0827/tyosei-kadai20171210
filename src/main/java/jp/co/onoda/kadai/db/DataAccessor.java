package jp.co.onoda.kadai.db;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataAccessor {

	final static Logger logger = LoggerFactory.getLogger(DataAccessor.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	//@Bean
		//public String createtb() {
		//	jdbcTemplate.execute("CREATE TABLE EVENT_DATA (EVENT_NAME VARCHAR2(50), EVENT_DATE VARCHAR2(50), REMARKS VARCHAR2(500), PRIMARY KEY(EVENT_NAME))");
		//	return null;

		//  }

	public int insertEventDATA(String eventId, String eventName,String Remarks){

		int count = jdbcTemplate.update("INSERT INTO EVENT_DATA(EVENT_ID,EVENT_NAME,REMARKS) VALUES(SEQ_EVENT_ID.nextval,?,?)",
				eventName,Remarks);
		return count;
	}

	public String insertEventDATE(String eventId, String eventDate){

		String[] datesplit = eventDate.split("\r?\n");

		for(int i=0; i<datesplit.length; i++) {
		 jdbcTemplate.update("INSERT INTO EVENT_DATE(EVENT_ID,SET_DATE) VALUES(SEQ_EVENT_ID.currval,?)",
			datesplit[i]);
		}
		return null;
	}





	public int insertATDInfo(String name, String answer, String atdremarks){
		int count = jdbcTemplate.update("INSERT INTO ATD_RESULT(NAME,ANSWER,ATD_REMARKS) VALUES(?,?,?)",
				name, answer, atdremarks);
		return count;
	}

	public Map<String, Object> getEventData(String eventName) {
		Map<String, Object> row = jdbcTemplate.queryForMap("SELECT EVENT_ID FROM EVENT_DATA WHERE EVENT_NAME=?", eventName);
		return row;
	}

	public Map<String, Object> getEventDate(String eventId) {
		Map<String, Object> row = jdbcTemplate.queryForMap("SELECT * FROM EVENT_DATE WHERE EVENT_ID=?", eventId);
		return row;
	}

	public List<Map<String,Object>> getEventInfo(){
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM EVENT_DATA");
		return list;
	}

	public Map<String, Object> getatdresult(String Name) {
		Map<String, Object> row = jdbcTemplate.queryForMap("SELECT * FROM ATD_RESULT WHERE NAME=?", Name);
		return row;
	}
}
