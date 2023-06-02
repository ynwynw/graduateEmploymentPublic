import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => {return import('../views/Login.vue');}
  },
  {
    path: '/main',
    name: 'Main',
    component: () => {return import('../views/Main.vue');},
    children:[
      {
        path:'/indexadmins',
        component:() => {return import('../views/admins/indexAdmins.vue');},
        meta:{ title:'首页' }
      },{
        path:'/admins',
        component:() => {return import('../views/admins/Admins.vue');},
        meta:{ title:'个人信息管理' }
      },
      {
        path:'/users',
        component:() => {return import('../views/users/Users.vue');},
        meta:{ title:'学生信息管理' }
      },
      {
        path:'/companys',
        component:() => {return import('../views/companys/Companys.vue');},
        meta:{ title:'公司企业信息管理' }
      },
      {
        path:'/instructors',
        component:() => {return import('../views/instructors/Instructors.vue');},
        meta:{ title:'辅导员信息管理' }
      },
      {
        path:'/colleges',
        component:() => {return import('../views/colleges/Colleges.vue');},
        meta:{ title:'学院信息管理' }
      },
      {
        path:'/majors',
        component:() => {return import('../views/majors/Majors.vue');},
        meta:{ title:'专业信息管理' }
      },
      {
        path:'/classes',
        component:() => {return import('../views/classes/Classes.vue');},
        meta:{ title:'班级信息管理' }
      },
      {
        path:'/recruits',
        component:() => {return import('../views/recruits/Recruits.vue');},
        meta:{ title:'岗位信息管理' }
      },
      {
        path:'/types',
        component:() => {return import('../views/types/Types.vue');},
        meta:{ title:'类型信息管理' }
      },
      {
        path:'/resumes',
        component:() => {return import('../views/resumes/Resumes.vue');},
        meta:{ title:'简历信息管理' }
      },
      {
        path:'/myapplys2',
        component:() => {return import('../views/resumes/MyApplys2.vue');},
        meta:{ title:'学生三方协议书审核' }
      },
      {
        path:'/classestongji',
        component:() => {return import('../views/tongjis/ClassesTongji.vue');},
        meta:{ title:'按班级就业统计' }
      },
      {
        path:'/tongji',
        component:() => {return import('../views/colleges/Tongji.vue');},
        meta:{ title:'按班级就业统计' }
      },
      {
        path:'/majorstongji',
        component:() => {return import('../views/tongjis/MajorsTongji.vue');},
        meta:{ title:'按专业就业统计' }
      },
      {
        path:'/companyestongji',
        component:() => {return import('../views/tongjis/CompanyesTongji.vue');},
        meta:{ title:'按企业就业统计' }
      },
      {
        path:'/recruitstongji',
        component:() => {return import('../views/tongjis/RecruitsTongji.vue');},
        meta:{ title:'按岗位就业统计' }
      }

    ]
  },
  {
    path: '/main2',
    name: 'Main2',
    component: () => {return import('../views/Main2.vue');},
    children:[
      {
        path:'/companys1',
        component:() => {return import('../views/companys/Companys2.vue');},
        meta:{ title:'公司信息管理' }
      },
      {
        path:'/recruits1',
        component:() => {return import('../views/recruits/Recruits1.vue');},
        meta:{ title:'岗位信息管理' }
      },
      {
        path:'/resumes2',
        component:() => {return import('../views/resumes/Resumes2.vue');},
        meta:{ title:'简历信息查看' }
      },
      {
        path:'/myapplys1',
        component:() => {return import('../views/resumes/MyApplys1.vue');},
        meta:{ title:'应聘信息管理' }
      }

    ]
  },
  {
    path: '/main3',
    name: 'Main3',
    component: () => {return import('../views/Main3.vue');},
    children:[
      {
        path:'/instructors1',
        component:() => {return import('../views/instructors/Instructors1.vue');},
        meta:{ title:'个人信息管理' }
      },
      {
        path:'/users1',
        component:() => {return import('../views/users/Users1.vue');},
        meta:{ title:'学生用户信息管理' }
      },
      {
        path:'/myapplys3',
        component:() => {return import('../views/resumes/MyApplys3.vue');},
        meta:{ title:'学生三方协议书审核' }
      },
      {
        path:'/classestongji2',
        component:() => {return import('../views/tongjis/ClassesTongji2.vue');},
        meta:{ title:'按班级就业统计' }
      }

    ]
  },
  {
    path: '/main4',
    name: 'Main4',
    component: () => {return import('../views/Main4.vue');},
    children:[
      {
        path:'/users2',
        component:() => {return import('../views/users/Users2.vue');},
        meta:{ title:'个人信息管理' }
      },
      {
        path:'/recruits2',
        component:() => {return import('../views/recruits/Recruits2.vue');},
        meta:{ title:'查看企业岗位信息' }
      },
      {
        path:'/resumes1',
        component:() => {return import('../views/resumes/Resumes1.vue');},
        meta:{ title:'简历信息管理' }
      },
      {
        path:'/myapplys',
        component:() => {return import('../views/resumes/MyApplys.vue');},
        meta:{ title:'我的应聘' }
      }

    ]
  }

];

const router = new VueRouter({
  routes
});

export default router;
