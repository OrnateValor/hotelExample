package com.ovalor.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection() {
		System.out.println("testConnection>>>>>>>>>>>");
		String connIp = "192.168.5.22";
		String dbName = "sampledb";
		String connurl = "jdbc:postgresql://" + connIp + "/" + dbName;
		System.out.println("connurl : " + connurl);
		String userId = "testuser";
		String userPw = "1234";

		try (Connection conn = DriverManager.getConnection(connurl, userId, userPw)) {
			System.out.println("Connection Success!!");
			log.info(conn);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
