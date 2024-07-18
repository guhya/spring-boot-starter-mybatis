/*
 *    Copyright 2015-2024 the original author or authors.
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
package sample.mybatis.lombok.mapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import sample.mybatis.lombok.domain.District;
import sample.mybatis.lombok.domain.DistrictCityId;

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
@Slf4j
class DistrictMapperTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    void findByCityTest() {
        List<District> districtList = districtMapper.findByCity("San Francisco");
        assertThat(districtList.get(0).getId()).isEqualTo(1);
    }

    @Test
    void findWithCityIdByIdTest() {
        DistrictCityId district = districtMapper.findWithCityIdById(1);
        log.debug("District [{}]", district);
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

        log.debug("Before insert [{}]", d);
        districtMapper.insert(d);
        log.debug("After insert [{}]", d);
        log.debug("Insert ID [{}]", d.getId());

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

    @Test
    void findAllPagedTest() {
        District district = new District();
        district.setDistrict("en");
        List<District> districtList = districtMapper.findAllPaged(district, 0, 10);

        log.debug("District List Size [{}]", districtList.size());
        int districtCount = districtMapper.countAll(district);
        log.debug("Total size [{}]", districtCount);
    }

}
