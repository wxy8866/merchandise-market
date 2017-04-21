package com.wxy8866.demo.marketing163.mybatis.mappers;

import com.wxy8866.demo.marketing163.entities.Content;
import com.wxy8866.demo.marketing163.entities.ContentExample;
import com.wxy8866.demo.marketing163.entities.ContentWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContentMapper
{
    long countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContentWithBLOBs record);

    int insertSelective(ContentWithBLOBs record);

    List<ContentWithBLOBs> selectByExampleWithBLOBs(ContentExample example);

    List<Content> selectByExample(ContentExample example);

    ContentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContentWithBLOBs record, @Param("example") ContentExample example);

    int updateByExampleWithBLOBs(@Param("record") ContentWithBLOBs record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(ContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ContentWithBLOBs record);

    int updateByPrimaryKey(Content record);
}