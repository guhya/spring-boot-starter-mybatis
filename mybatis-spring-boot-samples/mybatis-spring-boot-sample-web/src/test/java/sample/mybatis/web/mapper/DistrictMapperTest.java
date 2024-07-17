/*
 *    Copyright 2015-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package sample.mybatis.web.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import sample.mybatis.web.domain.City;
import sample.mybatis.web.domain.District;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CityMapper}.
 *
 * @author wonwoo
 * @since 1.2.1
 */
@MybatisTest
class DistrictMapperTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    void findByCityTest() {
        List<District> districtList = districtMapper.findByCity("San Francisco");
        assertThat(districtList.get(0).getId()).isEqualTo(1);
    }

    @Test
    void findAllTest() {
        List<District> districtList = districtMapper.findAll();
        System.out.println(districtList.get(0));
        assertThat(districtList.get(0).getId()).isEqualTo(1);
    }

    @Test
    void insertTest() {
        District d = new District();
        d.setCity("California");
        d.setDistrict("Pasadena");
        d.setCountry("US");
        d.setState("CA");
        d.setIncome(BigDecimal.valueOf(30000));

        districtMapper.insert(d);
        districtMapper.findAll();
    }

    @Test
    void updateTest() {
        districtMapper.findAll();
        District d = districtMapper.findById(1);
        d.setDistrict("Edited Village");
        d.setIncome(BigDecimal.valueOf(15000));
        districtMapper.update(d);
        districtMapper.findAll();
    }
}
