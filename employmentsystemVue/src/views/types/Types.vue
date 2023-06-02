<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>类型信息管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-form :inline="true" :model="dataVo" class="demo-form-inline">
        <el-form-item label="类型名称" label-width="70px">
          <el-input clearable v-model="dataVo.tname" placeholder="请输入类型名称"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="resetDataVo">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getDataList">查询</el-button>
          <el-button type="success" icon="el-icon-plus" @click="show">添加</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="dataList"
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
          prop="tname"
          label="类型名称"
          width="300">
        </el-table-column>

        <el-table-column
          prop="tnums"
          label="简历数"
          width="300">
        </el-table-column>

        <el-table-column
          label="操作" width="250">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="dataEdit(scope.row)" title="编辑"></el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteData(scope.row.id)" title="删除"></el-button>
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

      <TypesAdd :addOrUpdateVisible="addOrUpdateVisible" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></TypesAdd>
      <TypesEdit :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></TypesEdit>
    </el-card>
  </div>

</template>

<script>
  import { findTypesList,findTypesById,deleteTypes } from '../../api/types';

  import TypesAdd from './TypesAdd';
  import TypesEdit from './TypesEdit';

  export default {
    name: 'Types',
    data() {
      return {
        dataVo: {
          tname: ''
        },
        addOrUpdateVisible: false,
        editOrUpdateVisible: false,
        dataList: [],
        size: 6,
        total: 100,
        current: 1
      };
    },
    components: {
      TypesEdit,
      TypesAdd
    },
    created(){
      this.getDataList();
    },
    methods: {
      onSubmit() {
      },
      handleSizeChange(val) {
        this.size = val;
        this.getDataList();
      },
      handleCurrentChange(val) {
        this.current = val;
        this.getDataList();
      },
      async getDataList(){
        const { data } = await findTypesList(this.current,this.size,this.dataVo);
        this.dataList = data.data.records;
        this.total = data.data.total;
      },
      resetDataVo(){
        this.dataVo.tname = '';
        this.getDataList();
      },
      show(){
        this.addOrUpdateVisible = true;
      },
      deleteData(id){
        var that = this;
        this.$confirm('此操作将永久删除该类型, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteTypes(id);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          that.getDataList();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      dataEdit(row){
        var that = this;
         this.editOrUpdateVisible = true;
        this.$nextTick(() => {
            that.$refs.eidtOrUpdateRef.dataInit(row);
        });
         this.getDataList();
      },
      showAddOrUpdate(data){
        if(data === 'false'){
          this.addOrUpdateVisible = false;
        }else{
          this.addOrUpdateVisible = true;
        }
        this.getDataList();
      },
      showEidtOrUpdate(data){
        if(data === 'false'){
          this.editOrUpdateVisible = false;
        }else{
          this.editOrUpdateVisible = true;
        }
        this.getDataList();
      }
    }
  };
</script>

<style scoped>

</style>
