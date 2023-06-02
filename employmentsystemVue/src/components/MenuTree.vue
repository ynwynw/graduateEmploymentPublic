<template>
  <div>
    <template v-for="item in this.menuList">
      <el-submenu :disabled="item.disabled" :index="item.id+''" :key="item.id+''" v-if="item.children.length>0">
        <template slot="title">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.menuName }}</span>
        </template>
        <MenuTree :menuList="item.children"></MenuTree>
      </el-submenu>
      <el-menu-item
        v-else
        :disabled="item.disabled"
        :index="item.url+''"
        :route="item.url+''"
        :key="item.id"
        @click="savePath(item.url)">

        <i :class="item.icon"></i>
        <span slot="title">{{ item.menuName }}</span>
      </el-menu-item>
    </template>
  </div>
</template>

<script>
  export default {
    name: 'MenuTree',
    props: ['menuList'],
    methods:{
      // 保存激活路径
      savePath(path) {
         // alert(path);
        window.sessionStorage.setItem("activePath", path);
        // 调用Main.vue的activePath
        this.activePath = path;
        // this.$router.push(this.activePath);
      }
    }
  };
</script>

<style scoped>
  /*实现了一个溢出处理*/
  .el-menu--collapse span,
  .el-menu--collapse i.el-submenu__icon-arrow {
    height: 0;
    width: 0;
    overflow: hidden;
    visibility: hidden;
    display: inline-block;
  }

</style>
