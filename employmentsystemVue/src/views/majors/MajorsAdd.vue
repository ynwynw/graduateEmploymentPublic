<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="添加专业信息">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="user" :rules="rules" size="medium" label-width="80px">
          <el-col :span="12">
            <el-form-item label="专业名称" prop="cname">
              <el-input v-model="user.cname" placeholder="请输入专业名称" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="专业编号" prop="cbianhao">
              <el-input v-model="user.cbianhao" placeholder="请输入专业编号" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="专业介绍" prop="cintroduce">
              <el-input v-model="user.cintroduce" placeholder="请输入专业介绍" clearable :style="{width: '100%'}">
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
  import { saveMajors } from '../../api/majors';
  import { uploadimg } from '../../api/uploadfile';

  export default {
    name: 'MajorsAdd',
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
          cintroduce: undefined
        },
        imgUrl:'',
        imgName:'',
        imgtype: '',
        showDialog:false,
        rules: {
          cname: [{
            required: true,
            mescstaffnums: '请输入专业名称',
            trigger: 'blur'
          }],
          cbianhao: [{
            required: true,
            mescstaffnums: '请输入专业编号',
            trigger: 'blur'
          }],
          cintroduce: [{
            required: true,
            mescstaffnums: '请输入专业介绍',
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
    created() {},
    mounted() {},
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
        var that = this;
        this.$refs.elForm.validate((valid) => {
          if (!valid) return;
          that.addMajors();
        });
      },
      async addMajors(){
        var that = this;
        const { data:res } = await saveMajors(this.user);
        if(res.code == 200){
           this.$notify.success({
             title:'操作成功',
             mescstaffnums: '添加成功'
           });
          this.$emit('changeShow','false');
          this.user = {};
          this.$emit('getMajorsList');
        }else{
          return this.$mescstaffnums.error('添加失败:'+res.mescstaffnums);
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
