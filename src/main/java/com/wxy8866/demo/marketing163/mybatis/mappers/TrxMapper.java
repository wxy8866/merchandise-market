package com.wxy8866.demo.marketing163.mybatis.mappers;

import com.wxy8866.demo.marketing163.entities.Trx;
import com.wxy8866.demo.marketing163.entities.TrxExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TrxMapper
{
    long countByExample(TrxExample example);

    int deleteByExample(TrxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Trx record);

    int insertSelective(Trx record);

    List<Trx> selectByExample(TrxExample example);

    Trx selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Trx record, @Param("example") TrxExample example);

    int updateByExample(@Param("record") Trx record, @Param("example") TrxExample example);

    int updateByPrimaryKeySelective(Trx record);

    int updateByPrimaryKey(Trx record);
}