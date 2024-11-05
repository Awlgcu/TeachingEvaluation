package com.example.webdemo.Service.Impl;

import com.example.webdemo.Dao.*;
import com.example.webdemo.Entity.*;
import com.example.webdemo.Service.IAdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class AdminService implements IAdminService {
    private final EvaluationIndicatorDao eiDao = new EvaluationIndicatorDao();
    private final StudentDao studentDao = new StudentDao();
    private final TeacherDao teacherDao = new TeacherDao();
    private final AdminDao adminDao = new AdminDao();

    private final EvaluationResultDao erDao = new EvaluationResultDao();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Admin selectAdminObjBySnumber(String snumber){
        return adminDao.selectBySnumber(snumber);
    }
    /**
     * 选择所有学生
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public String SelectAllStudents() throws JsonProcessingException {
        List<Student> list = studentDao.selectAll();
        System.out.println("查到学生:"+list.size());
        String respJson = objectMapper.writeValueAsString(list);
        return respJson;
    }

    /**
     * 选择所有管理员
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public String SelectAllAdmins() throws JsonProcessingException {
        List<Admin> list = adminDao.selectAll();
        System.out.println("查到管理员数量:"+list.size());
        String respJson = objectMapper.writeValueAsString(list);
        return respJson;
    }

    /**
     * 选择所有老师
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public String SelectAllTeachers() throws JsonProcessingException {
        List<Teacher> list = teacherDao.selectAll();
        System.out.println("查到老师数量:"+list.size());
        return objectMapper.writeValueAsString(list);
    }

    /**
     * 选择所有评价指标
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public String SelectAllIndicators() throws JsonProcessingException {
        List<EvaluationIndicator> list = eiDao.selectAll();
        System.out.println("查到评价指标数量:"+list.size());
        return objectMapper.writeValueAsString(list);
    }

    /**
     * 更新学生
     * @param student
     * @return
     */
    @Override
    public String updateStudentByEntity(Student student) {
        //检验唯一性
        if (student.getSnumber().length()!=6){
            return "{\"message\":\"修改失败,学号位数不正确!\"}";
        }
        Student temp = studentDao.selectBySnumber(student.getSnumber());
        if (temp!=null&& temp.getId()!=student.getId()){
            return "{\"message\":\"修改失败,学生学号已存在!\"}";
        }
//        }
        boolean res = studentDao.updateByEntity(student);
        String respJson = null;
        if (res){
            System.out.println("修改成功");
            respJson="{\"message\":\"修改成功!\"}";
        }else{
            System.out.println("修改失败");
            respJson="{\"message\":\"修改失败!\"}";
        }
        return respJson;
    }

    /**
     * 更新管理员
     * @param admin
     * @return
     */
    @Override
    public String updateAdminByEntity(Admin admin) {
        //检验唯一性
        if (admin.getSnumber().length()!=4){
            return "{\"message\":\"修改失败,工号位数不正确!\"}";
        }
        Admin temp = adminDao.selectBySnumber(admin.getSnumber());
        if (temp!=null&& temp.getId()!=admin.getId()){
            return "{\"message\":\"修改失败,管理员工号已存在!\"}";
        }

        boolean res = adminDao.updateByEntity(admin);
        String respJson = null;
        if (res){
            System.out.println("修改成功");
            respJson="{\"message\":\"修改成功!\"}";
        }else{
            System.out.println("修改失败");
            respJson="{\"message\":\"修改失败!\"}";
        }
        return respJson;
    }

    /**
     * 更新老师
     * @param teacher
     * @return
     */
    @Override
    public String updateTeacherByEntity(Teacher teacher) {
        //检验唯一性
        if (teacher.getTnumber().length()!=6){
            return "{\"message\":\"修改失败,工号位数不正确!\"}";
        }
        Teacher temp = teacherDao.selectByTnumber(teacher.getTnumber());
        if (temp!=null&&temp.getId()!=teacher.getId()){
            return "{\"message\":\"修改失败,老师工号已存在!\"}";
        }

        boolean res = teacherDao.updateByEntity(teacher);
        String respJson = null;
        if (res){
            System.out.println("修改成功");
            respJson="{\"message\":\"修改成功!\"}";
        }else{
            System.out.println("修改失败");
            respJson="{\"message\":\"修改失败!\"}";
        }
        return respJson;
    }

    /**
     * 更新评价指标
     * @param indicator
     * @return
     */
    @Override
    public String updateIndicatorByEntity(EvaluationIndicator indicator) {

        boolean res = eiDao.updateByEntity(indicator);
        String respJson = null;
        if (res){
            System.out.println("修改成功");
            respJson="{\"message\":\"修改成功!\"}";
        }else{
            System.out.println("修改失败");
            respJson="{\"message\":\"修改失败!\"}";
        }
        return respJson;
    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    @Override
    public String deleteStudentById(Long id) {
        System.out.println("待修改学生id:"+id);
        boolean res = studentDao.deleteById(id);
        String respJson = null;
        if(res){
            System.out.println("删除成功");
            respJson="{\"message\":\"删除成功!\"}";
        }else{
            System.out.println("删除失败");
            respJson="{\"message\":\"删除失败!\"}";
        }
        return respJson;
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @Override
    public String deleteAdminById(Long id) {
        System.out.println("待修改管理员id:"+id);
        boolean res = adminDao.deleteById(id);
        String respJson = null;
        if(res){
            System.out.println("删除成功");
            respJson="{\"message\":\"删除成功!\"}";
        }else{
            System.out.println("删除失败");
            respJson="{\"message\":\"删除失败!\"}";
        }
        return respJson;
    }

    /**
     * 删除老师
     * @param id
     * @return
     */
    @Override
    public String deleteTeacherById(Long id) {
        System.out.println("待修改老师id:"+id);
        boolean res = teacherDao.deleteById(id);
        String respJson = null;
        if(res){
            System.out.println("删除成功");
            respJson="{\"message\":\"删除成功!\"}";
        }else{
            System.out.println("删除失败");
            respJson="{\"message\":\"删除失败!\"}";
        }
        return respJson;
    }

    /**
     * 删除评价指标
     * @param id
     * @return
     */
    @Override
    public String deleteIndicatorById(Long id) {

        boolean res = eiDao.deleteById(id);
        String respJson = null;
        if (!res){
            System.out.println("删除成功");
            respJson="{\"message\":\"删除成功!\"}";
        }else{
            System.out.println("添加失败");
            respJson="{\"message\":\"删除失败!\"}";
        }
        return respJson;
    }

    /**
     * 插入学生
     * @param student
     * @return
     */
    @Override
    public String insertStudentByEntity(Student student) {
        //检验唯一性
        if (student.getSnumber().length()!=6){
            return "{\"message\":\"添加失败,学号位数不正确!\"}";
        }
        Student temp = studentDao.selectBySnumber(student.getSnumber());
        if (temp!=null&& temp.getId()!=student.getId()){
            return "{\"message\":\"修改失败,学生学号已存在!\"}";
        }
        boolean res = studentDao.insertByEntity(student);
        String respJson = null;
        if(!res){
            System.out.println("插入成功");
            respJson="{\"message\":\"添加成功!\"}";
        }else{
            System.out.println("插入失败");
            respJson="{\"message\":\"添加失败!\"}";
        }
        return respJson;
    }

    /**
     * 插入管理员
     * @param admin
     * @return
     */
    @Override
    public String insertAdminByEntity(Admin admin) {
        //检验唯一性
        if (admin.getSnumber().length()!=4){
            return "{\"message\":\"添加失败,工号位数不正确!\"}";
        }
        Admin temp = adminDao.selectBySnumber(admin.getSnumber());
        if (temp!=null&& temp.getId()!=admin.getId()){
            return "{\"message\":\"修改失败,管理员工号已存在!\"}";
        }
        boolean res = adminDao.insertByEntity(admin);
        String respJson = null;
        if(!res){
            System.out.println("插入成功");
            respJson="{\"message\":\"添加成功!\"}";
        }else{
            System.out.println("插入失败");
            respJson="{\"message\":\"添加失败!\"}";
        }
        return respJson;
    }
    
    /**
     * 插入老师
     * @param teacher
     * @return
     */
    @Override
    public String insertTeacherByEntity(Teacher teacher) {
        //检验唯一性
        if (teacher.getTnumber().length()!=6){
            return "{\"message\":\"添加失败,学号位数不正确!\"}";
        }
        Teacher temp = teacherDao.selectByTnumber(teacher.getTnumber());
        if (temp!=null&& temp.getId()!=teacher.getId()){
            return "{\"message\":\"修改失败,学生学号已存在!\"}";
        }
        boolean res = teacherDao.insertByEntity(teacher);
        String respJson = null;
        if(!res){
            System.out.println("插入成功");
            respJson="{\"message\":\"添加成功!\"}";
        }else{
            System.out.println("插入失败");
            respJson="{\"message\":\"添加失败!\"}";
        }
        return respJson;
    }

    /**
     * 插入评价指标
     * @param indicator
     * @return
     */
    @Override
    public String insertIndicatorByEntity(EvaluationIndicator indicator) {

        boolean res = eiDao.insertByEntity(indicator);
        String respJson = null;
        if (!res){
            System.out.println("添加成功");
            respJson="{\"message\":\"添加成功!\"}";
        }else{
            System.out.println("添加失败");
            respJson="{\"message\":\"添加失败!\"}";
        }
        return respJson;
    }

    @Override
    public String selectAllResults() throws JsonProcessingException {
        List<EvaluationResult> list = erDao.selectAll();
        System.out.println("查到评价结果数量:"+list.size());
        return objectMapper.writeValueAsString(list);
    }

    @Override
    public String updateAdminPassword(String snumber, String oldPassword, String newPassword1,String newPassword2){
        String respJson = null;
        if (!newPassword1.equals(newPassword2)){
            respJson = "{\"message\":\"两次输入密码不一致!\"}";
            System.out.println("两次输入密码不一致!");
            return respJson;
        }
        if (!oldPassword.equals(adminDao.selectBySnumber(snumber).getPassword())){
            respJson = "{\"message\":\"旧密码错误!\"}";
            System.out.println("旧密码错误!");
            return respJson;
        }
        adminDao.updatePassword(snumber, oldPassword, newPassword1);
        System.out.println("修改成功,请重新登录");
        return "{\"message\":\"修改成功,请重新登录!\"}";
    }

    @Override
    public String selectAdminBySnumber(String snumber) throws JsonProcessingException {
        Admin admin = adminDao.selectBySnumber(snumber);
        if (admin==null){
            return "{\"message\": \"未找到该管理员\"}";
        }
        return objectMapper.writeValueAsString(admin);
    }

    /**
     * 更新管理员自己
     * @param admin
     * @return
     */
    @Override
    public String updateAdminSelfByEntity(Admin admin) {
        //检验唯一性
//        if (admin.getSnumber().length()!=4){
//            return "{\"message\":\"修改失败,工号位数不正确!\"}";
//        }
////        Admin temp = adminDao.selectBySnumber(admin.getSnumber());
//        if (temp!=null){
//            return "{\"message\":\"修改失败,管理员工号已存在!\"}";
//        }

        boolean res = adminDao.updateSelfByEntity(admin);
        String respJson = null;
        if (res){
            System.out.println("修改成功");
            respJson="{\"message\":\"修改成功!\"}";
        }else{
            System.out.println("修改失败");
            respJson="{\"message\":\"修改失败!\"}";
        }
        return respJson;

    }
}
