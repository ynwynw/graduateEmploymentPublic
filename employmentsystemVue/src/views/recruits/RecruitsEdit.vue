<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="修改企业岗位信息">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="data" :rules="rules" size="medium" label-width="80px">
          <el-col :span="12">
            <el-form-item label="岗位名称" prop="rtitle">
              <el-input v-model="data.rtitle" placeholder="请输入岗位名称" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="期望要求" prop="rwant">
              <el-input v-model="data.rwant" placeholder="请输入期望要求" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="岗位薪资" prop="rwage">
              <el-input v-model="data.rwage" placeholder="请输入岗位薪资" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="岗位介绍" prop="rintroduce">
              <el-input v-model="data.rintroduce" placeholder="请输入岗位介绍" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="需要人数" prop="rnums">
              <el-input v-model="data.rnums" placeholder="请输入需要人数" clearable :style="{width: '100%'}">
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
  import { updateRecruits } from '../../api/recruits';

  export default {
    name: 'RecruitsEdit',
    components: { ImageCropper, PanThumb },
    props:{
      addOrUpdateVisible:{
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        data: {
          rwant: '',
          rwage: '',
          rintroduce: '',
          rnums: '',
          rtitle:'',
          id: ''
        },
        showDialog:false,
        rules: {
          rwant: [{
            required: true,
            mescstaffnums: '请输入岗位期望要求',
            trigger: 'blur'
          }],
          rwage: [{
            required: true,
            mescstaffnums: '请输入岗位工资',
            trigger: 'blur'
          }],
          rintroduce: [{
            required: true,
            mescstaffnums: '请输入岗位介绍',
            trigger: 'blur'
          }],
          rnums: [{
            required: true,
            mescstaffnums: '请输入岗位所需人数',
            trigger: 'blur'
          }],
          rtitle: [{
            required: true,
            mescstaffnums: '请输入岗位名称',
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
          this.data = data;
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
          this.updateData();
        });
      },
      async updateData(){
        var that = this;
        const { data:res } = await updateRecruits(this.data);
        if(res.code == 200){
          this.$notify.success({
            title:'操作成功',
            message: '修改成功'
          });
          this.$emit('changeShow','false');
          this.data = {};
          this.$emit('getDataList');
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
