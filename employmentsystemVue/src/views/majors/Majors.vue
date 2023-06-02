<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>专业信息管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-form :inline="true" :model="majorsVo" class="demo-form-inline">
        <el-form-item label="专业名称" label-width="70px">
          <el-input clearable v-model="majorsVo.cname" placeholder="请输入专业名称"></el-input>
        </el-form-item>
        <el-form-item label="专业编号" label-width="70px">
          <el-input clearable v-model="majorsVo.cbianhao" placeholder="请输入专业编号"></el-input>
        </el-form-item>
        <el-form-item label="专业介绍" label-width="70px">
          <el-input clearable v-model="majorsVo.cintroduce" placeholder="请输入专业介绍"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="resetMajorsVo">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getMajorsList">查询</el-button>
          <el-button type="success" icon="el-icon-plus" @click="show">添加</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="majorsList"
        border
        max-height="400"
        style="width: 100%;">
        <el-table-column
          prop="id"
          type="index"
          label="#"
          width="50">
        </el-table-column>

        <el-table-column
          prop="cname"
          label="专业名称"
          width="300">
        </el-table-column>

        <el-table-column
          prop="cbianhao"
          label="专业编号"
          width="300">
        </el-table-column>

        <el-table-column
          prop="cintroduce"
          label="专业介绍"
          width="300">
        </el-table-column>

        <el-table-column
          label="操作" width="250">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="majorsEdit(scope.row)" title="编辑"></el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteMajors(scope.row.id)" title="删除"></el-button>
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

      <MajorsAdd :addOrUpdateVisible="addOrUpdateVisible" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></MajorsAdd>
      <MajorsEdit :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></MajorsEdit>
    </el-card>
  </div>

</template>

<script>
  import { findMajorsList,findMajorsById,deleteMajors } from '../../api/majors';

  import MajorsAdd from './MajorsAdd';
  import MajorsEdit from './MajorsEdit';

  export default {
    name: 'Majors',
    data() {
      return {
        majorsVo: {
          cname: '',
          cbianhao: '',
          cintroduce:''
        },
        addOrUpdateVisible: false,
        editOrUpdateVisible: false,
        majorsList: [],
        size: 6,
        total: 100,
        current: 1
      };
    },
    components: {
      MajorsEdit,
      MajorsAdd
    },
    created(){
      this.getMajorsList();
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      handleSizeChange(val) {
        this.size = val;
        this.getMajorsList();
      },
      handleCurrentChange(val) {
        this.current = val;
        this.getMajorsList();
      },
      async getMajorsList(){
        const { data } = await findMajorsList(this.current,this.size,this.majorsVo);
        this.majorsList = data.data.records;
        this.total = data.data.total;
      },
      resetMajorsVo(){
        this.majorsVo.cname = '';
        this.majorsVo.cbianhao = '';
        this.majorsVo.cintroduce = '';
        this.getMajorsList();
      },
      show(){
        this.addOrUpdateVisible = true;
      },
      deleteMajors(id){
        var that = this;
        this.$confirm('此操作将永久删除该专业, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteMajors(id);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          that.getMajorsList();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      majorsEdit(row){
        var that = this;
         this.editOrUpdateVisible = true;
        this.$nextTick(() => {
            that.$refs.eidtOrUpdateRef.dataInit(row);
        });
         this.getMajorsList();
        console.log(this.majorsEdit);
      },
      showAddOrUpdate(data){
        if(data === 'false'){
          this.addOrUpdateVisible = false;
        }else{
          this.addOrUpdateVisible = true;
        }
        this.getMajorsList();
      },
      showEidtOrUpdate(data){
        if(data === 'false'){
          this.editOrUpdateVisible = false;
        }else{
          this.editOrUpdateVisible = true;
        }
        this.getMajorsList();
      }
    }
  };
</script>

<style scoped>

</style>
