<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>首页</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row>
      <el-col :span="6" class="col1">
        学生人数 : {{userstotal}}
      </el-col>
      <el-col :span="6" class="col2">
        企业数 : {{companystotal}}
      </el-col>
      <el-col :span="6" class="col1">
        岗位数 : {{resurtstotal}}
      </el-col>
      <el-col :span="6" class="col2">
        简历数 : {{resumestotal}}
      </el-col>
    </el-row>
    <!-- 第二列布局 -->
    <el-row>
      <el-col :span="24" class="col3">
        按专业统计
        <el-table
          :data="majordataList"
          border
          max-height="400"
          style="width: 100%;">
          <el-table-column
            prop="id"
            label="#"
            width="50">
          </el-table-column>
          <el-table-column
            prop="cname"
            label="专业名称"
            width="300">
          </el-table-column>

          <el-table-column
            prop="ctotalnums"
            label="总人数"
            width="150">
          </el-table-column>

          <el-table-column
            prop="cnums"
            label="就业人数"
            width="150">
          </el-table-column>

          <el-table-column
            prop="crates"
            label="就业率"
            width="150">
          </el-table-column>
        </el-table>

        按学院统计
        <el-table
          :data="collegedataList"
          border
          max-height="400"
          style="width: 100%;">
          <el-table-column
            prop="id"
            label="#"
            width="50">
          </el-table-column>

          <el-table-column
            prop="cname"
            label="学院名称"
            width="300">
          </el-table-column>

          <el-table-column
            prop="ctotalnums"
            label="总人数"
            width="150">
          </el-table-column>

          <el-table-column
            prop="cnums"
            label="就业人数"
            width="150">
          </el-table-column>

          <el-table-column
            prop="crates"
            label="就业率"
            width="150">
          </el-table-column>

        </el-table>

        按班级统计
        <el-table
          :data="dataList"
          border
          max-height="400"
          style="width: 100%;">
          <el-table-column
            prop="id"
            label="#"
            width="50">
          </el-table-column>

          <el-table-column
            prop="cname"
            label="班级名称"
            width="300">
          </el-table-column>

          <el-table-column
            prop="ctotalnums"
            label="总人数"
            width="150">
          </el-table-column>

          <el-table-column
            prop="cnums"
            label="就业人数"
            width="150">
          </el-table-column>

          <el-table-column
            prop="crates"
            label="就业率"
            width="150">
          </el-table-column>

        </el-table>
      </el-col>
    </el-row>
  </div>
</template>

<script>

  import { findAdminsPage } from '../../api/admins';
  import { findUserList2 } from '../../api/users';
  import { findCompanysList2 } from '../../api/companys';
  import { findResurtsDatasList2 } from '../../api/recruits';
  import { findResumesDatasList2 } from '../../api/resumes';
  import { findClassesTongjiList3 } from '../../api/classes';
  import { findMajorTongjiList } from '../../api/majors';
  import { findCollegesTongjiList } from '../../api/colleges';

  export default {
    name: 'Admins',
    data(){
      return {
        adminsList: [],
        userstotal: 100,
        companystotal: 100,
        resurtstotal: 100,
        resumestotal: 100,
        dataList: [],
        collegedataList: [],
        majordataList: [],
        editOrUpdateVisible: false
      };
    },
    components: {
    },
    created() {
      this.findUserList();
      this.findCompanyeList();
      this.findResurtsList();
      this.findResumesList();
      this.getClassesDataList();
      this.findMajorTongjiList();
      this.findCollegesTongjiList();
    },
    methods:{
      async findCollegesTongjiList(){
        const { data } = await findCollegesTongjiList();
        this.collegedataList = data.data.records;
      },
      async getClassesDataList(){
        const { data } = await findClassesTongjiList3();
        this.dataList = data.data.records;
      },
      async findMajorTongjiList(){
        const { data } = await findMajorTongjiList();
        this.majordataList = data.data.records;
      },
      async findUserList(){
        const { data } = await findUserList2();
        this.userstotal = data.data.total;
      },
      async findCompanyeList(){
        const { data } = await findCompanysList2();
        this.companystotal = data.data.total;
      },
      async findResurtsList(){
        const { data } = await findResurtsDatasList2();
        this.resurtstotal = data.data.total;
      },
      async findResumesList(){
        const { data } = await findResumesDatasList2();
        this.resumestotal = data.data.total;
      },
      adminsEdit(row){
        var that = this;
        this.editOrUpdateVisible = true;
        this.$nextTick(() => {
          that.$refs.eidtOrUpdateRef.dataInit(row);
        });
      },
      showEidtOrUpdate(data){
        if(data === 'false'){
          this.editOrUpdateVisible = false;
        }else{
          this.editOrUpdateVisible = true;
        }
      }
    }
  };

</script>

<style scoped>
  .col1 {
    background-color: teal;
    height: 200px;
    text-align: center;
    line-height: 200px;
    color: #fff;
    font-size: 30px;
  }
  .col2 {
    background-color: tomato;
    height: 200px;
    text-align: center;
    line-height: 200px;
    color: #fff;
    font-size: 30px;
  }
  .col3 {
    background-color: thistle;
    height: 600px;
    text-align: center;
    font-size: 25px;
  }
</style>
