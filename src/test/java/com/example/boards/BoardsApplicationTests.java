package com.example.boards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class BoardsApplicationTests {

	@Value("${spring.datasource.boardName}")
	String boardName;
	@Test
	void contextLoads() {
	}
	@Test
	void dbBoardNameIsSa(){
		assertEquals("sa",boardName);
	}

}
