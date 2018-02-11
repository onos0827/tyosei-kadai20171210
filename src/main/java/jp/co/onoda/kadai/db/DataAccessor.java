package jp.co.onoda.kadai.db;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jp.co.onoda.kadai.form.answer;

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

		String[] datesplit =eventDate.split("\r?\n");

		for(int i=0; i<datesplit.length; i++) {
		 jdbcTemplate.update("INSERT INTO EVENT_DATE(EVENT_ID,SET_DATE) VALUES(SEQ_EVENT_ID.currval,?)",
			datesplit[i]);
		}
		return null;
	}

	public int insertUserDATA(String eventID,String userID, String userName,String userRemarks){

		int count = jdbcTemplate.update("INSERT INTO USER_DATA(EVENT_ID,USER_ID,USER_NAME,USER_REMARKS) VALUES(SEQ_EVENT_ID.currval,SEQ_USER_ID.nextval,?,?)",
				userName,userRemarks);
		return count;
	}

	public String insertAnswerDATA(String eventID,String userID, String[] eventDate, List<answer> radio){


		for(int i=0; i<eventDate.length; i++) {
		jdbcTemplate.update("INSERT INTO ANSWER_DATA(EVENT_ID,USER_ID,SET_DATE,ANSWER) VALUES(SEQ_EVENT_ID.currval,SEQ_USER_ID.currval,?,?)",
				eventDate[i],radio.get(i).getRadiobutton());
		}

		return null;
	}




	public Map<String, Object> getEventData(String eventName,String Remarks) {
		Map<String, Object> row = jdbcTemplate.queryForMap("SELECT EVENT_ID FROM EVENT_DATA WHERE EVENT_NAME=? AND REMARKS=?", eventName,Remarks);
		return row;
	}

	public Map<String, Object> getEventData2(String eventID) {
		Map<String, Object> row = jdbcTemplate.queryForMap("SELECT EVENT_NAME,REMARKS FROM EVENT_DATA WHERE EVENT_ID=?", eventID);
		return row;
	}

	public List<Map<String,Object> >getEventDate(String eventID) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT SET_DATE FROM EVENT_DATE WHERE EVENT_ID=?", eventID);
		return list;
	}


}
