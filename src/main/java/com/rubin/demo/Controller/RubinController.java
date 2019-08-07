package com.rubin.demo.Controller;


import com.rubin.demo.Entity.Admin;
import com.rubin.demo.Entity.Rubbish;
import com.rubin.demo.Entity.Useradd;
import com.rubin.demo.Entity.Useredit;
import com.rubin.demo.Mapper.RubMapper;
import com.rubin.demo.Utils.FileUtil;
import com.rubin.demo.Utils.ImageRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("rub")
public class RubinController {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RubMapper rubMapper;

    @GetMapping("/login")
    public String getLoginPage(HttpSession session){
        if (session.getAttribute("adm")!=null){
            session.invalidate();//退出
        }
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @GetMapping("/welcome")
    public String getWelcomePage(Model model, HttpSession session){
        if (session.getAttribute("adm")==null){
            return "redirect:/rub/login";
        }

        //未审核添加提示
        Integer n = rubMapper.getAddListCount();
        model.addAttribute("countadd",n);
        //未审核修改提示
        Integer m = rubMapper.getEditListCount();
        model.addAttribute("countedit",m);

        return "welcome";
    }

    @GetMapping("/manage")
    public String getManagePage(Model model, HttpSession session){
        if (session.getAttribute("adm")==null){
            return "redirect:/rub/login";
        }

        List<Rubbish> recycLists=rubMapper.getAllRecycRub();
        model.addAttribute("recycLists",recycLists);

        List<Rubbish> residLists=rubMapper.getAllResidRub();
        model.addAttribute("residLists",residLists);

        List<Rubbish> householdLists=rubMapper.getAllHouseholdRub();
        model.addAttribute("householdLists",householdLists);

        List<Rubbish> hazarLists=rubMapper.getAllHazarRub();
        model.addAttribute("hazarLists",hazarLists);

        //未审核添加提示
        Integer n = rubMapper.getAddListCount();
        model.addAttribute("countadd",n);
        //未审核修改提示
        Integer m = rubMapper.getEditListCount();
        model.addAttribute("countedit",m);

        return "manage";
    }

    @GetMapping("/checkadd")
    public String getCheckaddPage(Model model, HttpSession session){
        if (session.getAttribute("adm")==null){
            return "redirect:/rub/login";
        }
        List<Useradd> addLists=rubMapper.getAllUserAdd();
        model.addAttribute("addLists",addLists);

        //未审核添加提示
        Integer n = rubMapper.getAddListCount();
        model.addAttribute("countadd",n);
        //未审核修改提示
        Integer m = rubMapper.getEditListCount();
        model.addAttribute("countedit",m);


        return "checkadd";
    }

    @GetMapping("/checkedit")
    public String getCheckeditPage(Model model, HttpSession session){
        if (session.getAttribute("adm")==null){
            return "redirect:/rub/login";
        }

        List<Useredit> editLists=rubMapper.getAllUserEdit();
        model.addAttribute("editLists",editLists);

        //未审核添加提示
        Integer n = rubMapper.getAddListCount();
        model.addAttribute("countadd",n);
        //未审核修改提示
        Integer m = rubMapper.getEditListCount();
        model.addAttribute("countedit",m);

        return "checkedit";
    }


    // 登录
    @PostMapping("/login")
    public String adminLogin(@RequestParam("admname") String admname,
                             @RequestParam("admpass") String admpass,
                             Map<String, Object> map,
                             HttpSession session){

        Admin admin = rubMapper.adminLogin(admname,admpass);
        if(admin == null){
            map.put("ac","用户名或密码错误，请重试!");
            return "login";
        }else{
            session.setAttribute("adm",admin);
            return "redirect:/rub/welcome";
        }
    }
    //注册
    @PostMapping("/signup")
    public String addAdmin(@RequestParam("admname") String admname,
                           @RequestParam("admpass") String admpass,
                           Map<String, Object> map){
        try{
            Integer i = rubMapper.addAdmin(admname,admpass);

        }catch (Exception e){
            map.put("alarm", "注册失败，请更换用户名重试！");
            return "signup";
        }
        map.put("alarm2", "注册成功，已跳转到登录界面！");
        return "login";

    }
    //管理员添加垃圾
    @PostMapping("/manage")
    public String addRubbish(@RequestParam("file1") MultipartFile file, String rubname, String rubcate, String rubimg, String rubloc, String rubdetail){
        //TODO:图片过大错误处理1048576 bytes=1M
        if (file!=null) {

            String fileName = file.getOriginalFilename();//图片名称
            String filePath = FileUtil.getUpLoadFilePath();
            fileName = System.currentTimeMillis() + fileName;
            try {
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                //TODO: handel exception
            }
            rubimg = fileName;
        }
        Integer i = rubMapper.addRubbish(rubname,rubcate,rubimg,rubloc,rubdetail);
        return "redirect:/rub/manage";
    }
    //管理员删除垃圾
    @GetMapping("/del/{id}")
    public String deleteByID(@PathVariable("id") Integer rubid){
        Integer i = rubMapper.deleteByID(rubid);
        return "redirect:/rub/manage";
    }
    //管理员修改垃圾
    @PostMapping("/update")
    public String updateRubbish(@RequestParam("file2") MultipartFile file, String rubcate,String rubimg,String rubdetail,String rubid){
        if (file!=null){
            String fileName = file.getOriginalFilename();//图片名称
            String filePath = FileUtil.getUpLoadFilePath();
            fileName = System.currentTimeMillis()+fileName;
            try{
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            }catch (Exception e){
                //TODO: handel exception
            }
            rubimg = fileName;
        }

        Integer i = rubMapper.updateRubbish(rubcate,rubimg,rubdetail,rubid);
        return "redirect:/rub/manage";
    }
    //删除添加垃圾的记录
    @GetMapping("/deladd/{id}")
    public String deleteByAddID(@PathVariable("id") Integer add_id){
        Integer i = rubMapper.deleteByAddID(add_id);
        return "redirect:/rub/checkadd";
    }
    //添加审核通过
    @GetMapping("/passadd/{id}")
    public String passAdd(@PathVariable("id") Integer add_id){
        Integer i = rubMapper.passAddSign(add_id,"审核通过");
        Useradd record = rubMapper.getUseraddByID(add_id);
        Integer j = rubMapper.addRubbish(record.getAdd_name(),record.getAdd_cate(),record.getAdd_img(),record.getAdd_loc(),record.getAdd_detail());
        return "redirect:/rub/checkadd";
    }
    //添加审核不通过
    @GetMapping("/nopassadd/{id}")
    public String nopassAdd(@PathVariable("id") Integer add_id){
        Integer i = rubMapper.passAddSign(add_id,"审核不通过");
        return "redirect:/rub/checkadd";

    }
    //删除修改垃圾的记录
    @GetMapping("/deledit/{id}")
    public String deleteByEditID(@PathVariable("id") Integer e_id){
        Integer i = rubMapper.deleteByEditID(e_id);
        return "redirect:/rub/checkedit";
    }
    //添加审核通过
    @GetMapping("/passedit/{id}")
    public String passEdit(@PathVariable("id") Integer eid){
        Integer i = rubMapper.passEditSign(eid,"审核通过");
        Useredit record = rubMapper.getUsereditByID(eid);
        Integer j = rubMapper.updateRubbish(record.getEcate(),record.getEimg(),record.getEdetail(),record.getE_rid());
        return "redirect:/rub/checkedit";
    }
    //添加审核不通过
    @GetMapping("/nopassedit/{id}")
    public String nopassEdit(@PathVariable("id") Integer eid){
        Integer i = rubMapper.passEditSign(eid,"审核不通过");
        return "redirect:/rub/checkedit";

    }

    //****************************主页操作
    @GetMapping("/index")
    public String getIndexPage(){
        return "index";
    }

    @PostMapping("/index")
    public String searchRub(Model model, @RequestParam(value = "spic",required = false) MultipartFile file, String rubname,String rubloc){
        if (rubname == null && file!=null) {
            String fileName = file.getOriginalFilename();//图片名称
            String suffix = "jpg";
            if(fileName.contains(".")) {
                int index = fileName.lastIndexOf(".");
                suffix = fileName.substring(index + 1);//图片后缀
            }
                String filePath = FileUtil.getUpLoadFilePath();
                fileName = "tmp."+suffix;
            try {
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                //TODO: handel exception
            }

            try {
                rubname = ImageRec.img2txt(filePath + "\\" + fileName);
            }catch (IOException m){
                //TODO: handel exception
            }

        }
        Rubbish rubbish = rubMapper.searchRub(rubname, rubloc);
        model.addAttribute("rubbish",rubbish);
        model.addAttribute("isResult","1");
        return "index";

    }

    @PostMapping("/useradd")
    public String userAddRub(@RequestParam("file1") MultipartFile file, Useradd useradd){
        String fileName = "";
        if (file!=null) {

            fileName = file.getOriginalFilename();//图片名称
            String filePath = FileUtil.getUpLoadFilePath();
            fileName = System.currentTimeMillis() + fileName;
            try {
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                //TODO: handel exception
            }
            useradd.setAdd_img(fileName);
        }
        Integer i = rubMapper.userAddRub(useradd);

        return "redirect:/rub/index";

    }

    @PostMapping("/useredit")
    public String userEditRub(@RequestParam("file2") MultipartFile file, Useredit useredit){
        String fileName = "";
        if (file!=null) {

            fileName = file.getOriginalFilename();//图片名称
            String filePath = FileUtil.getUpLoadFilePath();
            fileName = System.currentTimeMillis() + fileName;
            try {
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                //TODO: handel exception
            }
            useredit.setEimg(fileName);
        }
        Integer i = rubMapper.userEditRub(useredit);

        return "redirect:/rub/index";

    }




}
