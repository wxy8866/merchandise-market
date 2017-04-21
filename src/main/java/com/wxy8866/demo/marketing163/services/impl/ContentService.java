package com.wxy8866.demo.marketing163.services.impl;

import com.wxy8866.demo.marketing163.entities.ContentWithBLOBs;
import com.wxy8866.demo.marketing163.mybatis.mappers.ContentMapper;
import com.wxy8866.demo.marketing163.services.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wxy8866 on 2017/3/16.
 */
@Service
@Transactional
public class ContentService implements IContentService
{
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public int create(ContentWithBLOBs entity)
    {
        return contentMapper.insert(entity);
    }

    @Override
    public int update(ContentWithBLOBs entity)
    {
        return contentMapper.updateByPrimaryKeyWithBLOBs(entity);
    }

    @Override
    public ContentWithBLOBs findById(Serializable id)
    {
        return contentMapper.selectByPrimaryKey((Integer) id);
    }

    @Override
    public List<ContentWithBLOBs> findAll()
    {
        return contentMapper.selectByExampleWithBLOBs(null);
    }

    @Override
    public int delete(ContentWithBLOBs entity)
    {
        return contentMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public int deleteAll()
    {
        return contentMapper.deleteByExample(null);
    }
}
