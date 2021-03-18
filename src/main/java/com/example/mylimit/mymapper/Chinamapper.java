package com.example.mylimit.mymapper;

import com.example.mylimit.bean.China;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

@Mapper
public interface Chinamapper {
    //@Select("select * from china where pid=0")
    @SelectProvider(type = ChinaSelect.class,method = "select")
    ArrayList<China> getAll(China china,int pageNo,int pageSize);

    @Select("select * from china LIMIT ${(pageNo-1)*pageSize},${pageSize};")
    ArrayList<China> getOne(int pageNo,int pageSize);

    @Select("select count(*) from china where pid=0")
    int get();
}
