package com.example.webdemo.Dao;

import com.example.webdemo.Entity.EvaluationIndicator;
import com.example.webdemo.Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluationIndicatorDao {
    public List<EvaluationIndicator> selectAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EvaluationIndicator> list = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from evaluationindicators where isDelete = 0";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                EvaluationIndicator ei = new EvaluationIndicator();
                ei.setId(resultSet.getLong("id"));
                ei.setIndicator(resultSet.getString("indicator"));
                list.add(ei);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean deleteById(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update evaluationindicators set isDelete=1 where id=?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public EvaluationIndicator selectById(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from evaluationindicators where id=? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            EvaluationIndicator evIndicator = new EvaluationIndicator();
            if(resultSet.next()){
                evIndicator.setId(resultSet.getLong("id"));
                evIndicator.setIndicator(resultSet.getString("indicator"));
                return evIndicator;
            }
            System.out.println("未查询到评价指标");
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public void deleteAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update evaluationindicators set isDelete=1";
            statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("成功删除所有指标");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean updateByEntity(EvaluationIndicator indicator){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update evaluationindicators set indicator=?,  updateTime=?  where id=?";
            statement = connection.prepareStatement(sql);
            System.out.println(indicator.getIndicator());
            System.out.println(new Timestamp(new Date().getTime()));
            System.out.println(indicator.getId());
            statement.setString(1, indicator.getIndicator());
            statement.setTimestamp(2, new Timestamp(new Date().getTime()));
            statement.setLong(3, indicator.getId());

            int i = statement.executeUpdate();

            if (i>0){
                System.out.println("后台修改评价指标成功");
                return true;
            }else{
                System.out.println("后台修改评价指标失败");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean insertByEntity(EvaluationIndicator evIndicator){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into evaluationindicators (indicator, createTime, updateTime) values (?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, evIndicator.getIndicator());
            statement.setTimestamp(2, new Timestamp(new Date().getTime()));
            statement.setTimestamp(3, new Timestamp(new Date().getTime()));
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
}
