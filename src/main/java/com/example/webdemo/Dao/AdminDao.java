package com.example.webdemo.Dao;

import com.example.webdemo.Entity.Admin;
import com.example.webdemo.Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDao {

    public List<Admin> selectAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Admin> list = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from admin where isDelete=0";//可以只查需返回的部分
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setSnumber(resultSet.getString("snumber"));
                admin.setName(resultSet.getString("name"));
                admin.setGender(resultSet.getString("gender"));
                admin.setAge(resultSet.getInt("age"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setPassword(resultSet.getString("password"));
                list.add(admin);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    //管理员没查到返回null
    public Admin selectById(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from admin where id= ? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Admin admin = new Admin();
            if (resultSet.next()){
                admin.setId(resultSet.getLong("id"));
                admin.setSnumber(resultSet.getString("snumber"));
                admin.setName(resultSet.getString("name"));
                admin.setGender(resultSet.getString("gender"));
                admin.setAge(resultSet.getInt("age"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setPassword(resultSet.getString("password"));
                System.out.println("成功查询到管理员:"+admin.getName());
                return admin;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }

    }
    public Admin selectBySnumber(String snumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from admin where snumber = ? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setString(1, snumber);
            resultSet = statement.executeQuery();

            Admin admin = new Admin();
            if (resultSet.next()){
                admin.setId(resultSet.getLong("id"));
                admin.setSnumber(resultSet.getString("snumber"));
                admin.setName(resultSet.getString("name"));
                admin.setGender(resultSet.getString("gender"));
                admin.setAge(resultSet.getInt("age"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setPassword(resultSet.getString("password"));
                System.out.println("成功查询到管理员:"+admin.getName());
                return admin;
            }
            System.out.println("未查询到该管理员");
            return null;
        } catch (SQLException e) {
            System.out.println("查询失败");
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }

    }

    public boolean updateByEntity(Admin admin){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        admin.setUpdateTime(new Timestamp(new java.util.Date().getTime()));

        try {
            connection = DBUtil.getConnection();
            String sql = "update admin set snumber = ?, name=?, gender=?, age=?, password=?, phone=?, updateTime=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, admin.getSnumber());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getGender());
            statement.setInt(4, admin.getAge());
            statement.setString(5, admin.getPassword());
            statement.setString(6, admin.getPhone());
            statement.setTimestamp(7, admin.getUpdateTime());
            statement.setLong(8, admin.getId());
            int i = statement.executeUpdate();
            if (i>0){
                System.out.println("后台管理员修改成功");
                return true;
            }else{
                System.out.println("后台管理员修改失败");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean updateSelfByEntity(Admin admin){
        admin.setUpdateTime(new Timestamp(new java.util.Date().getTime()));

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update admin set snumber = ?, name=?, gender=?, age=?, password=?, phone=?, updateTime=? where snumber=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, admin.getSnumber());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getGender());
            statement.setInt(4, admin.getAge());
            statement.setString(5, admin.getPassword());
            statement.setString(6, admin.getPhone());
            statement.setTimestamp(7, admin.getUpdateTime());
            statement.setString(8, admin.getSnumber());
            int i = statement.executeUpdate();
            if (i>0){
                System.out.println("后台管理员修改成功");
                return true;
            }else{
                System.out.println("后台管理员修改失败");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    //这个方法仅用户自己更改密码时调用
    public void updatePassword(String snumber, String oldPassword, String newPassword){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update admin set password = ?, updateTime=?  where snumber=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
            statement.setString(3, snumber);
            statement.setString(4, oldPassword);
            statement.executeUpdate();
            System.out.println("成功修改管理员密码");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public void deleteBySnumber(String snumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {

            connection = DBUtil.getConnection();
            String sql = "update admin set IsDelete=1, updateTime=? where snumber=?";
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
            statement.setString(2, snumber);
            statement.executeUpdate();
            System.out.println("成功删除管理员");
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
            String sql = "update admin set IsDelete=1, updateTime=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
            statement.setLong(2, id);
            int i = statement.executeUpdate();
            if (i>0){
                System.out.println("后台管理员删除成功");
                return true;
            }else{
                System.out.println("后台管理员删除失败");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean insertByEntity(Admin admin){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into admin (snumber, name, age, gender, phone, password, createTime, updateTime) values (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, admin.getSnumber());
            statement.setString(2, admin.getName());
            statement.setInt(3, admin.getAge());
            statement.setString(4, admin.getGender());
            statement.setString(5, admin.getPhone());
            statement.setString(6, admin.getPassword());
            statement.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
            statement.setTimestamp(8, new Timestamp(new Date().getTime()));

            return statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean exsits(String snumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (snumber==null) return false;
            connection = DBUtil.getConnection();
            String sql = "select * from admin where snumber = ? and isDelete = 0";
            statement = connection.prepareStatement(sql);
            statement.setString(1, snumber);
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
