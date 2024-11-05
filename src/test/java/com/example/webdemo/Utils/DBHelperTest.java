package com.example.webdemo.Utils;

import com.example.webdemo.Entity.Admin;
import com.example.webdemo.Entity.Student;
import com.example.webdemo.Entity.User;
import com.example.webdemo.Hello;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DBHelperTest {
    DBHelper dbHelper = new DBHelper();
    @Test
    void selectAll() throws IntrospectionException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String sql = "select * from student where isDelete=0";
        List<Student> list = dbHelper.selectAll(sql, Student.class);
        System.out.println(list.size());
    }
    @Test
    void update(){
        String sql = "update student set age=? where snumber=?";
        dbHelper.update(sql,  23, "123456");
    }
    @Test
    void selectOne() throws IntrospectionException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from student where snumber=?";
        Student student = dbHelper.selectOne(sql, Student.class, "123456");
        if (student!=null){
            System.out.println("学号:"+student.getSnumber()+"姓名:"+student.getName());
        }else{
            System.out.println("出错了");
        }
    }
    @Test
    void selectOneAdmin() throws IntrospectionException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from admin where snumber=?";
        Admin admin = dbHelper.selectOne(sql, Admin.class, "1234");
        if (admin!=null){
            System.out.println("工号:"+admin.getSnumber()+"姓名:"+admin.getName());
        }else{
            System.out.println("出错了");
        }
    }
    @Test
    void insert(){
        String sql = "insert into student (snumber, name, age, gender, classId, password, createTime, updateTime) values(?, ?, ?, ?, ?, ?, ?, ?)";
        boolean insert = dbHelper.insert(sql, "123111", "newtest", 22, "male", "1", "123", new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        System.out.println(insert);
    }

    @Test
    void delete(){
        String sql = "update student set isDelete=1 where snumber=?";
        int delete = dbHelper.delete(sql, "123111");
        System.out.println(delete);
    }
    @Test
    void classTest() throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<User> clazz = User.class;
        System.out.println(clazz.getName());
        //1.拿到所有的属性
//        Field[] declaredFields = clazz.getDeclaredFields();
//        for(Field field: declaredFields){
//            System.out.println(field);
//        }

        //2.通过属性去拿到所有的具体值
//        Field field = clazz.getDeclaredField("name");
        User user = new User();
        user.setName("Rick");
//        Class<?> type = field.getType();
//        System.out.println("该字段类型:"+type);
//        field.setAccessible(true);
//            //private字段不能通过get调用该字段的反射去拿到值,可以改为public,也可以field.setAccessible(true)可以通过get方法
//        Object o = field.get(user);
//        System.out.println(o);

        //3.通过拿到某个属性去修改对象的这个属性值
//        field.set(user, "RickRuo");
//        System.out.println(user.getName());

        //4.拿到某个类的所有方法
//        Method[] declaredMethods = clazz.getDeclaredMethods();
//        for (Method method: declaredMethods){
//            System.out.println(method);
//        }

//        try {
//            //5.拿一个方法试试水
//            Method method = clazz.getDeclaredMethod("getName");
//            System.out.println(method);
//            Object res = method.invoke(user);
//            System.out.println(res);
//
//            //6.静态方法,静态方法不用传具体对象来invoke
//            Method staticMethod = clazz.getDeclaredMethod("staticMethod");
//            staticMethod.invoke(null);
//
//            //7.调用非public方法,需要method.setAccessible(true);
//
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }

        //8.试试构造方法,直接拿到构造方法,要求必须有public无参构造
//        User user1 = clazz.newInstance();
//        user1.setName("lwb");
//        System.out.println(user1.getName());

        //9.获取所有构造方法,非public的构造方法还是需要setAccessible(true)
//        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
//        for (Constructor Constructor: declaredConstructors){
//            System.out.println(Constructor);
//        }

        //10.使用带参的构造方法
//        Constructor<User> constructor = clazz.getDeclaredConstructor(String.class, String.class);
//        User user2 = constructor.newInstance("000000", "haha");
//        System.out.println(user2.getName());

        //11.通过class文件获取父类的class,除了Object类,其余类或接口一定都存在父class
        Class<Integer> integerClass = Integer.class;
        Class<? super Integer> superclass = integerClass.getSuperclass();
        System.out.println(superclass);

        //12.拿到一个类实现的接口(及所有接口)
        Class<?>[] interfaces = integerClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
        //13.拿到方法上的注解......
    }

    @Test
    public void proxyTest(){
        InvocationHandler handler = new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object...objects){
                System.out.println("方法名:"+method);
                if ("morning".equals(method.getName())){
                    System.out.println("GoodMorning!"+objects[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello)Proxy.newProxyInstance(
                Hello.class.getClassLoader(),//接口的class文件的loader
                new Class[]{Hello.class},//要实现的接口的字节码文件数组
                handler//handler,完成方法的handler
        );
        hello.morning("Rick");
    }

    @Test
    void testOptional(){
        User user = new User("123123", "lwb");
        Optional<User> optionalUser = Optional.ofNullable(null);
        optionalUser.ifPresent(u-> System.out.println(u.getName()));
//        System.out.println(optionalUser.orElse(user).getName());;
        System.out.println(optionalUser.orElseGet(()->new User("333222", "rick")).getName());
    }
}