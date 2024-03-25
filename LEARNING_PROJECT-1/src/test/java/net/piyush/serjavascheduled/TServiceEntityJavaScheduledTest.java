package net.piyush.serjavascheduled;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.piyush.dto.DtoJavaScheduledTask;


@SpringBootTest
class TServiceEntityJavaScheduledTest {

	@Autowired
	private ServiceEntityJavaScheduled serJavaScheduled;
	
	@Test
	public void testToSaveScheduledInDB() {
		
		
		DtoJavaScheduledTask d1 = new DtoJavaScheduledTask();
		d1.setTopicJDto("String");
		d1.setChatpterJDto("DummyChapter2");
		d1.setMode1jDto("DummyMode1");
		d1.setUniqueId1Dto("111DummyUniqueId1");
		d1.setUniqueId2Dto("22DummyUniqueId2");
		d1.setMode2jDto("DummyMode2");
		d1.setClear1JDto("DummyClear1");
		d1.setClear2JDto("DummyClear2");
		d1.setClear3JDto("DummyClear3");
		d1.setOverallJDto("DummyOverall");
        
		//serJavaScheduled.saveTaskInDBForJava(d1);
		
		fail("Not yet implemented");
	}

}
