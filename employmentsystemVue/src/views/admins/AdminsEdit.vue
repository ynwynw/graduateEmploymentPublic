<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="修改管理员信息">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="admins" :rules="rules" size="medium" label-width="100px">
          <el-col :span="22">
            <el-form-item label="姓名" prop="uname">
              <el-input v-model="admins.uname" placeholder="请输入姓名" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="密码" prop="upwd">
              <el-input v-model="admins.upwd" placeholder="请输入密码" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>

          </el-col>
          <el-col :span="22">
            <el-form-item label="电话" prop="utel">
              <el-input v-model="admins.utel" placeholder="请输入电话" clearable :style="{width: '100%'}">
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
  import { updateAdmins } from '../../api/admins';

  export default {
    name: 'AdminsEdit',
    components: { ImageCropper, PanThumb },
    props:{
      addOrUpdateVisible:{
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        admins: {
          uname: '',
          upwd: '',
          utel: ''
        },
        showDialog:false,
        rules: {
          uname: [{
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }],
          upwd: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
          utel: [{
            required: true,
            message: '电话不能为空',
            trigger: 'change'
          }]

        }
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
          this.updateadmins();
        });
      },

      dataInit(data){
        if(data){
          this.admins = data;
        }
      },

      async updateadmins(){
        var that = this;
        const { data:res } = await updateAdmins(this.admins);
        if(res.code == 200){
          this.$notify.success({
            title:'操作成功',
            message: '管理员信息修改成功'
          });

          this.$emit('changeShow','false');
          this.admins = {};
          this.$emit('getAdminsList');
        }else{
          return this.$message.error('管理员信息修改失败:'+res.message);
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
