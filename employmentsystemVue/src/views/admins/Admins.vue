<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>管理员管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-table
        :data="adminsList"
        border
        max-height="400"
        style="width: 100%;">
        <el-table-column
          prop="id"
          label="#"
          width="50">
        </el-table-column>
        <el-table-column
          prop="uname"
          label="用户名"
          width="250">
        </el-table-column>
        <el-table-column
          prop="utel"
          label="电话"
          width="200">
        </el-table-column>
        <el-table-column
          prop="upwd"
          label="密码"
          width="180">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="adminsEdit(scope.row)" title="编辑"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <AdminsEdit :addOrUpdateVisible="editOrUpdateVisible" @changeShow="showEidtOrUpdate" ref="eidtOrUpdateRef"></AdminsEdit>
    </el-card>
  </div>
</template>

<script>

  import { findAdminsPage } from '../../api/admins';
  import AdminsEdit from './AdminsEdit';

  export default {
    name: 'Admins',
    data(){
      return {
        adminsList: [],
        editOrUpdateVisible: false
      };
    },
    components: {
      AdminsEdit
    },
    created() {
      this.getAdminsPage();
    },
    methods:{
      async getAdminsPage(){
        const { data } = await findAdminsPage();
        this.adminsList = data.data.admins;
      },
      adminsEdit(row){
        var that = this;
        this.editOrUpdateVisible = true;
        this.$nextTick(() => {
          that.$refs.eidtOrUpdateRef.dataInit(row);
        });
        this.getAdminsPage();
      },
      showEidtOrUpdate(data){
        if(data === 'false'){
          this.editOrUpdateVisible = false;
        }else{
          this.editOrUpdateVisible = true;
        }
        this.getCompanysList();
      }
    }
  };

</script>

<style scoped>

</style>
