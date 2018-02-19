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

	public List<Map<String,Object> >getAnswerData(String eventID) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select DISTINCT A.SET_DATE,B.MARU,D.SANKAKU,C.BATU from ANSWER_DATA  A\r\n" +
				"left outer join(\r\n" +
				"select SET_DATE,count(ANSWER) as MARU from ANSWER_DATA \r\n" +
				"group by(SET_DATE,ANSWER)\r\n" +
				"having ANSWER IN('〇')\r\n" +
				") AS B\r\n" +
				"on A.SET_DATE = B.SET_DATE\r\n" +
				"left outer join(\r\n" +
				"select SET_DATE,count(ANSWER) as BATU from ANSWER_DATA \r\n" +
				"group by(SET_DATE,ANSWER)\r\n" +
				"having ANSWER IN('×')\r\n" +
				") AS C\r\n" +
				"on A.SET_DATE = C.SET_DATE\r\n" +
				"left outer join(\r\n" +
				"select SET_DATE,count(ANSWER) as SANKAKU from ANSWER_DATA \r\n" +
				"group by(SET_DATE,ANSWER)\r\n" +
				"having ANSWER IN('△')\r\n" +
				") AS D\r\n" +
				"on A.SET_DATE = D.SET_DATE\r\n" +
				"where EVENT_ID = ?\r\n" +
				"group by(A.SET_DATE,A.ANSWER)\r\n" +
				"order by SET_DATE",eventID);
		return list;
	}

	public List<Map<String,Object> >getAnswerData2(String eventID) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select DISTINCT SET_DATE,USER_NAME,ANSWER from USER_DATA a\r\n" +
				"inner join ANSWER_DATA b on a.EVENT_ID = b.EVENT_ID  \r\n" +
				"and a.USER_ID = b.USER_ID \r\n" +
				"where b.EVENT_ID = ?\r\n" +
				"order by USER_NAME,SET_DATE",eventID);
		return list;
	}

	public List<Map<String,Object> >getUserRemarks(String eventID) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select USER_NAME,USER_REMARKS from USER_DATA where EVENT_ID = ?", eventID);
		return list;
	}


}
