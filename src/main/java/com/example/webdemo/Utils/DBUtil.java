package com.example.webdemo.Utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    public static DataSource dataSource = new MysqlDataSource();
    //静态代码块,加载一次数据库驱动
    static{
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/teaching_evaluation?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("123789");
    }
    public static Connection getConnection() throws SQLException {
        return (Connection)dataSource.getConnection();
    }
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(resultSet!=null){
            try {
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
