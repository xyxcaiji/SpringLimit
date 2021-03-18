package com.example.mylimit.mymapper;

import com.example.mylimit.bean.China;
import lombok.var;
import org.apache.ibatis.jdbc.SQL;

public class ChinaSelect {
    public String select(final China china,final int pageNo,final int pageSize){
        String china1 = new SQL() {
            {
                SELECT("*");
                FROM("china");
            }
        }.toString();
        System.out.println(china1+" limit "+pageNo+","+pageSize+"");
        return china1+" limit "+pageNo+","+pageSize+"";

    }
}
