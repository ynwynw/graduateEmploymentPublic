<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>查看企业岗位信息</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-form :inline="true" :model="dataVo" class="demo-form-inline">
        <el-form-item label="岗位名称" label-width="70px">
          <el-input clearable v-model="dataVo.rtitle" placeholder="请输入岗位名称"></el-input>
        </el-form-item>
        <el-form-item label="岗位介绍" label-width="70px">
          <el-input clearable v-model="dataVo.rintroduce" placeholder="请输入岗位介绍"></el-input>
        </el-form-item>
        <el-form-item label="企业名称" label-width="70px">
          <el-input clearable v-model="dataVo.cname" placeholder="请输入企业名称"></el-input>
        </el-form-item>
        <el-form-item label="企业电话" label-width="70px">
          <el-input clearable v-model="dataVo.ctel" placeholder="请输入企业电话"></el-input>
        </el-form-item>
        <el-form-item label="企业位置" label-width="70px">
          <el-input clearable v-model="dataVo.cattrs" placeholder="请输入企业位置"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="resetDataVo">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getDataList">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="dataList"
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
          prop="rtitle"
          label="岗位名称"
          width="120">
        </el-table-column>
        <el-table-column
          prop="rwage"
          label="岗位薪资"
          width="70">
        </el-table-column>
        <el-table-column
          sortable
          prop="rdate"
          label="添加日期"
          width="100">
          <template slot-scope="scope">
            {{scope.row.rdate}}
          </template>
        </el-table-column>
        <el-table-column
          prop="rnums"
          label="需要人数"
          width="50">
        </el-table-column>
        <el-table-column
          prop="cname"
          label="企业名称"
          width="150">
        </el-table-column>
        <el-table-column
          prop="ctel"
          label="企业电话"
          width="120">
        </el-table-column>
        <el-table-column
          prop="cattrs"
          label="企业地址"
          width="120">
        </el-table-column>
        <el-table-column
          prop="cmajor"
          label="企业服务范围"
          width="180">
        </el-table-column>
        <el-table-column
          sortable
          prop="rwant"
          label="岗位要求"
          width="250">
        </el-table-column>
        <el-table-column
          prop="rintroduce"
          label="岗位介绍"
          width="280">
        </el-table-column>
        <el-table-column
          label="操作" width="150">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-check" @click="dataEdit(scope.row)" title="我要应聘"></el-button>
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
      <RecruitsEdit1 :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></RecruitsEdit1>
    </el-card>
  </div>

</template>

<script>
  import { findDatasList } from '../../api/recruits';
  import RecruitsEdit1 from './RecruitsEdit1';

  export default {
    name: 'Recruits2',
    data() {
      return {
        dataVo: {
          rtitle:'',
          rintroduce:'',
          cname: '',
          ctel: '',
          cattrs: ''
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
      RecruitsEdit1
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
        const { data } = await findDatasList(this.current,this.size,this.dataVo);
        this.dataList = data.data.records;
        this.total = data.data.total;
      },
      resetDataVo(){
        this.dataVo.cname = '';
        this.dataVo.ctel = '';
        this.dataVo.rtitle = '';
        this.dataVo.cattrs= '';
        this.dataVo.rintroduce= '';
        this.getDataList();
      },
      show(){
        this.addOrUpdateVisible = true;
      },
      dataEdit(row){
        var that = this;
         this.editOrUpdateVisible = true;
        this.$nextTick(() => {
            that.$refs.eidtOrUpdateRef.dataInit(row);
        });
         this.getDataList();
        console.log(this.dataEdit);
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
