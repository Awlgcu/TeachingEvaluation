package com.example.webdemo.Dao;

import com.example.webdemo.Entity.EvaluationResult;
import com.example.webdemo.Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluationResultDao {

    //为了防止重复插入,要对比学生号,老师号,还有id
    public void insertByEntity(EvaluationResult evaluationResult){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "insert into evaluationresults (snumber, sname, tnumber,tname, results, indicatorId, createTime, updateTime) values (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, evaluationResult.getSnumber());
            statement.setString(2, evaluationResult.getSname());

            statement.setString(3, evaluationResult.getTnumber());
            statement.setString(4, evaluationResult.getTname());
            statement.setString(5, evaluationResult.getResults());
            statement.setLong(6, evaluationResult.getIndicatorId());

            //插入timestamp类型到后端!!!
            statement.setTimestamp(7, new Timestamp(new Date().getTime()));
            statement.setTimestamp(8, new Timestamp(new Date().getTime()));
            statement.execute();
            System.out.println("成功插入一条结果");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public void deleteById(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "update evaluationresults set isDelete=1 where id=?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("成功删除一条结果");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public void deleteByTnumber(String tnumber){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update evaluationresults set isDelete=1 where tnumber=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tnumber);
            statement.executeUpdate();
            System.out.println("成功该老师的评价结果");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public List<EvaluationResult> selectByTnumber(String tnumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from evaluationresults where tnumber = ? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tnumber);
            resultSet = statement.executeQuery();

            List<EvaluationResult> list = new ArrayList<>();
            while (resultSet.next()){
                EvaluationResult result = new EvaluationResult();
                result.setId(resultSet.getLong("id"));
                result.setSnumber(resultSet.getString("snumber"));
                result.setTnumber(resultSet.getString("tnumber"));
                result.setSname(resultSet.getString("sname"));
                result.setTname(resultSet.getString("tname"));
                result.setIndicatorId(resultSet.getLong("indicatorId"));
                result.setResults(resultSet.getString("results"));
                list.add(result);
            }
            System.out.println("查询到"+list.size()+"条评价结果");
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public List<EvaluationResult> selectBySandT(String snumber, String tnumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from evaluationresults where snumber = ? and tnumber=? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setString(1, snumber);
            statement.setString(2, tnumber);
            resultSet = statement.executeQuery();

            List<EvaluationResult> list = new ArrayList<>();
            while (resultSet.next()){
                EvaluationResult result = new EvaluationResult();
                result.setId(resultSet.getLong("id"));
                result.setSnumber(resultSet.getString("snumber"));
                result.setTnumber(resultSet.getString("tnumber"));
                result.setSname(resultSet.getString("sname"));
                result.setTname(resultSet.getString("tname"));
                result.setIndicatorId(resultSet.getLong("indicatorId"));
                result.setResults(resultSet.getString("results"));
                list.add(result);
            }
            System.out.println("查询到"+list.size()+"条评价结果");
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public List<EvaluationResult> selectAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from evaluationresults where isDelete=0";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<EvaluationResult> list = new ArrayList<>();
            while (resultSet.next()){
                EvaluationResult result = new EvaluationResult();
                result.setId(resultSet.getLong("id"));
                result.setSnumber(resultSet.getString("snumber"));
                result.setTnumber(resultSet.getString("tnumber"));
                result.setSname(resultSet.getString("sname"));
                result.setTname(resultSet.getString("tname"));
                result.setIndicatorId(resultSet.getLong("indicatorId"));
                result.setResults(resultSet.getString("results"));
                list.add(result);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public EvaluationResult selectById(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from evaluationresults where id=? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            EvaluationResult result = new EvaluationResult();
            if (resultSet.next()){
                result.setId(resultSet.getLong("id"));
                result.setSnumber(resultSet.getString("snumber"));
                result.setTnumber(resultSet.getString("tnumber"));
                result.setSname(resultSet.getString("sname"));
                result.setTname(resultSet.getString("tname"));
                result.setIndicatorId(resultSet.getLong("indicatorId"));
                result.setResults(resultSet.getString("results"));
                return result;
            }
            System.out.println("查询到评价结果");
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
}
