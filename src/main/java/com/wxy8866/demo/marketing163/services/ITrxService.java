package com.wxy8866.demo.marketing163.services;

import com.wxy8866.demo.marketing163.entities.Trx;

import java.util.List;

/**
 * Created by wxy8866 on 2017/3/21.
 */
public interface ITrxService extends IBaseService<Trx>
{
    public boolean isContentSold(Integer contentID);

    public boolean isUserBoughtContent(Integer personID, Integer contentID);

    public List<Trx> getUserContentRecords(Integer personID, Integer contentID);

    public List<Trx> getUserBuyHistory(Integer personID);

    public Long getSaleNum(Integer contentID);

    public Long insertBuyRecord(Integer personID, Integer contentID, Integer currentPrice, Long amount);
}
