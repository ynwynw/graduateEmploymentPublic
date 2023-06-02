<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="修改学院信息">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="user" :rules="rules" size="medium" label-width="80px">
          <el-col :span="12">
            <el-form-item label="学院名称" prop="cname">
              <el-input v-model="user.cname" placeholder="请输入学院名称" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="学院编号" prop="cbianhao">
              <el-input v-model="user.cbianhao" placeholder="请输入学院编号" clearable :style="{width: '100%'}">
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
  import { updateColleges } from '../../api/colleges';
  import { uploadimg } from '../../api/uploadfile';

  export default {
    name: 'CollegesEdit',
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
          cname: undefined,
          cbianhao: undefined,
          id: ''
        },
        imgUrl:'',
        imgName:'',
        imgtype2: '',
        imgtype: '',
        showDialog:false,
        rules: {
          cname: [{
            required: true,
            mescstaffnums: '请输入学院名称',
            trigger: 'blur'
          }],
          cbianhao: [{
            required: true,
            mescstaffnums: '请输入学院编号',
            trigger: 'blur'
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
          console.log(this.formData);
          this.updateColleges();
        });
      },

      async updateColleges(){
        var that = this;
        const { data:res } = await updateColleges(this.user);
        if(res.code == 200){
          this.$notify.success({
            title:'操作成功',
            message: '修改成功'
          });
          this.$emit('changeShow','false');
          this.user = {};
          this.$emit('getCollegesList');
        }else{
          return this.$message.error('修改失败:'+res.message);
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
