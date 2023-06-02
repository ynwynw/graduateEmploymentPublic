<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="修改类型信息">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="datas" :rules="rules" size="medium" label-width="80px">
          <el-col :span="12">
            <el-form-item label="类型名称" prop="tname">
              <el-input v-model="datas.tname" placeholder="请输入类型名称" clearable :style="{width: '100%'}">
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
  import { updateTypes } from '../../api/types';
  import { uploadimg } from '../../api/uploadfile';

  export default {
    name: 'TypesEdit',
    components: { ImageCropper, PanThumb },
    props:{
      addOrUpdateVisible:{
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        datas: {
          tname: '',
          id: ''
        },
        showDialog:false,
        rules: {
          tname: [{
            required: true,
            mescstaffnums: '请输入类型名称',
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
          this.datas = data;
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
          this.updateClasses();
        });
      },
      async updateClasses(){
        var that = this;
        const { data:res } = await updateTypes(this.datas);
        if(res.code == 200){
          this.$notify.success({
            title:'操作成功',
            message: '修改成功'
          });
          this.$emit('changeShow','false');
          this.datas = {};
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
