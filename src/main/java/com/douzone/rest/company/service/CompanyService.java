package com.douzone.rest.company.service;

import com.douzone.rest.company.vo.DataSourceVo;
import com.douzone.rest.datasource.service.DataSourceService;
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

    @Autowired
    private DataSourceService dataSourceService;

    public void createNewSchema(String companyCode, String password) throws Exception {
        ClassPathResource sqlFilePath = new ClassPathResource("ddlQuery.sql");
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);  // 트랜잭션 시작
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(sqlFilePath.getInputStream()))) {
                String line;
                StringBuilder sqlBuilder = new StringBuilder();

                boolean isFirstLine = true;
                boolean isTrigger = false;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        line = line.replace("1004", password);
                        isFirstLine = false;
                    }
                    // "HRHR" 단어를 새 단어로 바꾸기
                    line = line.replaceAll("HRHR", companyCode);

                    // 주석 라인 무시
                    if (line.trim().startsWith("--")) continue;
                    if (line.trim().startsWith("CREATE OR REPLACE TRIGGER")) isTrigger = true;

                    sqlBuilder.append(line).append("\n");
                    if (line.endsWith(";") && !isTrigger) {
                        try (Statement stmt = conn.createStatement()) {
                            String sqlToExecute = sqlBuilder.toString().replace(";", "");  // 세미콜론 제거
                            System.out.println(sqlBuilder.toString());
                            stmt.execute(sqlToExecute);
                        }
                        sqlBuilder.setLength(0);
                    }
                    if (isTrigger && line.trim().equalsIgnoreCase("END;")) {
                        // "END;" 문자열을 만났으므로 전체 쿼리를 실행
                        try (Statement stmt = conn.createStatement()) {
                            stmt.execute(sqlBuilder.toString());
                        }
                        isTrigger = false;
                        sqlBuilder.setLength(0); // StringBuilder 초기화
                    }
                }

            }
            conn.commit();  // 트랜잭션 커밋
            dataSourceService.insertDataSourceVo(new DataSourceVo(companyCode, password));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
