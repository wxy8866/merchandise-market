package com.wxy8866.demo.marketing163.services.impl;

import com.wxy8866.demo.marketing163.entities.Trx;
import com.wxy8866.demo.marketing163.entities.TrxExample;
import com.wxy8866.demo.marketing163.mybatis.mappers.TrxMapper;
import com.wxy8866.demo.marketing163.services.ITrxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wxy8866 on 2017/3/16.
 */
@Service
@Transactional
public class TrxService implements ITrxService
{
    @Autowired
    private TrxMapper trxMapper;

    @Override
    public int create(Trx entity)
    {
        return trxMapper.insert(entity);
    }

    @Override
    public int update(Trx entity)
    {
        return trxMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Trx findById(Serializable id)
    {
        return trxMapper.selectByPrimaryKey((Integer) id);
    }

    @Override
    public List<Trx> findAll()
    {
        return trxMapper.selectByExample(null);
    }

    @Override
    public int delete(Trx entity)
    {
        return trxMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public int deleteAll()
    {
        return trxMapper.deleteByExample(null);
    }

    @Override
    public boolean isContentSold(Integer contentID)
    {
        TrxExample trxExample = new TrxExample();
        trxExample.createCriteria().andContentidEqualTo(contentID);
        return trxMapper.countByExample(trxExample) > 0 ? true : false;
    }

    @Override
    public boolean isUserBoughtContent(Integer personID, Integer contentID)
    {
        TrxExample trxExample = new TrxExample();
        trxExample.createCriteria().andContentidEqualTo(contentID).andPersonidEqualTo(personID);
        return trxMapper.countByExample(trxExample) > 0 ? true : false;
    }

    @Override
    public List<Trx> getUserContentRecords(Integer personID, Integer contentID)
    {
        TrxExample trxExample = new TrxExample();
        trxExample.createCriteria().andContentidEqualTo(contentID).andPersonidEqualTo(personID);
        return trxMapper.selectByExample(trxExample);
    }

    @Override
    public List<Trx> getUserBuyHistory(Integer personID)
    {
        TrxExample trxExample = new TrxExample();
        trxExample.createCriteria().andPersonidEqualTo(personID);
        return trxMapper.selectByExample(trxExample);
    }

    @Override
    public Long getSaleNum(Integer contentID)
    {
        TrxExample trxExample = new TrxExample();
        trxExample.createCriteria().andContentidEqualTo(contentID);
        return trxMapper.countByExample(trxExample);
    }

    @Override
    public Long insertBuyRecord(Integer personID, Integer contentID, Integer currentPrice, Long amount)
    {
        Date date = new Date();
        long currentTimeInLong = date.getTime();

        Trx trx = new Trx();
        trx.setPersonid(personID);
        trx.setContentid(contentID);
        trx.setPrice(currentPrice);
        trx.setTime(currentTimeInLong);
        Long i = 0l;
        for (; i < amount; i++)
        {
            trxMapper.insert(trx);
        }
        return i;
    }
}
