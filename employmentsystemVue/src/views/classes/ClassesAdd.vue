<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="添加班级信息">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="user" :rules="rules" size="medium" label-width="80px">
          <el-col :span="12">
            <el-form-item label="班级名称" prop="cname">
              <el-input v-model="user.cname" placeholder="请输入班级名称" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="班主任" prop="cheadmaster">
              <el-input v-model="user.cheadmaster" placeholder="请输入班主任" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
            <el-form-item label="电话" prop="ctel">
              <el-input v-model="user.ctel" placeholder="请输入电话" clearable :style="{width: '100%'}">
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
  import { saveClasses } from '../../api/classes';
  import { uploadimg } from '../../api/uploadfile';

  export default {
    name: 'ClassesAdd',
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
          cheadmaster: undefined,
          ctel: undefined
        },
        imgUrl:'',
        imgName:'',
        imgtype: '',
        showDialog:false,
        rules: {
          cname: [{
            required: true,
            mescstaffnums: '请输入班级名称',
            trigger: 'blur'
          }],
          cheadmaster: [{
            required: true,
            mescstaffnums: '请输入班主任',
            trigger: 'blur'
          }],
          ctel: [{
            required: true,
            mescstaffnums: '请输入电话',
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
          that.addClasses();
        });
      },
      async addClasses(){
        var that = this;
        const { data:res } = await saveClasses(this.user);
        if(res.code == 200){
           this.$notify.success({
             title:'操作成功',
             mescstaffnums: '添加成功'
           });
          this.$emit('changeShow','false');
          this.user = {};
          this.$emit('getClassesList');
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
