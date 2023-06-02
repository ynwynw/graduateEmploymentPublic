package com.stu.system.controller;

import com.stu.handler.BusinessException;
import com.stu.response.Result;
import com.stu.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.system.entity.*;
import com.stu.vo.*;
import com.stu.system.entity.Admins;
import com.stu.system.entity.Applys;
import com.stu.system.entity.Classes;
import com.stu.system.entity.Colleges;
import com.stu.system.entity.Companys;
import com.stu.system.entity.Instructors;
import com.stu.system.entity.Majors;
import com.stu.system.entity.Recruits;
import com.stu.system.entity.Resumes;
import com.stu.system.entity.Types;
import com.stu.system.entity.User;
import com.stu.system.service.AdminsService;
import com.stu.system.service.ApplysService;
import com.stu.system.service.ClassesService;
import com.stu.system.service.CollegesService;
import com.stu.system.service.CompanysService;
import com.stu.system.service.InstructorsService;
import com.stu.system.service.MajorsService;
import com.stu.system.service.RecruitsService;
import com.stu.system.service.ResumesService;
import com.stu.system.service.TypesService;
import com.stu.system.service.UserService;
import com.stu.vo.ApplysVO;
import com.stu.vo.ClassesVO;
import com.stu.vo.CollegesVO;
import com.stu.vo.CompanysVO;
import com.stu.vo.InstructorsVO;
import com.stu.vo.MajorsVO;
import com.stu.vo.RecruitsVO;
import com.stu.vo.ResumesVO;
import com.stu.vo.TypesVO;
import com.stu.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Api(value = "api管理")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {
    //@CrossOrigin 解决跨域问题
    @Resource
    private UserService userService;
    @Resource
    private AdminsService adminsService;
    @Resource
    private CompanysService companysService;
    @Resource
    private InstructorsService instructorsService;
    @Resource
    private CollegesService collegesService;
    @Resource
    private MajorsService majorsService;
    @Resource
    private ClassesService classesService;
    @Resource
    private RecruitsService recruitsService;
    @Resource
    private ResumesService resumesService;
    @Resource
    private TypesService typesService;
    @Resource
    private ApplysService applysService;

    @ApiOperation(value="")
    @PostMapping(value = "/nopassCompanys", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result nopassCompanys(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Companys companys = companysService.findCompanysById(id);
            companys.setCflag(2);
            companys.setCstatus("审核不通过");
            companysService.updateCompanys(companys);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="")
    @PostMapping(value = "/passCompanys", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result passCompanys(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Companys companys = companysService.findCompanysById(id);
            companys.setCflag(1);
            companys.setCstatus("审核通过");
            companysService.updateCompanys(companys);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="")
    @PostMapping(value = "/passUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result passUsers(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            User user = userService.findUserById(id);
            user.setSflag(1);
            user.setStatus("已就业");
            userService.updateUser(user);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="")
    @PostMapping(value = "/nopassUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result nopassUsers(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            User user = userService.findUserById(id);
            user.setSflag(2);
            user.setStatus("未就业");
            userService.updateUser(user);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "修改管理员信息")
    @PostMapping("/updateAdmins")
    public Result updateAdmins( @RequestBody Admins admins){
        try {
            adminsService.updateAdmins(admins);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "修改信息")
    @PostMapping("/updateOneusers")
    public Result updateOneusers( @RequestBody User user){
        try {
            userService.updateUser(user);

            Resumes resumes = new Resumes();
            resumes.setSname(user.getSname());
            resumes.setStel(user.getStel());
            resumes.setSmajor(user.getSmajor());
            resumes.setScollege(user.getScollege());
            resumes.setSclass(user.getSclass());
            resumes.setRuid(Integer.parseInt(user.getId()+""));

            resumesService.updateByUid(resumes);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "查询管理员信息",notes = "从admins表中查询管理员信息")
    @GetMapping("/findAdminsPage")
    public Result findAdminsPage(){
        List<Admins> admins = adminsService.findAdminsPage();
        if(admins.size()==0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        return Result.ok().data("admins",admins);
    }

    @ApiOperation(value = "查询",notes = "")
    @GetMapping("/findOneusersPage")
    public Result findOneusersPage(@RequestParam(required = true, defaultValue = "") String phone){
        List<User> users = userService.findUsersByTel(phone);
        System.out.println(users.size());
        if(users==null){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        return Result.ok().data("users",users);
    }

    @ApiOperation("分页查询用户列表")
    @GetMapping("/findUserList")
    public Result findUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size) {
        Page<User> page = new Page<>(current, size);
        Page<User> userPage = userService.page(page);
        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @ApiOperation("根据用户登录账号获取用户信息")
    @PostMapping("/findUserInfo")
    public Result findUserInfo(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size,
                               @RequestParam(required = true, defaultValue = "1") String phone) {
        System.out.println(phone);
        Page<User> page = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stel", phone);
        IPage<User> userPage = userService.findUserPage(page,queryWrapper);
        List<User> records = userPage.getRecords();
        return Result.ok().data("records", records);
    }

    @PostMapping("/findUserPage2")
    public Result findUserPage2(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size) {
        Page<User> page = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> userPage = userService.findUserPage(page, queryWrapper);
        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @PostMapping("/findUserPage")
    public Result findUserPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size,
                               @RequestBody UserVO userVO) {
        Page<User> page = new Page<>(current, size);
        QueryWrapper<User> wrapper = getWrapper(userVO);
        IPage<User> userPage = userService.findUserPage(page, wrapper);
        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        System.out.println(records);
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<User> getWrapper(UserVO userVO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getSname())) {
                queryWrapper.like("sname", userVO.getSname().trim());
            }
            if (!StringUtils.isEmpty(userVO.getStel())) {
                queryWrapper.like("stel", userVO.getStel().trim());
            }
            if (!StringUtils.isEmpty(userVO.getSmajor())) {
                queryWrapper.like("smajor", userVO.getSmajor().trim());
            }
            if (!StringUtils.isEmpty(userVO.getSclass())) {
                queryWrapper.like("sclass", userVO.getSclass().trim());
            }

        }
        return queryWrapper;
    }

    @PostMapping("/findInstructorsUserPage")
    public Result findInstructorsUserPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size,
                                          @RequestParam(required = true, defaultValue = "6") String phone,
                               @RequestBody UserVO userVO) {
        Page<User> page = new Page<>(current, size);
        QueryWrapper<User> wrapper = getInstructorsUsersWrapper(userVO,phone);
        IPage<User> userPage = userService.findUserPage(page, wrapper);
        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<User> getInstructorsUsersWrapper(UserVO userVO, String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Instructors instructors = instructorsService.findInstructorsByTel(phone).get(0);
        queryWrapper.eq("sclass", instructors.getSclass());
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getSname())) {
                queryWrapper.like("sname", userVO.getSname().trim());
            }
            if (!StringUtils.isEmpty(userVO.getStel())) {
                queryWrapper.like("stel", userVO.getStel().trim());
            }
            if (!StringUtils.isEmpty(userVO.getSmajor())) {
                queryWrapper.like("smajor", userVO.getSmajor().trim());
            }
            if (!StringUtils.isEmpty(userVO.getSclass())) {
                queryWrapper.like("sclass", userVO.getSclass().trim());
            }
            queryWrapper.orderByDesc("id");
        }
        return queryWrapper;
    }

    @PostMapping("/findInstructorsPage")
    public Result findInstructorsPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size,
                               @RequestBody InstructorsVO userVO) {
        Page<Instructors> page = new Page<>(current, size);
        QueryWrapper<Instructors> wrapper = getInstructorsWrapper(userVO);
        IPage<Instructors> userPage = instructorsService.findInstructorsPage(page, wrapper);
        long total = userPage.getTotal();
        List<Instructors> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Instructors> getInstructorsWrapper(InstructorsVO userVO) {
        QueryWrapper<Instructors> queryWrapper = new QueryWrapper<>();
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getSname())) {
                queryWrapper.like("sname", userVO.getSname().trim());
            }
            if (!StringUtils.isEmpty(userVO.getStel())) {
                queryWrapper.like("stel", userVO.getStel().trim());
            }
            if (!StringUtils.isEmpty(userVO.getSmajor())) {
                queryWrapper.like("smajor", userVO.getSmajor().trim());
            }
            if (!StringUtils.isEmpty(userVO.getSclass())) {
                queryWrapper.like("sclass", userVO.getSclass().trim());
            }

        }
        return queryWrapper;
    }

    @PostMapping("/findCompanysPage2")
    public Result findCompanysPage2(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size) {
        Page<Companys> page = new Page<>(current, size);
        QueryWrapper<Companys> queryWrapper = getFlagCompanysWrapper();
        IPage<Companys> userPage = companysService.findCompanysPage(page, queryWrapper);
        long total = userPage.getTotal();
        List<Companys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Companys> getFlagCompanysWrapper() {
        QueryWrapper<Companys> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cflag",1);
        return queryWrapper;
    }

    @PostMapping("/findCompanysPage")
    public Result findCompanysPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size,
                                   @RequestBody CompanysVO userVO) {
        Page<Companys> page = new Page<>(current, size);
        QueryWrapper<Companys> wrapper = getCompanysWrapper(userVO);
        IPage<Companys> userPage = companysService.findCompanysPage(page, wrapper);
        long total = userPage.getTotal();
        List<Companys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Companys> getCompanysWrapper(CompanysVO userVO) {
        QueryWrapper<Companys> queryWrapper = new QueryWrapper<>();
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getCname())) {
                queryWrapper.like("cname", userVO.getCname().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCtel())) {
                queryWrapper.like("ctel", userVO.getCtel().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCattr())) {
                queryWrapper.like("cattr", userVO.getCattr().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCmajor())) {
                queryWrapper.like("cmajor", userVO.getCmajor().trim());
            }

        }
        return queryWrapper;
    }

    @PostMapping("/findRecruitsPage2")
    public Result findRecruitsPage2(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size) {
        Page<Recruits> page = new Page<>(current, size);
        QueryWrapper<Recruits> queryWrapper = new QueryWrapper<>();
        IPage<Recruits> userPage = recruitsService.findRecruitsPage(page, queryWrapper);
        long total = userPage.getTotal();
        List<Recruits> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @PostMapping("/findRecruitsPage")
    public Result findRecruitsPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size,
                                   @RequestBody RecruitsVO dataVO) {
        Page<Recruits> page = new Page<>(current, size);
        QueryWrapper<Recruits> wrapper = getRecruitsWrapper(dataVO);
        IPage<Recruits> userPage = recruitsService.findRecruitsPage(page, wrapper);
        long total = userPage.getTotal();
        List<Recruits> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Recruits> getRecruitsWrapper(RecruitsVO dataVO) {
        QueryWrapper<Recruits> queryWrapper = new QueryWrapper<>();
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getCname())) {
                queryWrapper.like("cname", dataVO.getCname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getCtel())) {
                queryWrapper.like("ctel", dataVO.getCtel().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getCattrs())) {
                queryWrapper.like("cattrs", dataVO.getCattrs().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRintroduce())) {
                queryWrapper.like("rintroduce", dataVO.getRintroduce().trim());
            }
            queryWrapper.orderByDesc("id");
        }
        return queryWrapper;
    }

    @PostMapping("/findResumesPage2")
    public Result findResumesPage2(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size) {
        Page<Resumes> page = new Page<>(current, size);
        QueryWrapper<Resumes> queryWrapper = new QueryWrapper<>();
        IPage<Resumes> userPage = resumesService.findResumesPage(page, queryWrapper);
        long total = userPage.getTotal();
        List<Resumes> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @PostMapping("/findResumesPage")
    public Result findResumesPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                                  @RequestParam(required = true, defaultValue = "6") Integer size,
                                  @RequestBody ResumesVO dataVO) {
        Page<Resumes> page = new Page<>(current, size);
        QueryWrapper<Resumes> wrapper = getResumesWrapper(dataVO);
        IPage<Resumes> userPage = resumesService.findResumesPage(page, wrapper);
        long total = userPage.getTotal();
        List<Resumes> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Resumes> getResumesWrapper(ResumesVO dataVO) {
        QueryWrapper<Resumes> queryWrapper = new QueryWrapper<>();
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getRwantworks())) {
                queryWrapper.like("rwantworks", dataVO.getRwantworks().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSname())) {
                queryWrapper.like("sname", dataVO.getSname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getStel())) {
                queryWrapper.like("stel", dataVO.getStel().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSmajor())) {
                queryWrapper.like("smajor", dataVO.getSmajor().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSclass())) {
                queryWrapper.like("scollege", dataVO.getSclass().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSclass())) {
                queryWrapper.like("sclass", dataVO.getSclass().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getTname())) {
                queryWrapper.like("tname", dataVO.getTname().trim());
            }
            queryWrapper.orderByDesc("id");
        }
        return queryWrapper;
    }

    @PostMapping("/findDatasForCompanyList")
    public Result findDatasForCompanyList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size,
                                          @RequestParam(required = true, defaultValue = "6") String phone,
                                   @RequestBody RecruitsVO dataVO) {
        Page<Recruits> page = new Page<>(current, size);
        QueryWrapper<Recruits> wrapper = getRecruitsForCompanyWrapper(dataVO,phone);
        IPage<Recruits> userPage = recruitsService.findRecruitsPage(page, wrapper);
        long total = userPage.getTotal();
        List<Recruits> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Recruits> getRecruitsForCompanyWrapper(RecruitsVO dataVO, String phone) {
        QueryWrapper<Recruits> queryWrapper = new QueryWrapper<>();
        Companys companys = companysService.findCompanysByTel(phone).get(0);
        queryWrapper.eq("rcid",companys.getId());
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getCname())) {
                queryWrapper.like("cname", dataVO.getCname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getCtel())) {
                queryWrapper.like("ctel", dataVO.getCtel().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getCattrs())) {
                queryWrapper.like("cattrs", dataVO.getCattrs().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRintroduce())) {
                queryWrapper.like("rintroduce", dataVO.getRintroduce().trim());
            }
            queryWrapper.orderByDesc("id");
        }
        return queryWrapper;
    }

    @PostMapping("/findDatasForUserList")
    public Result findDatasForUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                          @RequestParam(required = true, defaultValue = "6") Integer size,
                                          @RequestParam(required = true, defaultValue = "6") String phone,
                                          @RequestBody ResumesVO dataVO) {
        Page<Resumes> page = new Page<>(current, size);
        QueryWrapper<Resumes> wrapper = getResumesForUsersWrapper(dataVO,phone);
        IPage<Resumes> userPage = resumesService.findResumesPage(page, wrapper);
        long total = userPage.getTotal();
        List<Resumes> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Resumes> getResumesForUsersWrapper(ResumesVO dataVO, String phone) {
        QueryWrapper<Resumes> queryWrapper = new QueryWrapper<>();
        User user = userService.findUsersByTel(phone).get(0);
        queryWrapper.eq("ruid", user.getId());
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
        }
        return queryWrapper;
    }

    @PostMapping("/findUserApplysDatasForUserList")
    public Result findUserApplysDatasForUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                       @RequestParam(required = true, defaultValue = "6") Integer size,
                                       @RequestParam(required = true, defaultValue = "6") String phone,
                                       @RequestBody ApplysVO dataVO) {
        Page<Applys> page = new Page<>(current, size);
        QueryWrapper<Applys> wrapper = getUserApplysForUsersWrapper(dataVO,phone);
        IPage<Applys> userPage = applysService.findApplysPage(page, wrapper);
        long total = userPage.getTotal();
        List<Applys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Applys> getUserApplysForUsersWrapper(ApplysVO dataVO, String phone) {
        QueryWrapper<Applys> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stel", phone);
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRname())) {
                queryWrapper.like("rname", dataVO.getRname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getCname())) {
                queryWrapper.like("cname", dataVO.getCname().trim());
            }
        }
        return queryWrapper;
    }

    @PostMapping("/findSuccessCompanyApplysDatasForUserList")
    public Result findSuccessCompanyApplysDatasForUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                 @RequestParam(required = true, defaultValue = "6") Integer size,
                                                 @RequestParam(required = true, defaultValue = "6") String phone) {
        Page<Applys> page = new Page<>(current, size);
        QueryWrapper<Applys> wrapper = getSuccessCompanyApplysForUsersWrapper(phone);
        IPage<Applys> userPage = applysService.findApplysPage(page, wrapper);
        long total = userPage.getTotal();
        List<Applys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Applys> getSuccessCompanyApplysForUsersWrapper(String phone) {
        QueryWrapper<Applys> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ctel", phone);
        queryWrapper.eq("aflag", 1);
        return queryWrapper;
    }

    @PostMapping("/findDaishenheCompanyApplysDatasForUserList")
    public Result findDaishenheCompanyApplysDatasForUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                             @RequestParam(required = true, defaultValue = "6") Integer size,
                                                             @RequestParam(required = true, defaultValue = "6") String phone) {
        Page<Applys> page = new Page<>(current, size);
        QueryWrapper<Applys> wrapper = getDaishenheCompanyApplysForUsersWrapper(phone);
        IPage<Applys> userPage = applysService.findApplysPage(page, wrapper);
        long total = userPage.getTotal();
        List<Applys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Applys> getDaishenheCompanyApplysForUsersWrapper(String phone) {
        QueryWrapper<Applys> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ctel", phone);
        queryWrapper.eq("aflag", 0);
        return queryWrapper;
    }

    @PostMapping("/findCompanyApplysDatasForUserList")
    public Result findCompanyApplysDatasForUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                    @RequestParam(required = true, defaultValue = "6") Integer size,
                                                    @RequestParam(required = true, defaultValue = "6") String phone,
                                                    @RequestBody ApplysVO dataVO) {
        Page<Applys> page = new Page<>(current, size);
        QueryWrapper<Applys> wrapper = getCompanyApplysForUsersWrapper(dataVO,phone);
        IPage<Applys> userPage = applysService.findApplysPage(page, wrapper);
        long total = userPage.getTotal();
        List<Applys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Applys> getCompanyApplysForUsersWrapper(ApplysVO dataVO, String phone) {
        QueryWrapper<Applys> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ctel", phone);
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRname())) {
                queryWrapper.like("rname", dataVO.getRname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSname())) {
                queryWrapper.like("sname", dataVO.getSname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getStel())) {
                queryWrapper.like("stel", dataVO.getStel().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSmajor())) {
                queryWrapper.like("smajor", dataVO.getSmajor().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getScollege())) {
                queryWrapper.like("scollege", dataVO.getScollege().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getTname())) {
                queryWrapper.like("tname", dataVO.getTname().trim());
            }
        }
        return queryWrapper;
    }

    @PostMapping("/findAdminApplysDatasForUserList")
    public Result findAdminApplysDatasForUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                    @RequestParam(required = true, defaultValue = "6") Integer size,
                                                    @RequestParam(required = true, defaultValue = "6") String phone,
                                                    @RequestBody ApplysVO dataVO) {
        Page<Applys> page = new Page<>(current, size);
        QueryWrapper<Applys> wrapper = getAdminApplysForUsersWrapper(dataVO);
        IPage<Applys> userPage = applysService.findApplysPage(page, wrapper);
        long total = userPage.getTotal();
        List<Applys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Applys> getAdminApplysForUsersWrapper(ApplysVO dataVO) {
        QueryWrapper<Applys> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("aflag", 6);
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRname())) {
                queryWrapper.like("rname", dataVO.getRname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSname())) {
                queryWrapper.like("sname", dataVO.getSname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getStel())) {
                queryWrapper.like("stel", dataVO.getStel().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSmajor())) {
                queryWrapper.like("smajor", dataVO.getSmajor().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getScollege())) {
                queryWrapper.like("scollege", dataVO.getScollege().trim());
            }
        }
        return queryWrapper;
    }

    @PostMapping("/findInstructorsApplysDatasForUserList")
    public Result findInstructorsApplysDatasForUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                  @RequestParam(required = true, defaultValue = "6") Integer size,
                                                  @RequestParam(required = true, defaultValue = "6") String phone,
                                                  @RequestBody ApplysVO dataVO) {
        Page<Applys> page = new Page<>(current, size);
        QueryWrapper<Applys> wrapper = getInstructorsApplysForUsersWrapper(dataVO,phone);
        IPage<Applys> userPage = applysService.findApplysPage(page, wrapper);
        long total = userPage.getTotal();
        List<Applys> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Applys> getInstructorsApplysForUsersWrapper(ApplysVO dataVO, String phone) {
        QueryWrapper<Applys> queryWrapper = new QueryWrapper<>();
        Instructors instructors = instructorsService.findInstructorByTel(phone);
        //queryWrapper.eq("aflag", 6);
        queryWrapper.eq("sclass", instructors.getSclass());
        if (dataVO != null) {
            if (!StringUtils.isEmpty(dataVO.getRtitle())) {
                queryWrapper.like("rtitle", dataVO.getRtitle().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getRname())) {
                queryWrapper.like("rname", dataVO.getRname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSname())) {
                queryWrapper.like("sname", dataVO.getSname().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getStel())) {
                queryWrapper.like("stel", dataVO.getStel().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getSmajor())) {
                queryWrapper.like("smajor", dataVO.getSmajor().trim());
            }
            if (!StringUtils.isEmpty(dataVO.getScollege())) {
                queryWrapper.like("scollege", dataVO.getScollege().trim());
            }
        }
        return queryWrapper;
    }

    @PostMapping("/findCollegesPage3")
    public Result findCollegesPage3(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size) {
        Page<Colleges> page = new Page<>(current, size);
        QueryWrapper<Colleges> queryWrapper = new QueryWrapper<>();
        IPage<Colleges> userPage = collegesService.findCollegesPage(page, queryWrapper);
        long total = userPage.getTotal();
        List<Colleges> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @PostMapping("/findCollegesPage")
    public Result findCollegesPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size,
                                   @RequestBody CollegesVO userVO) {
        Page<Colleges> page = new Page<>(current, size);
        QueryWrapper<Colleges> wrapper = getCollegesWrapper(userVO);
        IPage<Colleges> userPage = collegesService.findCollegesPage(page, wrapper);
        long total = userPage.getTotal();
        List<Colleges> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Colleges> getCollegesWrapper(CollegesVO userVO) {
        QueryWrapper<Colleges> queryWrapper = new QueryWrapper<>();
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getCname())) {
                queryWrapper.like("cname", userVO.getCname().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCbianhao())) {
                queryWrapper.like("cbianhao", userVO.getCbianhao().trim());
            }

        }
        return queryWrapper;
    }

    @PostMapping("/findMajorsPage3")
    public Result findMajorsPage3(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size) {
        Page<Majors> page = new Page<>(current, size);
        QueryWrapper<Majors> queryWrapper = new QueryWrapper<>();
        IPage<Majors> userPage = majorsService.findMajorsPage(page, queryWrapper);
        long total = userPage.getTotal();
        List<Majors> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @PostMapping("/findMajorsPage")
    public Result findMajorsPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                                 @RequestParam(required = true, defaultValue = "6") Integer size,
                                 @RequestBody MajorsVO userVO) {
        Page<Majors> page = new Page<>(current, size);
        QueryWrapper<Majors> wrapper = getMajorsWrapper(userVO);
        IPage<Majors> userPage = majorsService.findMajorsPage(page, wrapper);
        long total = userPage.getTotal();
        List<Majors> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Majors> getMajorsWrapper(MajorsVO userVO) {
        QueryWrapper<Majors> queryWrapper = new QueryWrapper<>();
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getCname())) {
                queryWrapper.like("cname", userVO.getCname().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCbianhao())) {
                queryWrapper.like("cbianhao", userVO.getCbianhao().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCintroduce())) {
                queryWrapper.like("cintroduce", userVO.getCintroduce().trim());
            }

        }
        return queryWrapper;
    }

    @PostMapping("/findClassesPage3")
    public Result findClassesPage3(@RequestParam(required = true, defaultValue = "1") Integer current,
                                 @RequestParam(required = true, defaultValue = "6") Integer size,
                                   @RequestParam(required = true, defaultValue = "6") String phone) {
        Page<Classes> page = new Page<>(current, size);
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        IPage<Classes> userPage = classesService.findClassesPage(page, queryWrapper);
        long total = userPage.getTotal();
        List<Classes> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @PostMapping("/findClassesPage2")
    public Result findClassesPage2(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "6") Integer size,
                                   @RequestParam(required = true, defaultValue = "6") String phone) {
        Page<Classes> page = new Page<>(current, size);
        QueryWrapper<Classes> wrapper = getClassesWrapper2(phone);
        IPage<Classes> userPage = classesService.findClassesPage(page, wrapper);
        long total = userPage.getTotal();
        List<Classes> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Classes> getClassesWrapper2(String phone) {
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        Instructors instructors = instructorsService.findInstructorByTel(phone);
        queryWrapper.eq("cname",instructors.getSclass());
        return queryWrapper;
    }

    @PostMapping("/findClassesPage")
    public Result findClassesPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                                  @RequestParam(required = true, defaultValue = "6") Integer size,
                                  @RequestBody ClassesVO userVO) {
        Page<Classes> page = new Page<>(current, size);
        QueryWrapper<Classes> wrapper = getClassesWrapper(userVO);
        IPage<Classes> userPage = classesService.findClassesPage(page, wrapper);
        long total = userPage.getTotal();
        List<Classes> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Classes> getClassesWrapper(ClassesVO userVO) {
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getCname())) {
                queryWrapper.like("cname", userVO.getCname().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCheadmaster())) {
                queryWrapper.like("cheadmaster", userVO.getCheadmaster().trim());
            }
            if (!StringUtils.isEmpty(userVO.getCtel())) {
                queryWrapper.like("ctel", userVO.getCtel().trim());
            }

        }
        return queryWrapper;
    }

    @PostMapping("/findTypesPage")
    public Result findTypesPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                                  @RequestParam(required = true, defaultValue = "6") Integer size,
                                  @RequestBody TypesVO userVO) {
        Page<Types> page = new Page<>(current, size);
        QueryWrapper<Types> wrapper = getTypesWrapper(userVO);
        IPage<Types> userPage = typesService.findTypesPage(page, wrapper);
        long total = userPage.getTotal();
        List<Types> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    private QueryWrapper<Types> getTypesWrapper(TypesVO typesVO) {
        QueryWrapper<Types> queryWrapper = new QueryWrapper<>();
        if (typesVO != null) {
            if (!StringUtils.isEmpty(typesVO.getTname())) {
                queryWrapper.like("tname", typesVO.getTname().trim());
            }
        }
        return queryWrapper;
    }

    @ApiOperation("根据账号获取信息")
    @PostMapping("/findOneStudentsByUtelAndUtype")
    public Result findOneStudentsByUtelAndUtype(@RequestParam(required = true, defaultValue = "1") String phone,
                                                @RequestParam(required = true, defaultValue = "1") String utype) {
        List<User> records = userService.findUsersByTel(phone);
        return Result.ok().data("records", records);
    }

    @ApiOperation(value = "添加")
    @PostMapping("/addStuUser")
    public Result addStuUser(@RequestParam(required = true, defaultValue = "1") String simg,
                                  @RequestParam(required = true, defaultValue = "1") String imgtype,
                                  @RequestParam(required = true, defaultValue = "1") String imgName,
                                  @RequestBody User user){
        try {
            String eavatar = "";
            if(imgtype.equals("image/jpeg"))
                eavatar = simg.split(".jpg")[0];
            if(imgtype.equals("image/png"))
                eavatar = simg.split(".png")[0];

            Classes classes = classesService.findClassesByCname(user.getSclass()).get(0);
            user.setClassid(Integer.parseInt(classes.getId()+""));
            user.setSpwd("123456");
            user.setImgtype(imgtype);
            user.setSflag(0);
            user.setStatus("未就业");

            userService.addUser(user);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/addInstructorsUser")
    public Result addInstructorsUser(@RequestParam(required = true, defaultValue = "1") String simg,
                             @RequestParam(required = true, defaultValue = "1") String imgtype,
                             @RequestParam(required = true, defaultValue = "1") String imgName,
                             @RequestBody Instructors user){
        try {
            String eavatar = "";
            if(imgtype.equals("image/jpeg"))
                eavatar = simg.split(".jpg")[0];
            if(imgtype.equals("image/png"))
                eavatar = simg.split(".png")[0];

            Classes classes = classesService.findClassesByCname(user.getSclass()).get(0);
            user.setClassid(Integer.parseInt(classes.getId()+""));

            user.setSpwd("123456");
            user.setImgtype(imgtype);

            instructorsService.addInstructors(user);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/addCompanys")
    public Result addCompanys(@RequestParam(required = true, defaultValue = "1") String simg,
                             @RequestParam(required = true, defaultValue = "1") String imgtype,
                             @RequestParam(required = true, defaultValue = "1") String imgName,
                             @RequestBody Companys user){
        try {
            String eavatar = "";
            if(imgtype.equals("image/jpeg"))
                eavatar = simg.split(".jpg")[0];
            if(imgtype.equals("image/png"))
                eavatar = simg.split(".png")[0];

            user.setCpwd("123456");
            user.setImgtype(imgtype);
            user.setCflag(0);
            user.setCstatus("待审核");
            companysService.addCompanys(user);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/saveRecruits")
    public Result saveRecruits(@RequestParam(required = true, defaultValue = "") String phone,
                               @RequestBody Recruits data){
        try {
            Companys companys = companysService.findCompanysByTel(phone).get(0);
            System.out.println(phone);
            data.setCname(companys.getCname());
            data.setCtel(companys.getCtel());
            data.setCattrs(companys.getCattr());
            data.setCmajor(companys.getCmajor());
            data.setRdate(new Date());
            String rcid = companys.getId().toString();
            data.setRcid(Integer.parseInt(rcid));
            recruitsService.addRecruits(data);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/saveResumes")
    public Result saveResumes(@RequestParam(required = true, defaultValue = "") String phone,
                               @RequestBody Resumes data){
        try {
            User user = userService.findUsersByTel(phone).get(0);

            data.setSclass(user.getSclass());
            data.setScollege(user.getScollege());
            data.setSmajor(user.getSmajor());
            data.setSname(user.getSname());
            data.setStel(user.getStel());
            data.setRuid(Integer.parseInt(user.getId()+""));
            data.setRdate(new Date());

            resumesService.addResumes(data);

            Types types = typesService.findTypesByTname(data.getTname()).get(0);
            types.setTnums(types.getTnums()+1);
            typesService.updateTypes(types);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/addColleges")
    public Result addColleges(@RequestBody Colleges colleges){
        try {
            collegesService.addColleges(colleges);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/saveMajors")
    public Result saveMajors(@RequestBody Majors majors){
        try {
            majorsService.addMajors(majors);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/saveClasses")
    public Result saveClasses(@RequestBody Classes classes){
        try {
            classesService.addClasses(classes);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "添加")
    @PostMapping("/saveTypes")
    public Result saveTypes(@RequestBody Types types){
        try {
            typesService.addTypes(types);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestParam(required = true, defaultValue = "1") String simg,
                             @RequestParam(required = true, defaultValue = "1") String imgtype,
                             @RequestParam(required = true, defaultValue = "1") String imgName,
                             @RequestBody User user){
        try {
            if(userService.findUsersByTel(user.getStel()).size() == 0 ||
                    userService.findUsersByTel(user.getStel()).size() == 1){
                String eavatar = "";
                if(imgtype.equals("image/jpeg"))
                    eavatar = simg.split(".jpg")[0];
                if(imgtype.equals("image/png"))
                    eavatar = simg.split(".png")[0];

                User users = userService.findUsersByTel(user.getStel()).get(0);

                List<Resumes> resumes = resumesService.getResumesByUid(Integer.parseInt(user.getId()+""));
                for(int i=0; i<resumes.size();i++){
                    resumes.get(i).setSname(user.getSname());
                    resumes.get(i).setStel(user.getStel());
                    resumes.get(i).setSclass(user.getSclass());
                    resumes.get(i).setScollege(user.getScollege());
                    resumes.get(i).setSmajor(user.getSmajor());
                    resumesService.updateResumes(resumes.get(i));
                }

                if(imgName.equals("1")){
                    user.setImgtype(users.getImgtype());
                    user.setSimg(users.getSimg());
                    userService.updateUser(user);
                }else{
                    user.setImgtype(imgtype);
                    userService.updateUser(user);
                }
                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            //打印日志
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateUser2")
    public Result updateUser2(@RequestParam(required = true, defaultValue = "1") String tripartiteagreement,
                             @RequestParam(required = true, defaultValue = "1") String imgtype,
                             @RequestParam(required = true, defaultValue = "1") String imgName,
                             @RequestBody User user){
        try {
            System.out.println("3333333333333333333");
            System.out.println(user.getStel());
            if(userService.findUsersByTel(user.getStel()).size() == 0 ||
                    userService.findUsersByTel(user.getStel()).size() == 1){
                String eavatar = "";
                if(imgtype.equals("image/jpeg"))
                    eavatar = tripartiteagreement.split(".jpg")[0];
                if(imgtype.equals("image/png"))
                    eavatar = tripartiteagreement.split(".png")[0];

                User users = userService.findUsersByTel(user.getStel()).get(0);
                System.out.println(users.getTripartiteagreement());
                System.out.println(eavatar);
                System.out.println(imgtype);

                if(imgName.equals("1")){
                    user.setImgtype2(users.getImgtype2());
                    user.setTripartiteagreement(users.getTripartiteagreement());
                    userService.updateUser(user);
                }else{
                    user.setImgtype2(imgtype);
                    //user.setTripartiteagreement(users.getTripartiteagreement());
                    userService.updateUser(user);
                }
                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            //打印日志
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateInstructors")
    public Result updateInstructors(@RequestParam(required = true, defaultValue = "1") String simg,
                             @RequestParam(required = true, defaultValue = "1") String imgtype,
                             @RequestParam(required = true, defaultValue = "1") String imgName,
                             @RequestBody Instructors user){
        try {
            if(instructorsService.findInstructorsByTel(user.getStel()).size() == 0 ||
                    instructorsService.findInstructorsByTel(user.getStel()).size() == 1){
                String eavatar = "";
                if(imgtype.equals("image/jpeg"))
                    eavatar = simg.split(".jpg")[0];
                if(imgtype.equals("image/png"))
                    eavatar = simg.split(".png")[0];
                System.out.println("imgName"+imgName);
                Instructors users = instructorsService.findInstructorsById(Integer.parseInt(user.getId()+""));
                if(imgName.equals("1")){
                    System.out.println("user.getStel()"+users.getSimg());
                    user.setImgtype(users.getImgtype());
                    user.setSimg(users.getSimg());
                    instructorsService.updateInstructors(user);
                }else{
                    user.setImgtype(imgtype);
                    instructorsService.updateInstructors(user);
                }
                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateCompanys")
    public Result updateCompanys(@RequestParam(required = true, defaultValue = "1") String simg,
                             @RequestParam(required = true, defaultValue = "1") String imgtype,
                             @RequestParam(required = true, defaultValue = "1") String imgName,
                             @RequestBody Companys user){
        try {
            if(companysService.findCompanysByTel(user.getCtel()).size() == 0 ||
                    companysService.findCompanysByTel(user.getCtel()).size() == 1){
                String eavatar = "";
                if(imgtype.equals("image/jpeg"))
                    eavatar = simg.split(".jpg")[0];
                if(imgtype.equals("image/png"))
                    eavatar = simg.split(".png")[0];

                Companys users = companysService.findCompanysById(Integer.parseInt(user.getId()+""));
                if(imgName.equals("1")){
                    user.setImgtype(users.getImgtype());
                    user.setCimg(users.getCimg());
                    companysService.updateCompanys(user);
                }else{
                    user.setImgtype(imgtype);
                    companysService.updateCompanys(user);
                }

                Recruits recruits = new Recruits();
                recruits.setRcid(Integer.parseInt(user.getId()+""));
                recruits.setCname(user.getCname());
                recruits.setCtel(user.getCtel());
                recruits.setCattrs(user.getCattr());
                recruits.setCmajor(user.getCmajor());
                recruitsService.updateByCid(recruits);

                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateRecruits")
    public Result updateRecruits(@RequestBody Recruits data){
        try {
            if(companysService.findCompanysByTel(data.getCtel()).size() == 0 ||
                    companysService.findCompanysByTel(data.getCtel()).size() == 1){
                recruitsService.updateRecruits(data);
                return Result.ok();
            }else
                return Result.error();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateResumes")
    public Result updateResumes(@RequestBody Resumes data){
        try {
            resumesService.updateResumes(data);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateColleges")
    public Result updateColleges(@RequestBody Colleges colleges){
        try {
            if(collegesService.findCollegesByCbianhao(colleges.getCbianhao()).size() == 0 ||
                    collegesService.findCollegesByCbianhao(colleges.getCbianhao()).size() == 1){
                collegesService.updateColleges(colleges);

                Applys applys = new Applys();
                applys.setCollegeid(Integer.parseInt(colleges.getId()+""));
                applys.setScollege(colleges.getCname());
                applysService.updateBycollegeid(applys);

                Instructors instructors = new Instructors();
                instructors.setCollegeid(Integer.parseInt(colleges.getId()+""));
                instructors.setScollege(colleges.getCname());
                instructorsService.updateBycollegeid(instructors);

                User user = new User();
                user.setCollegeid(Integer.parseInt(colleges.getId()+""));
                user.setScollege(colleges.getCname());
                userService.updateBycollegeid(user);

                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateMajors")
    public Result updateMajors(@RequestBody Majors majors){
        try {
            if(majorsService.findMajorsByCbianhao(majors.getCbianhao()).size() == 0 ||
                    majorsService.findMajorsByCbianhao(majors.getCbianhao()).size() == 1){
                majorsService.updateMajors(majors);

                Applys applys = new Applys();
                applys.setMarjorid(Integer.parseInt(majors.getId()+""));
                applys.setSmajor(majors.getCname());
                applysService.updateBymarjorid(applys);

                Instructors instructors = new Instructors();
                instructors.setMarjorid(Integer.parseInt(majors.getId()+""));
                instructors.setSmajor(majors.getCname());
                instructorsService.updateBymarjorid(instructors);

                User user = new User();
                user.setMarjorid(Integer.parseInt(majors.getId()+""));
                user.setSmajor(majors.getCname());
                userService.updateBymarjorid(user);

                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateClasses")
    public Result updateClasses(@RequestBody Classes classes){
        try {
            if(classesService.findClassesByCtel(classes.getCtel()).size() == 0 ||
                    classesService.findClassesByCtel(classes.getCtel()).size() == 1){
                classesService.updateClasses(classes);

                Applys applys = new Applys();
                applys.setClassid(Integer.parseInt(classes.getId()+""));
                applys.setSclass(classes.getCname());
                applysService.updateApplysByClassid(applys);

                Instructors instructors = new Instructors();
                instructors.setClassid(Integer.parseInt(classes.getId()+""));
                instructors.setSclass(classes.getCname());
                instructorsService.updateInstructorsByClassid(instructors);

                User user = new User();
                user.setClassid(Integer.parseInt(classes.getId()+""));
                user.setSclass(classes.getCname());
                userService.updateUsersByClassid(user);

                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/updateTypes")
    public Result updateTypes(@RequestBody Types types){
        try {
            if(typesService.findTypesByTname(types.getTname()).size() == 0 ||
                    typesService.findTypesByTname(types.getTname()).size() == 1){
                typesService.updateTypes(types);
                return Result.ok();
            }else
                return Result.error();

        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation("根据账号获取信息")
    @PostMapping("/findOneCompanysByUtel")
    public Result findOneCompanysByUtel(@RequestParam(required = true, defaultValue = "1") String phone) {
        List<Companys> records = companysService.findCompanysByTel(phone);
        return Result.ok().data("records", records);
    }

    @ApiOperation("根据账号获取信息")
    @PostMapping("/findOneUserByUtel")
    public Result findOneUserByUtel(@RequestParam(required = true, defaultValue = "1") String phone) {
        List<User> records = userService.findUsersByTel(phone);
        return Result.ok().data("records", records);
    }

    @ApiOperation("根据账号获取信息")
    @PostMapping("/findOneInstructorsByUtel")
    public Result findOneInstructorsByUtel(@RequestParam(required = true, defaultValue = "1") String phone) {
        List<Instructors> records = instructorsService.findInstructorsByTel(phone);
        return Result.ok().data("records", records);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping(value = "/loginUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result loginUser(String phone_number, String password, String usertype){
        try {
            System.out.println(usertype);
            if(usertype.equals("1"))
                adminsService.loginUser(phone_number,password);
            else if(usertype.equals("2"))
                companysService.loginCompanys(phone_number,password);
            else if(usertype.equals("3"))
                instructorsService.loginInstructors(phone_number,password,0);
            else
                userService.loginUser(phone_number,password,0);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="通过id获取信息")
    @PostMapping(value = "/findUserById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result findUserById(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            User user = userService.findUserById(id);
            if(user != null)
              return Result.ok().data("userinfo", user);
            else
                return Result.error();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteUser(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            userService.deleteUser(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteInstructors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteInstructors(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            instructorsService.deleteInstructors(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteCompanys", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteCompanys(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            companysService.deleteCompanys(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteRecruits", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteRecruits(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            recruitsService.deleteRecruits(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="应聘")
    @PostMapping(value = "/applyRecruits", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result applyRecruits(@RequestBody Recruits data,
                                @RequestParam(required = true, defaultValue = "1") String resumetitle,
                                @RequestParam(required = true, defaultValue = "1") String phone){
        try{
            User user = userService.findUsersByTel(phone).get(0);
            Recruits recruits = recruitsService.getById(data.getId());

            Resumes resumes = new Resumes();
            resumes.setRtitle(data.getResumetitle());
            resumes.setStel(phone);
            Resumes resumes1 = resumesService.findResumesByRtitleAndUtel(resumes).get(0);

            Applys applys = new Applys();
            applys.setAflag(0);
            applys.setRname(recruits.getRtitle());
            applys.setCname(recruits.getCname());
            applys.setCtel(recruits.getCtel());
            applys.setCattr(recruits.getCattrs());
            applys.setRintroduce(recruits.getRintroduce());
            applys.setCmajor(recruits.getCmajor());
            applys.setRtitle(resumetitle);
            applys.setSname(user.getSname());
            applys.setStel(user.getStel());
            applys.setSmajor(user.getSmajor());
            applys.setScollege(user.getScollege());
            applys.setSclass(user.getSclass());
            applys.setTname(resumes1.getTname());
            applys.setAstuts("已投递简历");
            applys.setRdate(new Date());
            applys.setClassid(user.getClassid());

            List<Applys> checkApplys = applysService.checkUserApplys(applys);
            if(checkApplys.size() == 0){
                applysService.addApplys(applys);
                return Result.ok();
            }else{
                return Result.alreadyexists();
            }
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteResumes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteResumes(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            resumesService.deleteResumes(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteUserApplysDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteUserApplysDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            applysService.deleteApplys(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteCompanyApplysDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteCompanyApplysDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            applysService.deleteApplys(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/passApplys", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result passApplys(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(1);
            applys.setAstuts("应聘成功");
            applysService.updateApplys(applys);

            User user = userService.findUserByTel(applys.getStel());
            user.setSflag(1);
            user.setStatus("就业成功");
            userService.updateUser(user);

            Classes classes = classesService.findClassesByCname(user.getSclass()).get(0);
            classes.setCnums(classes.getCnums()+1);
            classes.setCrates((classes.getCnums()+1)/classes.getCtotalnums()/100+"%");
            classesService.updateClasses(classes);

            Colleges colleges = collegesService.findCollegesByCname(user.getScollege()).get(0);
            colleges.setCnums(colleges.getCnums() + 1);
            colleges.setCrates((colleges.getCnums()+1)/colleges.getCtotalnums()/100+"%");
            collegesService.updateColleges(colleges);

            Majors majors = majorsService.findMajorsByCname(user.getSmajor()).get(0);
            majors.setCnums(majors.getCnums() + 1);
            majors.setCrates((majors.getCnums()+1)/majors.getCtotalnums()/100+"%");
            majorsService.updateMajors(majors);

            Companys companys = companysService.findCompanyByTel(applys.getCtel());
            companys.setCnums(companys.getCnums() + 1);
            companysService.updateCompanys(companys);

            Recruits recruits = recruitsService.findRecruitsByRtitle(applys.getRname()).get(0);
            recruits.setCnums(recruits.getCnums() + 1);
            recruitsService.updateRecruits(recruits);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/nopassApplys", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result nopassApplys(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(2);
            applys.setAstuts("应聘不成功");
            applysService.updateApplys(applys);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="")
    @PostMapping(value = "/signagreementDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result signagreementDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(3);
            applys.setAstuts("发送三方协议邀请成功");
            applysService.updateApplys(applys);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="")
    @PostMapping(value = "/agreesigntriagreementDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result agreesigntriagreementDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(4);
            applys.setAstuts("学生签订三方协议成功");
            applysService.updateApplys(applys);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="")
    @PostMapping(value = "/noagreesigntriagreementDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result noagreesigntriagreementDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(11);
            applys.setAstuts("学生签订三方协议成功");
            applysService.updateApplys(applys);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="")
    @PostMapping(value = "/auditsignDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result auditsignDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(6);
            applys.setAstuts("审核通过学生对三方协议的签订");
            applysService.updateApplys(applys);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="")
    @PostMapping(value = "/noauditsignDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result noauditsignDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(7);
            applys.setAstuts("审核通过学生对三方协议的签订");
            applysService.updateApplys(applys);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="")
    @PostMapping(value = "/adminauditsignDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result adminauditsignDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(9);
            applys.setAstuts("审核通过");
            applysService.updateApplys(applys);

            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="")
    @PostMapping(value = "/adminnoauditsignDatas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result adminnoauditsignDatas(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            Applys applys = applysService.getById(id);
            applys.setAflag(10);
            applys.setAstuts("审核不通过");
            applysService.updateApplys(applys);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteColleges", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteColleges(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            collegesService.deleteColleges(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteMajors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteMajors(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            majorsService.deleteMajors(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }
    @ApiOperation("分页查询")
    @PostMapping("/getColleges")
    public Result getColleges() {
        List<Colleges> datas = collegesService.getColleges();
        return Result.ok().data("records", datas);
    }
    @ApiOperation("分页查询")
    @PostMapping("/getTypes")
    public Result getTypes() {
        List<Types> datas = typesService.getTypes();
        return Result.ok().data("records", datas);
    }
    @ApiOperation("分页查询")
    @PostMapping("/getClasses")
    public Result getClasses() {
        List<Classes> datas = classesService.getClasses();
        return Result.ok().data("records", datas);
    }
    @ApiOperation("分页查询")
    @PostMapping("/selectResumes")
    public Result selectResumes(@RequestParam(required = true, defaultValue = "1") String phone) {
        List<Resumes> datas = resumesService.getResumes(phone);
        return Result.ok().data("records", datas);
    }
    @ApiOperation("分页查询")
    @PostMapping("/getMajors")
    public Result getMajors() {
        List<Majors> datas = majorsService.getMajors();
        return Result.ok().data("records", datas);
    }
    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteClasses", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteClasses(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            classesService.deleteClasses(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    @ApiOperation(value="通过id删除信息")
    @PostMapping(value = "/deleteTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteTypes(@RequestParam(required = true, defaultValue = "0") Integer id){
        try{
            typesService.deleteTypes(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    public static String url = "E:\\educationProject\\高校就业系统\\graduateEmploymentsystem\\qianemploymentsystemwebweb\\src\\assets\\uploadImgs\\";
    public static String currPath = "D:/bishe/";
    @ApiOperation(value = "上传图片",notes = "上传图片信息")
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam(required = true, defaultValue = "") String imgtype,
                            @RequestParam(required = true, defaultValue = "") String imgName,
                            @RequestBody String imgUrl){
        try {
            System.out.println(imgUrl);
            System.out.println(imgtype);
            System.out.println(imgName);
            imgUrl = imgUrl.replaceAll("%2F","/");
            imgUrl = imgUrl.replaceAll("%2C",",");
            imgUrl = imgUrl.replaceAll("%3B",";");
            imgUrl = imgUrl.replaceAll("%3A",":");
            System.out.println(imgUrl);
            if(imgtype.trim().equals("1")){
                imgUrl = imgUrl.split("data:image/jpeg;base64,")[1];
            }
            if(imgtype.trim().equals("2"))
                imgUrl = imgUrl.split("data:image/png;base64,")[1];
            System.out.println(imgUrl);
            GenerateImage(imgUrl,imgName);

            return Result.ok();
        }catch (Exception e){
            //打印日志
            return Result.error();
        }
    }
    public static boolean GenerateImage(String imgStr, String avatar) {
        if (imgStr == null) {
            return false;
        }

        File file = new File(url);
        if(!file.exists()){
            file.mkdirs();
        }
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String name = df.format(new Date());

        Random r = new Random();
        for(int i = 0 ;i<3 ;i++){
            name += r.nextInt(10);
        }

        String path = "/"+avatar;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            String imgFilePath = url+path;
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public boolean checkUrl(String strUrl){
        URL url;
        try {
            url = new URL(strUrl);
            InputStream in = url.openStream();
            return true;
        } catch (Exception e1) {
            return false;
        }
    }

    public boolean downloadFile(String fileUrl,String fileLocal) throws Exception {
        boolean flag=false;
        FileInputStream infile = new FileInputStream(fileUrl);
        //读文件流
        DataInputStream in = new DataInputStream(infile);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileLocal));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        try {
            if(out!=null) {
                out.close();
            }
            if(in!=null) {
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        flag=true;
        return flag;
    }

    public String shijianchuo() {
        return System.currentTimeMillis()+"";
    }


}

