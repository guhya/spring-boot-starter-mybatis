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

import org.apache.ibatis.annotations.*;
import sample.mybatis.web.domain.City;
import sample.mybatis.web.domain.District;

import java.util.List;

/**
 * @author Eddú Meléndez
 */
@Mapper
public interface DistrictMapper {

    @Select("""
            SELECT 
                ID, DISTRICT, CITY, STATE, COUNTRY, INCOME 
            FROM DISTRICT 
            WHERE ID = #{id}
            """)
    District findById(@Param("id") long id);

    @Select("""
            SELECT 
                ID, DISTRICT, CITY, STATE, COUNTRY, INCOME 
            FROM DISTRICT 
            WHERE CITY = #{city}
            """)
    List<District> findByCity(@Param("city") String city);

    @Select("""
            SELECT 
                ID, DISTRICT, CITY, STATE, COUNTRY, INCOME  
            FROM DISTRICT
            """)
    List<District> findAll();

    @Select("""
            SELECT 
                ID, DISTRICT, CITY, STATE, COUNTRY, INCOME 
            FROM DISTRICT
            """)
    List<District> findAllPaged();

    @Insert("""
            INSERT INTO DISTRICT(DISTRICT, CITY, STATE, COUNTRY, INCOME) 
            VALUES (#{district.district}, #{district.city}, #{district.state}, #{district.country}, #{district.income})
            """)
    void insert(@Param("district") District district);

    @Update("""
            UPDATE DISTRICT 
                SET DISTRICT    = #{district.district}
                , CITY          = #{district.city}
                , STATE         = #{district.state}
                , COUNTRY       = #{district.country} 
                , INCOME        = #{district.income} 
            WHERE ID = #{district.id}
            """)
    void update(@Param("district") District district);
}
