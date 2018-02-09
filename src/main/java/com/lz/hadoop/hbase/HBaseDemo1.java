package com.lz.hadoop.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

public class HBaseDemo1 {
	
	// 与HBase数据库的连接对象
    public static Connection connection;

    // 数据库元数据操作对象
    public static Admin admin;
    
    static {
        // 取得一个数据库连接的配置参数对象
        Configuration conf = HBaseConfiguration.create();

        // 设置连接参数：HBase数据库所在的主机IP
        conf.set("hbase.zookeeper.quorum", "66.66.66.10");
        // 设置连接参数：HBase数据库使用的端口
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        
        try {
        	// 取得一个数据库连接对象
        	connection = ConnectionFactory.createConnection(conf);
        	
        	// 取得一个数据库元数据操作对象
        	admin = connection.getAdmin();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 创建表
     */
    public void createTable() throws IOException{

        System.out.println("---------------创建表 START-----------------");

        // 数据表表名
        String tableNameString = "t_book";

        // 新建一个数据表表名对象
        TableName tableName = TableName.valueOf(tableNameString);

        // 如果需要新建的表已经存在
        if(admin.tableExists(tableName)){
            System.out.println("表已经存在！");
        }
        // 如果需要新建的表不存在
        else{

            // 数据表描述对象
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);

            // 列族描述对象
            HColumnDescriptor family= new HColumnDescriptor("base");;

            // 在数据表中新建一个列族
            hTableDescriptor.addFamily(family);

            // 新建数据表
            admin.createTable(hTableDescriptor);
        }
        System.out.println("---------------创建表 END-----------------");
    }
    
    /**
     * 查询整表数据
     */
     public void queryTable() throws IOException{

        System.out.println("---------------查询整表数据 START-----------------");
        // 取得数据表对象
        Table table = connection.getTable(TableName.valueOf("t_book"));

        // 取得表中所有数据
        ResultScanner scanner = table.getScanner(new Scan());

        // 循环输出表中的数据
        for (Result result : scanner) {

            byte[] row = result.getRow();
            System.out.println("row key is:" + new String(row));

            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {

                byte[] familyArray = cell.getFamilyArray();
                byte[] qualifierArray = cell.getQualifierArray();
                byte[] valueArray = cell.getValueArray();

                System.out.println("row value is:" + new String(familyArray) + new String(qualifierArray)) ;
            }
        }
        System.out.println("---------------查询整表数据 END-----------------");
    }
     
     /**
      * 按行键查询表数据
      */
     public void queryTableByRowKey() throws IOException{

         System.out.println("---------------按行键查询表数据 START-----------------");

         // 取得数据表对象
         Table table = connection.getTable(TableName.valueOf("t_book"));

         // 新建一个查询对象作为查询条件
         Get get = new Get("row8".getBytes());

         // 按行键查询数据
         Result result = table.get(get);

         byte[] row = result.getRow();
         System.out.println("行键为:" + new String(row));

         List<Cell> listCells = result.listCells();
         for (Cell cell : listCells) {

             byte[] familyArray = cell.getFamilyArray();
             byte[] qualifierArray = cell.getQualifierArray();
             byte[] valueArray = cell.getValueArray();

             System.out.println("行数据为:" + new String(familyArray) + new String(qualifierArray) 
                                                                          + new String(valueArray));
         }

         System.out.println("---------------按行键查询表数据 END-----------------");

     }
      
      /**
       * 按条件查询表数据
       */
      public void queryTableByCondition() throws IOException{

          System.out.println("---------------按条件查询表数据 START-----------------");

          // 取得数据表对象
          Table table = connection.getTable(TableName.valueOf("t_book"));

          // 创建一个查询过滤器
          Filter filter = new SingleColumnValueFilter(Bytes.toBytes("base"), Bytes.toBytes("name"), 
                                                      CompareOp.EQUAL, Bytes.toBytes("bookName6"));

          // 创建一个数据表扫描器
          Scan scan = new Scan();

          // 将查询过滤器加入到数据表扫描器对象
          scan.setFilter(filter);

          // 执行查询操作，并取得查询结果
          ResultScanner scanner = table.getScanner(scan);

          // 循环输出查询结果
          for (Result result : scanner) {
              byte[] row = result.getRow();
              System.out.println("row key is:" + new String(row));

              List<Cell> listCells = result.listCells();
              for (Cell cell : listCells) {

                  byte[] familyArray = cell.getFamilyArray();
                  byte[] qualifierArray = cell.getQualifierArray();
                  byte[] valueArray = cell.getValueArray();

                  System.out.println("row value is:" + new String(familyArray) + new String(qualifierArray) 
                                                                               + new String(valueArray));
              }
          }

          System.out.println("---------------按条件查询表数据 END-----------------");

      }
       
       /**
        * 清空表
        */
       public void truncateTable() throws IOException{

           System.out.println("---------------清空表 START-----------------");

           // 取得目标数据表的表名对象
           TableName tableName = TableName.valueOf("t_book");

           // 设置表状态为无效
           admin.disableTable(tableName);
           // 清空指定表的数据
           admin.truncateTable(tableName, true);

           System.out.println("---------------清空表 End-----------------");
       }
       
       /**
        * 删除表
        * @param tableName 表名
        */
       public void deleteTable(String tableName) throws IOException{

           System.out.println("---------------删除表 START-----------------");

           // 设置表状态为无效
           admin.disableTable(TableName.valueOf(tableName));
           // 删除指定的数据表
           admin.deleteTable(TableName.valueOf(tableName));

           System.out.println("---------------删除表 End-----------------");
       }
       
       /**
        * 删除行
        */
       public void deleteByRowKey() throws IOException{

           System.out.println("---------------删除行 START-----------------");

           // 取得待操作的数据表对象
           Table table = connection.getTable(TableName.valueOf("t_book"));

           // 创建删除条件对象
           Delete delete = new Delete(Bytes.toBytes("row2"));

           // 执行删除操作
           table.delete(delete);

           System.out.println("---------------删除行 End-----------------");

       }
       
       /**
        * 删除行（按条件）
        */
       public void deleteByCondition() throws IOException, DeserializationException{

           System.out.println("---------------删除行（按条件） START-----------------");

           // 步骤1：调用queryTableByCondition()方法取得需要删除的数据列表 

           // 步骤2：循环步骤1的查询结果，对每个结果调用deleteByRowKey()方法

           System.out.println("---------------删除行（按条件） End-----------------");

       }
       
       /**
        * 新建列族
        */
       public void addColumnFamily() throws IOException{

           System.out.println("---------------新建列族 START-----------------");

           // 取得目标数据表的表名对象
           TableName tableName = TableName.valueOf("t_book");

           // 创建列族对象
           HColumnDescriptor columnDescriptor = new HColumnDescriptor("more");

           // 将新创建的列族添加到指定的数据表
           admin.addColumn(tableName, columnDescriptor);

           System.out.println("---------------新建列族 END-----------------");
       }
       
       /**
        * 删除列族
        */
       public void deleteColumnFamily() throws IOException{

           System.out.println("---------------删除列族 START-----------------");

           // 取得目标数据表的表名对象
           TableName tableName = TableName.valueOf("t_book");

           // 删除指定数据表中的指定列族
           admin.deleteColumn(tableName, "more".getBytes());

           System.out.println("---------------删除列族 END-----------------");
       }
       
       /**
        * 插入数据
        */
       public void insert() throws IOException{

           System.out.println("---------------插入数据 START-----------------");

           // 取得一个数据表对象
           Table table = connection.getTable(TableName.valueOf("t_book"));

           // 需要插入数据库的数据集合
           List<Put> putList = new ArrayList<Put>();

           Put put;

           // 生成数据集合
           for(int i = 0; i < 10; i++){
               put = new Put(Bytes.toBytes("row" + i));
               put.addColumn(Bytes.toBytes("base"), Bytes.toBytes("name"), Bytes.toBytes("bookName" + i));

               putList.add(put);
           }

           // 将数据集合插入到数据库
           table.put(putList);

           System.out.println("---------------插入数据 END-----------------");
       }
       
    
    public static void main(String[] args) throws IOException {
		HBaseDemo1 demo = new HBaseDemo1();
		
		//demo.deleteTable("hbase_1102");
		demo.createTable();
	}

}
