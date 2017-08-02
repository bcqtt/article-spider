package com.lz.hadoop.hdfs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public class RegxAcceptPathFilter implements PathFilter{
    private  final String regex;
    public RegxAcceptPathFilter(String regex) {
        this.regex=regex;
    }
    @Override
    public boolean accept(Path path) {

        boolean flag=path.toString().matches(regex);
        return flag;
    }
}
