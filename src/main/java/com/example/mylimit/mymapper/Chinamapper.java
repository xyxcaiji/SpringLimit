package com.example.mylimit.mymapper;

import com.example.mylimit.bean.China;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface Chinamapper {
    @Select("select * from china where pid=0")
    ArrayList<China> getAll();

    @Select("select * from china where id=0")
    ArrayList<China> getOne();
}
