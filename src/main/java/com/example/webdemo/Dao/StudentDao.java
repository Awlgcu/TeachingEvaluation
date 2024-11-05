package com.example.webdemo.Dao;

import com.example.webdemo.Entity.Student;
import com.example.webdemo.Utils.DBHelper;
import com.example.webdemo.Utils.DBUtil;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDao {
    DBHelper dbHelper = new DBHelper();

    public String selectClassIdBySnumber(String snumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select classId from student where snumber=?";//可以只查需返回的部分
            statement = connection.prepareStatement(sql);
            statement.setString(1, snumber);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                String result = resultSet.getString("classId");
                return result;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public List<Student> selectAll()  {
        String sql = "select * from student where isDelete=0";
        List<Student> list = null;
        try {
            list = dbHelper.selectAll(sql, Student.class);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 IntrospectionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(list.size());
        return list;
//        List<Student> list = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBUtil.getConnection();
//            String sql = "select * from student where isDelete=0";//可以只查需返回的部分
//            statement = connection.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                Student stu = new Student();
//                stu.setSnumber(resultSet.getString("snumber"));
//                stu.setId(resultSet.getLong("id"));
//                stu.setName(resultSet.getString("name"));
//                stu.setGender(resultSet.getString("gender"));
//                stu.setAge(resultSet.getInt("age"));
//                stu.setClassId(resultSet.getString("classId"));
//                stu.setPassword(resultSet.getString("password"));
//                list.add(stu);
//            }
//            return list;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            DBUtil.close(connection, statement, resultSet);
//        }
    }
    //学生没查到返回null
    public Student selectById(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from student where id= ? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Student stu = new Student();
            if (resultSet.next()){
                stu.setId(resultSet.getLong("id"));
                stu.setSnumber(resultSet.getString("snumber"));
                stu.setName(resultSet.getString("name"));
                stu.setGender(resultSet.getString("gender"));
                stu.setAge(resultSet.getInt("age"));
                stu.setClassId(resultSet.getString("classId"));
                stu.setPassword(resultSet.getString("password"));
                System.out.println("成功查询到学生:"+stu.getName());
                return stu;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public Student selectBySnumber(String snumber){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            System.out.println("后台开始查询学生");
            connection = DBUtil.getConnection();
            String sql = "select * from student where snumber = ? and isDelete=0";
            statement = connection.prepareStatement(sql);
            statement.setString(1, snumber);
            resultSet = statement.executeQuery();

            Student stu = new Student();
            if (resultSet.next()){
                stu.setId(resultSet.getLong("id"));
                stu.setSnumber(resultSet.getString("snumber"));
                stu.setName(resultSet.getString("name"));
                stu.setGender(resultSet.getString("gender"));
                stu.setAge(resultSet.getInt("age"));
                stu.setClassId(resultSet.getString("classId"));
                stu.setPassword(resultSet.getString("password"));
                System.out.println("成功查询到学生:"+stu.getName());
                return stu;
            }
            System.out.println("未查询到该学生");
            return null;
        } catch (SQLException e) {
            System.out.println("查询失败");
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }

    }

    public boolean updateByEntity(Student stu){

        stu.setUpdateTime(new Timestamp(new Date().getTime()));
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update student set snumber = ?, name=?, gender=?, age=?, password=?, classId=?, updateTime=? where id=?";
            statement = connection.prepareStatement(sql);
            System.out.println(stu.getName());
            statement.setString(1, stu.getSnumber());
            statement.setString(2, stu.getName());
            statement.setString(3, stu.getGender());
            statement.setInt(4, stu.getAge());
            statement.setString(5, stu.getPassword());
            statement.setString(6, stu.getClassId());
            statement.setTimestamp(7, stu.getUpdateTime());
            statement.setLong(8, stu.getId());
            int i = statement.executeUpdate();
            if (i>0){
                System.out.println("成功修改学生信息");
                return true;
            }else{
                System.out.println("数据库修改学生信息失败");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }

    }
    public boolean updateSelf(String snumber, String name, String age, String gender){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update student set  name=?, gender=?, age=?, updateTime=? where snumber=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setInt(3, Integer.parseInt(age));
            statement.setTimestamp(4, new Timestamp(new Date().getTime()));
            statement.setString(5, snumber);
            int i = statement.executeUpdate();
            if (i>=0){
                System.out.println("成功修改学生信息"+i);
                return true;
            }else{
                System.out.println("数据库修改学生信息失败");
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
            String sql = "update student set password = ?, updateTime=?  where snumber=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setTimestamp(2, new Timestamp(new Date().getTime()));
            statement.setString(3, snumber);
            statement.setString(4, oldPassword);
            statement.executeUpdate();
            System.out.println("成功修改学生密码");
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
            String sql = "update student set IsDelete=1, updateTime=? where snumber=?";
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, new Timestamp(new Date().getTime()));
            statement.setString(2, snumber);
            statement.executeUpdate();
            System.out.println("成功删除学生");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
    public boolean insertByEntity(Student stu){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into student (snumber, name, age, gender, classId, password, createTime, updateTime) values (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, stu.getSnumber());
            statement.setString(2, stu.getName());
            statement.setInt(3, stu.getAge());
            statement.setString(4, stu.getGender());
            statement.setString(5, stu.getClassId());
            statement.setString(6, stu.getPassword());
            statement.setTimestamp(7, new Timestamp(new Date().getTime()));
            statement.setTimestamp(8, new Timestamp(new Date().getTime()));

            boolean execute = statement.execute();
            System.out.println("成功插入学生");
            return execute;

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
            System.out.println("进入修改后台");
            connection = DBUtil.getConnection();
            String sql = "update student set isDelete=1 where id = ? and isDelete = 0";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            int i = statement.executeUpdate();
            System.out.println("执行语句成功");
            if (i>0){
                System.out.println("后台修改成功");
                return true;
            }else {
                System.out.println("后台修改失败");
                return false;
            }
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
            String sql = "select * from student where snumber = ? and isDelete = 0";
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
