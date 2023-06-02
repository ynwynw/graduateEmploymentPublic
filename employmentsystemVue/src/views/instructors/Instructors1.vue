<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>个人信息管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <el-table
        :data="userList"
        border
        max-height="400"
        style="width: 100%;">
        <el-table-column
          prop="id"
          label="#"
          type="index"
          width="50">
        </el-table-column>

        <el-table-column width="100">
          <template slot-scope="scope">
            <img v-if="scope.row.imgtype=='image/jpeg'"
                 :src="require('../../assets/uploadImgs/'+scope.row.simg)"
                 width="40" height="40" class="head_pic"/>
            <img v-else-if="scope.row.imgtype=='image/png'"
                 :src="require('../../assets/uploadImgs/'+scope.row.simg)"
                 width="40" height="40" class="head_pic"/>
          </template>
        </el-table-column>

        <el-table-column
          prop="sname"
          label="姓名"
          width="180">
        </el-table-column>

        <el-table-column
          prop="spwd"
          label="密码"
          width="120">
        </el-table-column>
        <el-table-column
          sortable
          prop="stel"
          label="电话"
          width="120">
        </el-table-column>
        <el-table-column
          prop="ssex"
          label="性别"
          width="90">
          <template slot-scope="scope">
            <span class="el-tag el-tag--success el-tag--mini el-tag--light">{{scope.row.ssex==1?'男':(scope.row.ssex==2?'女':'保密')}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="sage"
          label="年龄"
          width="90">
        </el-table-column>
        <el-table-column
          prop="scollege"
          label="学院"
          width="200">
        </el-table-column>
        <el-table-column
          prop="smajor"
          label="专业"
          width="200">
        </el-table-column>
        <el-table-column
          prop="sclass"
          label="班级"
          width="180">
        </el-table-column>
        <el-table-column
          label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="userEdit(scope.row)" title="编辑"></el-button>
          </template>
        </el-table-column>
      </el-table>

      <InstructorsEdit1 :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></InstructorsEdit1>
    </el-card>
  </div>

</template>

<script>
  import { findInstructorsOneList } from '../../api/instructors';

  import InstructorsEdit1 from './InstructorsEdit1';

  export default {
    name: 'Instructors1',
    data() {
      return {
        addOrUpdateVisible: false,
        editOrUpdateVisible: false,
        userList: [],
        size: 6,
        total: 100,
        current: 1
      };
    },
    components: {
      InstructorsEdit1
    },
    created(){
      this.getUserList();
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      handleSizeChange(val) {
        this.size = val;
        this.getUserList();
      },
      handleCurrentChange(val) {
        this.current = val;
        this.getUserList();
      },
      async getUserList(){
        if(this.$session.get("phone") == null)
          this.$router.push("/login");
        const { data } = await findInstructorsOneList(this.$session.get("phone"));
        this.userList = data.data.records;
      },
      show(){
        this.addOrUpdateVisible = true;
      },
       userEdit(row){
        var that = this;
         this.editOrUpdateVisible = true;
        this.$nextTick(() => {
            that.$refs.eidtOrUpdateRef.dataInit(row);
        });
         this.getUserList();
        console.log(this.userEdit);
      },
      showAddOrUpdate(data){
        if(data === 'false'){
          this.addOrUpdateVisible = false;
        }else{
          this.addOrUpdateVisible = true;
        }
        this.getUserList();
      },
      showEidtOrUpdate(data){
        if(data === 'false'){
          this.editOrUpdateVisible = false;
        }else{
          this.editOrUpdateVisible = true;
        }
        this.getUserList();
      }
    }
  };
</script>

<style scoped>

</style>
