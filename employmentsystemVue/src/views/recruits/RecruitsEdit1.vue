<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="岗位应聘">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="data" :rules="rules" size="medium" label-width="80px">
          <el-col :span="24">
            <el-form-item label="选择简历" prop="resumetitle">
              <select v-model="data.resumetitle" class="form-control" :style="{width: '100%', height: '38px'}">
                <option v-for="item in drugs" :key="item.rtitle" :label="item.rtitle"
                        :value="item.rtitle"></option>
              </select>
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
  import { updateRecruits,applyDatas } from '../../api/recruits';
  import { selectResumes } from '../../api/resumes';

  export default {
    name: 'RecruitsEdit1',
    components: { ImageCropper, PanThumb },
    props:{
      addOrUpdateVisible:{
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        drugs:[],
        data: {
          resumetitle:'',
          id: ''
        },
        showDialog:false,
        rules: {
          resumetitle: [{
            required: true,
            mescstaffnums: '请选择简历',
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
      this.selectResumes();
    },
    methods: {
      async selectResumes(){
        const { data } = await selectResumes(this.$session.get("phone"));
        this.drugs = data.data.records;
      },
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
        alert(this.data.resumetitle);
        const { data:res } = await applyDatas(this.data,this.data.resumetitle,this.$session.get("phone"));
        if(res.code == 200) {
          this.$notify.success({
            title: '操作成功',
            message: '投递简历成功'
          });
          this.$emit('changeShow', 'false');
          this.data = {};
          this.$emit('getDataList');
        } else if(res.code == 201){
          this.$notify.success({
            title: '提示',
            message: '投递成功，不能重复投递'
          });
        }else{
          return this.$message.error('操作失败:'+res.message);
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
