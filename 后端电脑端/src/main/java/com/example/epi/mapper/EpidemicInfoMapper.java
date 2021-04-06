package com.example.epi.mapper;

import com.example.epi.model.EpidemicInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface EpidemicInfoMapper {
    /*@Select("select * from epidemicinfo")*/
    @MapKey("id")
    public Map<Integer, EpidemicInfo> findAllEpidemicInfo();

    public  Map<Integer, EpidemicInfo> findByName(@Param(value = "name") String name);

    public EpidemicInfo findByIdCard(@Param(value = "id_crad") long id_card);

    public void deleteEpidemicInfoByName(@Param(value = "name") String name);

    public void addEpidemicInfo(EpidemicInfo epidemicInfo);

    public void updateEpidemicInfo();


}
