<template>
  <el-container class="main_container">
    <!--头部布局-->
    <el-header>
      <!--logo和项目名称-->
      <div class="left_box">
        <img src="../assets/imgs/4.jpg" style="margin-top: 10px; height: 50px;">
        <span>高校就业管理系统 —— 辅导员端 </span>
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
          :collapse-transition="false"
          :unique-opened="true"
          :router="true">
          <MenuTree :menuList="this.MenuList"></MenuTree>
        </el-menu>
      </el-aside>
      <el-main>
        <!--路由视图-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import MenuTree from '../components/MenuTree';

  export default {
    name: 'Main3',
    data () {
      return {
        isCollapse: false,
        activePath: '',
        MenuList: [
          {
            "id": 1,
            "parentId": 0,
            "menuName": "系统管理",
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
                "url": "/instructors1",
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
            "menuName": "学生用户管理",
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
                "menuName": "学生用户管理",
                "url": "/users1",
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
                "url": "/myapplys3",
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
            "menuName": "查看班级就业统计信息",
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
                "menuName": "查看班级就业统计信息",
                "url": "/classestongji2",
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
    methods: {
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
  /*设置整个容器的高度*/
  .main_container {
    height: 100%;
  }

  /*头部布局*/
  .el-header {
    background-color: #001529;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    color: #FFFFFF;
    align-items: center;
    font-size: 20px;

    /*左边logo和标题*/

    .left_box {
      display: flex;
      align-items: center;

      /*logo*/

      img {
        width: 60px;
        height: 60px;
        margin: 0px 0px 10px 15px;
      }

      /*标题*/

      span {
        margin-left: 15px;
      }
    }

    /*右边的登录头像*/

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

  /*侧边栏*/
  .el-aside {
    background-color: #001529;

    .el-menu {
      border-right: none;
    }

    /*展开/收起*/

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

  /*内容主体*/
  .el-main {
    background-color: #E9EEF3;
  }

  /*下拉菜单的样式*/
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
</style>
