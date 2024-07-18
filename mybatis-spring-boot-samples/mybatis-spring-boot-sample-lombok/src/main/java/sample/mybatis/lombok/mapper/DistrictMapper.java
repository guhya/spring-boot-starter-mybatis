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

import org.apache.ibatis.annotations.*;
import sample.mybatis.lombok.domain.District;
import sample.mybatis.lombok.domain.DistrictCityId;

import java.util.List;

/**
 * @author Eddú Meléndez
 */
@Mapper
public interface DistrictMapper {

    static final String WHERE_SQL = """
            <if test="district.city != null">
                AND A.CITY LIKE CONCAT(CONCAT('%',#{district.city}),'%')
            </if>
            <if test="district.district != null">
                AND A.DISTRICT LIKE CONCAT(CONCAT('%',#{district.district}),'%')
            </if>
            """;

    @Select("""
            SELECT
                ID, DISTRICT, CITY, STATE, COUNTRY, INCOME
            FROM DISTRICT
            WHERE ID = #{id}
            """)
    District findById(@Param("id") long id);

    @Select("""
            SELECT
                A.ID, A.DISTRICT, A.CITY, A.STATE, A.COUNTRY, A.INCOME
                , B.ID AS CITY_ID
            FROM DISTRICT A
                LEFT JOIN CITY B 
                    ON A.CITY = B.NAME
            WHERE A.ID = #{id}
            """)
    DistrictCityId findWithCityIdById(@Param("id") long id);

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
            <script>
            SELECT 
                COUNT(1)
            FROM DISTRICT A
            WHERE 1=1
            """ + WHERE_SQL + """
            </script>
            """)
    int countAll(@Param("district") District district);

    @Select("""
            <script>
            SELECT
                A.ID, A.DISTRICT, A.CITY, A.STATE, A.COUNTRY, A.INCOME
            FROM DISTRICT A
            WHERE 1=1
            """ + WHERE_SQL + """
            ORDER BY A.ID DESC
            LIMIT #{startRow}, #{pageSize}
            </script>
            """)
    List<District> findAllPaged(@Param("district") District district,
                                @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    @Insert("""
            INSERT INTO DISTRICT(DISTRICT, CITY, STATE, COUNTRY, INCOME)
            VALUES (#{district.district}, #{district.city}, #{district.state}, #{district.country}, #{district.income})
            """)
    @Options(useGeneratedKeys = true, keyColumn = "ID", keyProperty = "id")
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
