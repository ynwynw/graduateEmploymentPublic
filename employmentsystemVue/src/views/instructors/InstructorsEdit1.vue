<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="修改个人信息">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="user" :rules="rules" size="medium" label-width="80px">
          <el-col :span="24">
            <el-form-item label="头像">
              <input type="file" ref="file2" name="check_img_url" @change="tirggerFile($event)"
                     aria-multiline="true" />
              <div>
                <img style="width:100px;height:100px;margin-left:105px" id="img2" src alt />
              </div>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="sname">
              <el-input v-model="user.sname" placeholder="请输入姓名" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="密码" prop="spwd">
              <el-input v-model="user.spwd" placeholder="请输入密码" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="手机" prop="stel">
              <el-input v-model="user.stel" placeholder="请输入手机" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="ssex">
              <el-radio-group v-model="user.ssex" size="medium">
                <el-radio v-for="(item, index) in sexOptions" :key="index" :label="item.value"
                          :disabled="item.disabled">{{item.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄" prop="sage">
              <el-input v-model="user.sage" placeholder="请输入年龄" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

  import ImageCropper from '../../components/ImageCropper';
  import PanThumb from '../../components/PanThumb';
  import { updateInstructors } from '../../api/instructors';
  import { uploadimg } from '../../api/uploadfile';

  export default {
    name: 'InstructorsEdit1',
    components: { ImageCropper, PanThumb },
    props:{
      addOrUpdateVisible:{
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        user: {
          simg: null,
          sname: '',
          spwd: '',
          stel: '',
          sage:'',
          ssex:'',
          id: ''
        },
        imgUrl:'',
        imgName:'',
        imgtype2: '',
        imgtype: '',
        showDialog:false,
        rules: {
          sname: [{
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }],
          spwd: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
          stel: [{
            required: true,
            message: '请输入手机',
            trigger: 'blur'
          }],
          sage: [{
            required: true,
            message: '请输入年龄',
            trigger: 'blur'
          }],
          ssex: [{
            required: true,
            message: '请输入性别',
            trigger: 'blur'
          }]
        },
        sexOptions: [{
          "label": "男",
          "value": 1
        }, {
          "label": "女",
          "value": 2
        }]
      };
    },
    computed: {},
    watch: {
      addOrUpdateVisible(oldVal,newVal){
        this.showDialog = this.addOrUpdateVisible;
      }
    },
    created() {
    },
    mounted() {
    },
    methods: {
      onOpen() {
      },
      dataInit(data){
        if(data){
          this.user = data;
        }
      },
      onClose() {
        this.$refs.elForm.resetFields();
        this.$emit('changeShow', 'false');
      },
      close() {
        this.$emit('changeShow', 'false');
      },
      handelConfirm() {
        this.$refs.elForm.validate((valid) => {
          if (!valid) return;
          this.updateUser();
        });
      },

      tirggerFile(event) {
        var file = event.target.files;
        var reader = new FileReader();// 读取文件
        var that = this;

        var name = file[0].name;
        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(fileName !="jpg" && fileName !="jpeg" && fileName !="png" && fileName !="bmp"){
          alert("图片格式不正确！");
          return;
        }
        var fileSize = file[0].size;
        var size = fileSize / 1024;
        if(size>10000){
          alert("图片不能大于10M");
          return;
        }else if(size <= 0){
          alert("图片不能小于0M");
          return;
        }
        reader.readAsDataURL(file[0]);
        reader.onload = function() {
          document.getElementById("img2").src = reader.result;
          that.imgUrl = reader.result;
        };

        if(file[0].type == 'image/jpeg'){
          this.user.simg = new Date().getTime()+".jpg";
          this.imgtype2 = "1";
          this.imgName = file[0].name;
          this.imgtype = file[0].type;
        }
        else if(file[0].type == 'image/png'){
          this.user.simg = new Date().getTime()+".png";
          this.imgtype2 = "2";
          this.imgName = file[0].name;
          this.imgtype = file[0].type;
        }
        else{
          this.user.simg = "";
          this.imgName = "";
          this.imgtype = "";
        }
      },

      async updateUser(){
        var that = this;
        const { data:res } = await updateInstructors(this.user,this.user.simg,this.imgtype,this.imgName);
        if(res.code == 200){
          this.$notify.success({
            title:'操作成功',
            message: '修改成功'
          });
          if(this.imgtype == 'image/jpeg'){
            uploadimg(JSON.stringify(that.imgUrl),this.user.simg,this.imgtype2);
          }
          else if(this.imgtype == 'image/png'){
            uploadimg(JSON.stringify(that.imgUrl),this.user.simg,this.imgtype2);
          }
          else{
          }
          this.$emit('changeShow','false');
          this.user = {};
          this.$emit('getUserList');
        }else{
          return this.$message.error('修改失败:');
        }
      }
    }
  };

</script>
<style>
  .el-upload__tip {
    line-height: 1.2;
  }

</style>
