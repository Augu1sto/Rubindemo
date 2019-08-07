package com.rubin.demo.Mapper;

import com.rubin.demo.Entity.Admin;
import com.rubin.demo.Entity.Rubbish;
import com.rubin.demo.Entity.Useradd;
import com.rubin.demo.Entity.Useredit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RubMapper {
    //****************************管理员登陆注册
    //管理员注册
    @Insert("INSERT INTO admin(admname,admpass,admdate) values(#{admname},md5(#{admpass}),current_date())")
    Integer addAdmin(String admname, String admpass);
    //管理员登录
    @Select("SELECT * FROM admin\n"+
            "WHERE admname=#{admname} and admpass=md5(#{admpass})")
    Admin adminLogin(String admname, String admpass);
    //****************************管理员对垃圾条目增删查改
    //获得可回收垃圾列表
    @Select("SELECT * FROM rubbish\n"+
            "WHERE rubcate='可回收垃圾'")
    List<Rubbish> getAllRecycRub();
    //获得干垃圾列表
    @Select("SELECT * FROM rubbish\n"+
            "WHERE rubcate='干垃圾'")
    List<Rubbish> getAllResidRub();
    //获得可回收垃圾列表
    @Select("SELECT * FROM rubbish\n"+
            "WHERE rubcate='湿垃圾'")
    List<Rubbish> getAllHouseholdRub();
    //获得可回收垃圾列表
    @Select("SELECT * FROM rubbish\n"+
            "WHERE rubcate='有害垃圾'")
    List<Rubbish> getAllHazarRub();
    //添加垃圾
    @Insert("INSERT INTO rubbish(rubname,rubcate,rubimg,rubloc,rubdetail,adddate) VALUES(#{rubname},#{rubcate},#{rubimg},#{rubloc},#{rubdetail},current_date());")
    Integer addRubbish(String rubname,String rubcate,String rubimg,String rubloc,String rubdetail);
    //删除垃圾信息
    @Delete("DELETE FROM rubbish WHERE rubid=#{id}")
    Integer deleteByID(Integer id);
    //修改垃圾信息
    @Update("UPDATE rubbish SET rubcate=#{rubcate},rubimg=#{rubimg},rubdetail=#{rubdetail}\n"+
            "WHERE rubid=#{rubid}")
    Integer updateRubbish(String rubcate,String rubimg,String rubdetail,String rubid);
    //****************************管理员对用户添加的垃圾条目审核
    //用户添加垃圾列表
    @Select("SELECT * FROM useradd")
    List<Useradd> getAllUserAdd();
    //获得所有未审核的垃圾记录数目
    @Select("SELECT count(*) FROM useradd WHERE add_stat='未审核'")
    Integer getAddListCount();
    //删除用户添加垃圾的记录条数
    @Delete("DELETE FROM useradd WHERE add_id=#{id}")
    Integer deleteByAddID(Integer id);
    //置添加审核标记
    @Update("UPDATE useradd SET add_stat=#{sign}\n"+
            "WHERE add_id=#{id}")
    Integer passAddSign(Integer id,String sign);
    //通过ID查找
    @Select("SELECT * FROM useradd WHERE add_id=#{id}")
    Useradd getUseraddByID(Integer id);
    //****************************管理员对用户修改的垃圾条目审核
    //用户修改垃圾列表
    @Select("SELECT * FROM useredit")
    List<Useredit> getAllUserEdit();
    //获得所有未审核的垃圾记录数目
    @Select("SELECT count(*) FROM useredit WHERE e_stat='未审核'")
    Integer getEditListCount();
    //删除用户修改垃圾的记录条数
    @Delete("DELETE FROM useredit WHERE eid=#{id}")
    Integer deleteByEditID(Integer id);
    //置修改审核标记
    @Update("UPDATE useredit SET e_stat=#{sign}\n"+
            "WHERE eid=#{id}")
    Integer passEditSign(Integer id,String sign);
    //通过ID查找
    @Select("SELECT * FROM useredit WHERE eid=#{id}")
    Useredit getUsereditByID(Integer id);

    //*******************************主页
    //搜索功能
    @Select("SELECT * FROM rubbish WHERE rubname=#{rubname} and rubloc=#{rubloc}")
    Rubbish searchRub(String rubname, String rubloc);
    //用户添加词条
    @Insert("INSERT INTO useradd(add_name,add_cate,add_img,add_loc,add_detail,add_date) VALUES(#{add_name},#{add_cate},#{add_img},#{add_loc},#{add_detail},current_date())")
    Integer userAddRub(Useradd useradd);
    //用户修改词条
    @Insert("INSERT INTO useredit(e_rid,ename,ecate,eimg,eloc,edetail,edate) VALUES(#{e_rid},#{ename},#{ecate},#{eimg},#{eloc},#{edetail},current_date())")
    Integer userEditRub(Useredit useredit);

}
