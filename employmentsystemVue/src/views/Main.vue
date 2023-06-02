<template>
  <el-container class="main_container">
    <!--头部布局-->
    <el-header>
      <!--logo和项目名称-->
      <div class="left_box">
        <img src="../assets/imgs/4.jpg" style="margin-top: 10px; height: 50px;">
        <span>高校就业管理系统 —— 管理员端 </span>
      </div>
      <!--用户登录展示的头像-->
      <div class="right_box">
        <el-dropdown>
          <img src="../assets/imgs/2.jpg" style="height: 50px;">
          <!--下拉菜单-->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-switch-button" @click.native="outLogin()">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <!--侧边栏-->
      <el-aside :width="isCollapse?'64px':'200px'">
        <!--展开/收起-->
        <div class="toggle_box" @click="toggleCollapse">|||</div>
        <el-menu
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#001529"
          text-color="#fff"
          active-text-color="#ffd04b"
          :collapse="isCollapse"
          :default-active="activePath"
          :collapse-transition="true"
          :unique-opened="true"
          :router="true">
          <MenuTree :menuList="this.MenuList"></MenuTree>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import MenuTree from '../components/MenuTree';
  import { findUserList2 } from '../api/users';
  import { findCompanysList2 } from '../api/companys';
  import { findResurtsDatasList2 } from '../api/recruits';
  import { findResumesDatasList2 } from '../api/resumes';
  import { findClassesTongjiList3 } from '../api/classes';
  import { findMajorTongjiList } from '../api/majors';
  import { findCollegesTongjiList } from '../api/colleges';

  export default {
    name: 'Main',
    data () {
      return {
        userstotal: 100,
        companystotal: 100,
        resurtstotal: 100,
        resumestotal: 100,
        dataList: [],
        collegedataList: [],
        majordataList: [],
        isCollapse: false,
        activePath: '/indexadmins',
        MenuList: [
          {
            "id": 19,
            "parentId": 0,
            "menuName": "首页",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 1,
                "menuName": "首页",
                "url": "/indexadmins",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },{
            "id": 1,
            "parentId": 0,
            "menuName": "个人信息管理",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 1,
                "menuName": "个人信息管理",
                "url": "/admins",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },
          {
            "id": 2,
            "parentId": 0,
            "menuName": "用户信息管理",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 2,
                "menuName": "学生信息管理",
                "url": "/users",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 2,
                "parentId": 2,
                "menuName": "公司企业信息管理",
                "url": "/companys",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 3,
                "parentId": 2,
                "menuName": "辅导员信息管理",
                "url": "/instructors",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },{
            "id": 3,
            "parentId": 0,
            "menuName": "学院专业管理",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 3,
                "menuName": "学院信息管理",
                "url": "/colleges",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 2,
                "parentId": 3,
                "menuName": "专业信息管理",
                "url": "/majors",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 3,
                "parentId": 3,
                "menuName": "班级信息管理",
                "url": "/classes",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },{
            "id": 4,
            "parentId": 0,
            "menuName": "企业岗位管理",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 3,
                "menuName": "企业岗位信息管理",
                "url": "/recruits",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },{
            "id": 5,
            "parentId": 0,
            "menuName": "类型信息管理",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 3,
                "menuName": "类型信息管理",
                "url": "/types",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },{
            "id": 6,
            "parentId": 0,
            "menuName": "简历信息管理",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 3,
                "menuName": "简历信息管理",
                "url": "/resumes",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },{
            "id": 7,
            "parentId": 0,
            "menuName": "学生三方协议书审核",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 3,
                "menuName": "学生三方协议书审核",
                "url": "/myapplys2",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          },{
            "id": 8,
            "parentId": 0,
            "menuName": "就业统计",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 1,
                "parentId": 8,
                "menuName": "按班级统计",
                "url": "/classestongji",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 21,
                "parentId": 8,
                "menuName": "按学院统计",
                "url": "/tongji",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 3,
                "parentId": 8,
                "menuName": "按专业统计",
                "url": "/majorstongji",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 4,
                "parentId": 8,
                "menuName": "按企业统计",
                "url": "/companyestongji",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              },
              {
                "id": 5,
                "parentId": 8,
                "menuName": "按岗位统计",
                "url": "/recruitstongji",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "admins",
                "type": 0,
                "children": []
              }
            ]
          }

        ]
      };
    },
    components: {
      MenuTree
    },
    created(){
      this.findUserList();
      this.findCompanyeList();
      this.findResurtsList();
      this.findResumesList();
      this.getClassesDataList();
      this.findMajorTongjiList();
      this.findCollegesTongjiList();
    },
    methods: {
      async findCollegesTongjiList(){
        const { data } = await findCollegesTongjiList();
        this.collegedataList = data.data.records;
      },
      async getClassesDataList(){
        const { data } = await findClassesTongjiList3();
        this.dataList = data.data.records;
      },
      async findMajorTongjiList(){
        const { data } = await findMajorTongjiList();
        this.majordataList = data.data.records;
      },
      async findUserList(){
        const { data } = await findUserList2();
        this.userstotal = data.data.total;
      },
      async findCompanyeList(){
        const { data } = await findCompanysList2();
        this.companystotal = data.data.total;
      },
      async findResurtsList(){
        const { data } = await findResurtsDatasList2();
        this.resurtstotal = data.data.total;
      },
      async findResumesList(){
        const { data } = await findResumesDatasList2();
        this.resumestotal = data.data.total;
      },
      toggleCollapse () {
        this.isCollapse = !this.isCollapse;
        },
        handleOpen (key, keyPath) {
        console.log(key, keyPath);
        },
      handleClose (key, keyPath) {
        console.log(key, keyPath);
      },
      outLogin(){
        this.$router.push("/login");
        this.$session.clear();
      }
    }
  };
</script>

<style lang="less" scoped>
  .main_container {
    height: 100%;
  }
  .col1 {
    background-color: teal;
    height: 200px;
    text-align: center;
    line-height: 200px;
    color: #fff;
    font-size: 30px;
  }
  .col2 {
    background-color: tomato;
    height: 200px;
    text-align: center;
    line-height: 200px;
    color: #fff;
    font-size: 30px;
  }
  .col3 {
    background-color: thistle;
    height: 600px;
    text-align: center;
    font-size: 25px;
  }
  .el-header {
    background-color: #001529;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    color: #FFFFFF;
    align-items: center;
    font-size: 20px;

    .left_box {
      display: flex;
      align-items: center;
      img {
        width: 60px;
        height: 60px;
        margin: 0px 0px 10px 15px;
      }

      span {
        margin-left: 15px;
      }
    }

    .right_box {
      .el-dropdown > img {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        background-color: #FFFFFF;
        margin: 0px 15px 0px 0px;
        background-size: contain;
      }
    }
  }

  .el-aside {
    background-color: #001529;

    .el-menu {
      border-right: none;
    }
    .toggle_box {
      background-color: chartreuse;
      font-size: 15px;
      font-weight: bold;
      line-height: 24px;
      color: #FFFFFF;
      letter-spacing: 0.2em;
      text-align: center;
      cursor: pointer;

    }
  }

  .el-main {
    background-color: #E9EEF3;
  }

  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
</style>
