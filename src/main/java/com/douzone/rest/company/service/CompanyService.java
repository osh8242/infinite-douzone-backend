package com.douzone.rest.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

@Service
public class CompanyService {

    @Autowired
    private DataSource dataSource;

    public void createNewSchema(String companyCode, String password) throws Exception {
        ClassPathResource sqlFilePath = new ClassPathResource("ddlQuery.sql");
        try (Connection conn = dataSource.getConnection()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(sqlFilePath.getInputStream()))) {
                String line;
                StringBuilder sqlBuilder = new StringBuilder();

                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        line = line.replace("1004", password);
                        isFirstLine = false;
                    }
                    // "HR" 단어를 새 단어로 바꾸기
                    line = line.replaceAll("HRHR", companyCode);

                    sqlBuilder.append(line);
                    if (line.endsWith(";")) {
                        try (Statement stmt = conn.createStatement()) {
                            String sqlToExecute = sqlBuilder.toString();
                            System.out.println("Executing SQL: " + sqlToExecute);  // 쿼리 로깅
                            stmt.execute(sqlBuilder.toString());
                        }
                        sqlBuilder.setLength(0);
                    }
                }
            }
        }
    }
}
