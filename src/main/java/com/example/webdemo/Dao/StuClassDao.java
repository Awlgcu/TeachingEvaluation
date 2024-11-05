package com.example.webdemo.Dao;

import com.example.webdemo.Entity.Teacher;
import com.example.webdemo.Utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuClassDao {
    TeacherDao teacherDao = new TeacherDao();
    public List<Teacher> selectTeachersByClassId(String classId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Teacher> teacherList = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select ChineseTeacher,MathTeacher,EnglishTeacher from class where classId= ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, classId);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                Teacher t1 = teacherDao.selectByTnumber(resultSet.getString("ChineseTeacher"));
                Teacher t2 = teacherDao.selectByTnumber(resultSet.getString("MathTeacher"));
                Teacher t3 = teacherDao.selectByTnumber(resultSet.getString("EnglishTeacher"));
                teacherList.add(t1);
                teacherList.add(t2);
                teacherList.add(t3);
            }
            System.out.println("共查到"+teacherList.size()+"名老师");
            return teacherList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }
}
