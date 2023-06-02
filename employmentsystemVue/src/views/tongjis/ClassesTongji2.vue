<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>按班级统计</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
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
    </el-card>
  </div>

</template>

<script>
  import { findClassesTongjiList2 } from '../../api/classes';

  export default {
    name: 'ClassesTongji2',
    data() {
      return {
        addOrUpdateVisible: false,
        editOrUpdateVisible: false,
        dataList: [],
        size: 6,
        total: 100,
        current: 1
      };
    },
    components: {
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
        const { data } = await findClassesTongjiList2(this.current,this.size,this.$session.get("phone"));
        this.dataList = data.data.records;
        this.total = data.data.total;
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
