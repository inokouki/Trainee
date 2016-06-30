package jp.co.kenshu.mapper;

import java.util.List;

import jp.co.kenshu.dto.test.TestDto;
import jp.co.kenshu.entity.Test;

public interface TestMapper {
    Test getTest(int id);

    List<Test> getTestAll();

    Test getTestByDto(TestDto dto);

    int insertTest(String name);

    int deleteTest(int id);

    int updateTest(TestDto dto);
}

