package com.example.webdemo.Utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private PreparedStatement createPreparedStatement(Connection connection, String sql, Object[] params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for(int i=0;i< params.length;i++){
            statement.setObject(i+1, params[i]);
        }
        return statement;
    }
    public <T> List<T> selectAll(String sql, Class<T> clazz, Object... params) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IntrospectionException {
        List<T> list = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBUtil.getConnection();
//            statement = connection.prepareStatement(sql);
//            System.out.println(params.length);
//            for(int i=0;i< params.length;i++){
//                statement.setObject(i+1, params[i]);
//            }
//            resultSet = statement.executeQuery();
//            while (resultSet.next()){
//                //1.现在创建了一个实体
////                T entity = clazz.getDeclaredConstructor().newInstance();
//                //2.封装实体的属性
//                T entity = getObject(resultSet, clazz);
//                //3.把实体放进集合
//                list.add(entity);
//            }
//            return list;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBUtil.close(connection, statement, resultSet);
//        }
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = createPreparedStatement(connection, sql, params);
            ResultSet resultSet = statement.executeQuery();
        ){
            while (resultSet.next()) {
                //1.现在创建了一个实体
//                T entity = clazz.getDeclaredConstructor().newInstance();
                //2.封装实体的属性
                T entity = getObject(resultSet, clazz);
                //3.把实体放进集合
                list.add(entity);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public <T> T selectOne(String sql, Class<T> tClass, Object...objects) throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException, NoSuchMethodException {
        try (Connection connection = DBUtil.getConnection();
            PreparedStatement statement = createPreparedStatement(connection, sql, objects);
            ResultSet resultSet = statement.executeQuery();
        ){
            //1.创建一个实体
            T entity = tClass.getDeclaredConstructor().newInstance();
            //2.从结果集拿到结果并将其放进对象
            if (resultSet.next()) {
                entity = getObject(resultSet, tClass);
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> T getObject(ResultSet resultSet, Class<T> tClass) throws InstantiationException, IllegalAccessException, SQLException, IntrospectionException, InvocationTargetException {
        T object = tClass.newInstance();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//        for (int i = 0; i < resultSetMetaData.getColumnCount();i++){
//            System.out.println(resultSetMetaData.getColumnName(i+1));
//        }
        for (int i = 0; i < resultSetMetaData.getColumnCount()-3; i++) {
            //获取列名
            String colName = resultSetMetaData.getColumnName(i + 1);
            //通过内省获取列名对应的方法,比反射方便(反射只能根据方法名拿,内省专门拿getter, setter)
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(colName, tClass);
            if (propertyDescriptor != null) {
                //拿到set方法
                Method method = propertyDescriptor.getWriteMethod();
                //通过反射执行方法
                method.invoke(object, resultSet.getObject(colName));
            }
        }
        return object;
    }
    public int update(String sql, Object... objects){
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBUtil.getConnection();
//            statement = connection.prepareStatement(sql);
//            for(int i=0;i<objects.length;i++){
//                statement.setObject(i+1, objects[i]);
//            }
//            int res = statement.executeUpdate();
//
//            System.out.println(res);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            DBUtil.close(connection, statement, resultSet);
//        }
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, sql, objects);){
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean insert(String sql, Object...objects){
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, sql, objects);){
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int delete(String sql, Object...objects){

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, sql, objects);){
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
