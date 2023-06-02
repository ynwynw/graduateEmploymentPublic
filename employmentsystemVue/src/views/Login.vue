<template>
  <!--登录表单的容器-->
  <div class="login_container">
    <!--登录区域-->
    <div class="login_box" >
      <!--头像-->
      <div style="text-align: center">
        <p style="margin-top:30px;font-weight: 700; font-size: 25px;">高校就业管理系统</p>
      </div>
      <!--表单-->
      <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-width="0px" class="login_form">
        <el-form-item prop="phone_number">
          <el-input v-model="loginForm.phone_number" placeholder="请输入电话号" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" placeholder="请输入登录密码" prefix-icon="el-icon-lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="usertype">
          <el-radio-group v-model="loginForm.usertype" size="medium">
            <el-radio v-for="(item, index) in userOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="vfyCode">
          <el-row :span="24">
            <el-col :span="12">
              <el-input v-model="loginForm.vfyCode" auto-complete="off" placeholder="请输入验证码"></el-input>
            </el-col>
            <el-col :span="12">
              <div class="login-code" @click="refreshCode">
                <!--验证码组件-->
                <s-identify :identifyCode="identifyCode"></s-identify>
              </div>
            </el-col>
          </el-row>

        </el-form-item>
        <el-form-item class="login_btn">
          <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
          <el-button @click="resetForm('loginForm')">重置</el-button>
          <el-button type="success" icon="el-icon-plus" @click="show">商家注册</el-button>
        </el-form-item>
      </el-form>
    </div>
    <CompanysAdd :addOrUpdateVisible="addOrUpdateVisible" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></CompanysAdd>
  </div>
</template>

<script>
  import CompanysAdd from './companys/CompanysAdd';
  import SIdentify from "./SIdentify";
  import { loginUser } from '../api/users';

  export default {
    name: 'Login',
    components: {
      's-identify':SIdentify,
      CompanysAdd: CompanysAdd
    },
    data() {
      return {
        loginForm: {
          phone_number: '',
          password: '',
          vfyCode: '',
          usertype: ''
        },
        addOrUpdateVisible: false,
        identifyCode:'',
        identifyCodes: '1234567890',

        loginRules: {
          phone_number: [
            { required: true, message: '请输入用户姓名', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入登录密码', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
          ],
          vfyCode: [
            { required: true, message: '请输入验证码', trigger: 'blur' }
          ],
          usertype: [
            { required: true, message: '请选择登陆用户', trigger: 'blur' }
          ]
        },
        userOptions: [{
          "label": "管理员",
          "value": 1
        }, {
          "label": "企业",
          "value": 2
        }, {
          "label": "辅导员",
          "value": 3
        }, {
          "label": "学生",
          "value": 4
        }]
      };

    },
    mounted(){
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    created(){
      this.refreshCode();
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
             if(this.loginForm.vfyCode == ""){
               alert("请输入验证码");
               return;
             }
             if(this.loginForm.vfyCode != this.identifyCode){
               this.loginForm.vfyCode ="";
               this.refreshCode();
               alert("请输入正确的验证码！");
               return;
             }
              this.loginUser();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      async loginUser(){
        const { data:res } = await loginUser(this.loginForm.phone_number,this.loginForm.password,this.loginForm.usertype);
        if(res.code == 200){
          this.$notify.success({
            title:'登录成功',
            message: '登录成功'
          });
          if(this.loginForm.usertype == "1"){
            this.$session.set("phone",this.loginForm.phone_number);
            this.$session.set("usertype", "1");
            this.$router.push("/main");
          }else if(this.loginForm.usertype == "2"){
            this.$session.set("phone",this.loginForm.phone_number);
            this.$session.set("usertype", "2");
            this.$router.push("/main2");
          }else if(this.loginForm.usertype == "3"){
            this.$session.set("phone",this.loginForm.phone_number);
            this.$session.set("usertype", "3");
            this.$router.push("/main3");
          }else{
            this.$session.set("phone",this.loginForm.phone_number);
            this.$session.set("usertype", "4");
            this.$router.push("/main4");
          }
        }else{
          return this.$message.error('用户登录失败:'+res.message);
        }
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },

      randomNum(min, max) {
        return Math.floor(Math.random() * (max - min) + min);
      },
      refreshCode() {
        this.identifyCode = "";
        this.makeCode(this.identifyCodes, 4);
      },
      show(){
        this.addOrUpdateVisible = true;
      },
      showAddOrUpdate(data){
        if(data === 'false'){
          this.addOrUpdateVisible = false;
        }else{
          this.addOrUpdateVisible = true;
        }
      },
      makeCode(o, l) {
        for (let i = 0; i < l; i++) {
          this.identifyCode += this.identifyCodes[
            this.randomNum(0, this.identifyCodes.length)
            ];
        }
        console.log(this.identifyCode);
      }
    }
  };
</script>

<style lang="less" scoped>
  .login_container {
    height: 100%;
    background-image:url('../assets/imgs/loginimg.jpg');
    background-repeat:no-repeat;
    background-size: 100% 100%;
  }

  .login_box {
    width: 450px;
    height: 400px;
    background-color: #FFFFFF;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    .avatar_box{
      width: 130px;
      height: 130px;
      border: 1px solid #EEEEEE;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      margin: -65px auto;
      background-color: #FFFFFF;

      img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #EEEEEE;
      }
    }

    .login_form{
      position: absolute;
      bottom: 0px;
      width: 100%;
      padding: 0px 20px;
      box-sizing: border-box;

      .login_btn{
        display: flex;
        justify-content: flex-end;
      }

      .verifyCode_box{
        display: flex;
        .verifyCode{
          width: 70%;
          justify-content: left;
        }

        .verifyCode_img{
          width: 30%;
          height: 45px;
          justify-content: flex-end;
        }
      }
    }
  }
</style>
