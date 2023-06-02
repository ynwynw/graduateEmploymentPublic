<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>辅导员信息管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-form :inline="true" :model="userVo" class="demo-form-inline">
        <el-form-item label="姓名" label-width="70px">
          <el-input clearable v-model="userVo.sname" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话" label-width="70px">
          <el-input clearable v-model="userVo.stel" placeholder="请输入电话"></el-input>
        </el-form-item>
        <el-form-item label="专业" label-width="70px">
          <el-input clearable v-model="userVo.smajor" placeholder="请输入专业"></el-input>
        </el-form-item>
        <el-form-item label="班级" label-width="70px">
          <el-input clearable v-model="userVo.sclass" placeholder="请输入班级"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="resetUserVo">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getUserList">查询</el-button>
          <el-button type="success" icon="el-icon-plus" @click="show">添加</el-button>
        </el-form-item>
      </el-form>
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
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteUsers(scope.row.id)" title="删除"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        style="padding-top: 15px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="[6, 10, 20, 30]"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

      <InstructorsAdd :addOrUpdateVisible="addOrUpdateVisible" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></InstructorsAdd>
      <InstructorsEdit :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></InstructorsEdit>
    </el-card>
  </div>

</template>

<script>
  import { findInstructorsList,findUserById,deleteInstructors } from '../../api/instructors';

  import InstructorsAdd from './InstructorsAdd';
  import InstructorsEdit from './InstructorsEdit';

  export default {
    name: 'Instructors',
    data() {
      return {
        userVo: {
          sname: '',
          stel: '',
          smajor: '',
          sclass: ''
        },
        addOrUpdateVisible: false,
        editOrUpdateVisible: false,
        userList: [],
        size: 6,
        total: 100,
        current: 1
      };
    },
    components: {
      InstructorsEdit,
      InstructorsAdd
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
        const { data } = await findInstructorsList(this.current,this.size,this.userVo);
        this.userList = data.data.records;
        this.total = data.data.total;
      },
      resetUserVo(){
        this.userVo.sname = '';
        this.userVo.stel = '';
        this.userVo.smajor = '';
        this.userVo.sclass= '';
        this.getUserList();
      },
      show(){
        this.addOrUpdateVisible = true;
      },
      deleteUsers(id){
        var that = this;
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteInstructors(id);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          that.getUserList();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
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
