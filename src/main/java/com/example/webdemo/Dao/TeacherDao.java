package com.example.webdemo.Dao;

import com.example.webdemo.Entity.Teacher;

import com.example.webdemo.Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherDao {
    public List<Teacher> selectAll(){
        List<Teacher> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from teacher where isDelete=0";//可以只查需返回的部分
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                Teacher teacher = new Teacher();
                teacher.setTnumber(resultSet.getString("tnumber"));
                teacher.setId(resultSet.getLong("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setGender(resultSet.getString("gender"));
                teacher.setAge(resultSet.getInt("age"));
                teacher.setPhone(resultSet.getString("phone"));
                teacher.setIntroduction(resultSet.getString("introduction"));
                list.add(teacher);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    //老师没查到返回null
    public Teacher selectById(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from teacher where id= ? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Teacher teacher = new Teacher();
            if (resultSet.next()){
                teacher.setId(resultSet.getLong("id"));
                teacher.setTnumber(resultSet.getString("tnumber"));
                teacher.setName(resultSet.getString("name"));
                teacher.setGender(resultSet.getString("gender"));
                teacher.setAge(resultSet.getInt("age"));
                System.out.println("成功查询到老师:"+teacher.getName());
                return teacher;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }

    }
    public Teacher selectByTnumber(String tnumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {

            connection = DBUtil.getConnection();
            String sql = "select * from teacher where tnumber = ? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tnumber);
            resultSet = statement.executeQuery();

            Teacher teacher = new Teacher();
            if (resultSet.next()){
                teacher.setId(resultSet.getLong("id"));
                teacher.setTnumber(resultSet.getString("tnumber"));
                teacher.setName(resultSet.getString("name"));
                teacher.setGender(resultSet.getString("gender"));
                teacher.setAge(resultSet.getInt("age"));
                System.out.println("成功查询到老师:"+teacher.getName());
                return teacher;
            }
            System.out.println("未查询到该老师");
            return null;
        } catch (SQLException e) {
            System.out.println("查询失败");
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }

    }
    public boolean updateByEntity(Teacher teacher){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        teacher.setUpdateTime(new Timestamp(new java.util.Date().getTime()));

        try {
            connection = DBUtil.getConnection();
            String sql = "update teacher set tnumber = ?, name=?, gender=?, age=? , phone=?, introduction=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, teacher.getTnumber());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getGender());
            statement.setInt(4, teacher.getAge());
            statement.setString(5, teacher.getPhone());
            statement.setString(6, teacher.getIntroduction());
            statement.setLong(7, teacher.getId());
            System.out.println("执行语句前");
            int i = statement.executeUpdate();
            System.out.println("执行语句后");
            if (i>0){
                System.out.println("后台成功修改老师信息");
                return true;
            }
            System.out.println("后台修改老师信息失败");
            return false;

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
            String sql = "update teacher set IsDelete=1, updateTime=? where tnumber=?";
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
            statement.setString(2, tnumber);
            statement.executeUpdate();
            System.out.println("成功删除老师");
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
            String sql = "update teacher set IsDelete=1, updateTime=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
            statement.setLong(2, id);
            int i = statement.executeUpdate();
            if (i>0){
                System.out.println("后台删除老师成功");
                return true;
            }else{
                System.out.println("后台删除老师失败");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean insertByEntity(Teacher teacher){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into teacher (tnumber, name, age, gender,phone, introduction, createTime, updateTime) values (?, ?,?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, teacher.getTnumber());
            statement.setString(2, teacher.getName());
            statement.setInt(3, teacher.getAge());
            statement.setString(4, teacher.getGender());
            statement.setString(5, teacher.getPhone());
            statement.setString(6, teacher.getIntroduction());
            statement.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
            statement.setTimestamp(8, new Timestamp(new Date().getTime()));

            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean exsits(String tnumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (tnumber==null) return false;

            connection = DBUtil.getConnection();
            String sql = "select * from teacher where tnumber = ? and isDelete = 0";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tnumber);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return false;
    }
}
