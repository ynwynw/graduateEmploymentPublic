<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>学院信息管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-form :inline="true" :model="collegesVo" class="demo-form-inline">
        <el-form-item label="学院名称" label-width="70px">
          <el-input clearable v-model="collegesVo.cname" placeholder="请输入学院名称"></el-input>
        </el-form-item>
        <el-form-item label="学院编号" label-width="70px">
          <el-input clearable v-model="collegesVo.cbianhao" placeholder="请输入学院编号"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="resetCollegesVo">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getCollegesList">查询</el-button>
          <el-button type="success" icon="el-icon-plus" @click="show">添加</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="collegesList"
        border
        max-height="400"
        style="width: 100%;">
        <el-table-column
          prop="id"
          label="#"
          type="index"
          width="50">
        </el-table-column>

        <el-table-column
          prop="cname"
          label="学院名称"
          width="300">
        </el-table-column>

        <el-table-column
          prop="cbianhao"
          label="学院编号"
          width="300">
        </el-table-column>

        <el-table-column
          label="操作" width="250">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="collegesEdit(scope.row)" title="编辑"></el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteColleges(scope.row.id)" title="删除"></el-button>
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

      <CollegesAdd :addOrUpdateVisible="addOrUpdateVisible" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></CollegesAdd>
      <CollegesEdit :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></CollegesEdit>
    </el-card>
  </div>

</template>

<script>
  import { findCollegesList,findCollegesById,deleteColleges } from '../../api/colleges';

  import CollegesAdd from './CollegesAdd';
  import CollegesEdit from './CollegesEdit';

  export default {
    name: 'Colleges',
    data() {
      return {
        collegesVo: {
          cname: '',
          cbianhao: ''
        },
        addOrUpdateVisible: false,
        editOrUpdateVisible: false,
        collegesList: [],
        size: 6,
        total: 100,
        current: 1
      };
    },
    components: {
      CollegesEdit,
      CollegesAdd
    },
    created(){
      this.getCollegesList();
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      handleSizeChange(val) {
        this.size = val;
        this.getCollegesList();
      },
      handleCurrentChange(val) {
        this.current = val;
        this.getCollegesList();
      },
      async getCollegesList(){
        const { data } = await findCollegesList(this.current,this.size,this.collegesVo);
        this.collegesList = data.data.records;
        this.total = data.data.total;
      },
      resetCollegesVo(){
        this.collegesVo.cname = '';
        this.collegesVo.cbianhao = '';
        this.getCollegesList();
      },
      show(){
        this.addOrUpdateVisible = true;
      },
      deleteColleges(id){
        var that = this;
        this.$confirm('此操作将永久删除该学院, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteColleges(id);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          that.getCollegesList();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
       collegesEdit(row){
        var that = this;
         this.editOrUpdateVisible = true;
        this.$nextTick(() => {
            that.$refs.eidtOrUpdateRef.dataInit(row);
        });
         this.getCollegesList();
        console.log(this.collegesEdit);
      },
      showAddOrUpdate(data){
        if(data === 'false'){
          this.addOrUpdateVisible = false;
        }else{
          this.addOrUpdateVisible = true;
        }
        this.getCollegesList();
      },
      showEidtOrUpdate(data){
        if(data === 'false'){
          this.editOrUpdateVisible = false;
        }else{
          this.editOrUpdateVisible = true;
        }
        this.getCollegesList();
      }
    }
  };
</script>

<style scoped>

</style>
