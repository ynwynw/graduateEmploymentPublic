<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>班级信息管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-form :inline="true" :model="classesVo" class="demo-form-inline">
        <el-form-item label="班级名称" label-width="70px">
          <el-input clearable v-model="classesVo.cname" placeholder="请输入班级名称"></el-input>
        </el-form-item>
        <el-form-item label="班主任" label-width="70px">
          <el-input clearable v-model="classesVo.cheadmaster" placeholder="请输入班主任"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" label-width="70px">
          <el-input clearable v-model="classesVo.ctel" placeholder="请输入联系电话"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="resetClassesVo">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getClassesList">查询</el-button>
          <el-button type="success" icon="el-icon-plus" @click="show">添加</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="classesList"
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
          label="班级名称"
          width="300">
        </el-table-column>

        <el-table-column
          prop="cheadmaster"
          label="班主任"
          width="300">
        </el-table-column>

        <el-table-column
          prop="ctel"
          label="联系电话"
          width="300">
        </el-table-column>

        <el-table-column
          label="操作" width="250">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="classesEdit(scope.row)" title="编辑"></el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteClasses(scope.row.id)" title="删除"></el-button>
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

      <ClassesAdd :addOrUpdateVisible="addOrUpdateVisible" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></ClassesAdd>
      <ClassesEdit :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></ClassesEdit>
    </el-card>
  </div>

</template>

<script>
  import { findClassesList,findClassesById,deleteClasses } from '../../api/classes';

  import ClassesAdd from './ClassesAdd';
  import ClassesEdit from './ClassesEdit';

  export default {
    name: 'Classes',
    data() {
      return {
        classesVo: {
          cname: '',
          cheadmaster: '',
          ctel:''
        },
        addOrUpdateVisible: false,
        editOrUpdateVisible: false,
        classesList: [],
        size: 6,
        total: 100,
        current: 1
      };
    },
    components: {
      ClassesEdit,
      ClassesAdd
    },
    created(){
      this.getClassesList();
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      handleSizeChange(val) {
        this.size = val;
        this.getClassesList();
      },
      handleCurrentChange(val) {
        this.current = val;
        this.getClassesList();
      },
      async getClassesList(){
        const { data } = await findClassesList(this.current,this.size,this.classesVo);
        this.classesList = data.data.records;
        this.total = data.data.total;
      },
      resetClassesVo(){
        this.classesVo.cname = '';
        this.classesVo.cheadmaster = '';
        this.classesVo.ctel = '';
        this.getClassesList();
      },
      show(){
        this.addOrUpdateVisible = true;
      },
      deleteClasses(id){
        var that = this;
        this.$confirm('此操作将永久删除该班级, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteClasses(id);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          that.getClassesList();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      classesEdit(row){
        var that = this;
         this.editOrUpdateVisible = true;
        this.$nextTick(() => {
            that.$refs.eidtOrUpdateRef.dataInit(row);
        });
         this.getClassesList();
        console.log(this.classesEdit);
      },
      showAddOrUpdate(data){
        if(data === 'false'){
          this.addOrUpdateVisible = false;
        }else{
          this.addOrUpdateVisible = true;
        }
        this.getClassesList();
      },
      showEidtOrUpdate(data){
        if(data === 'false'){
          this.editOrUpdateVisible = false;
        }else{
          this.editOrUpdateVisible = true;
        }
        this.getClassesList();
      }
    }
  };
</script>

<style scoped>

</style>
