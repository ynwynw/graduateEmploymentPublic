import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router';
import store from './store';
import './assets/css/global.css';
import axios from 'axios';
import VueSession from 'vue-session';

Vue.use(ElementUI);
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.use(VueSession);

new Vue({
  router,
  store,
  render: (h) => {return h(App);}
}).$mount('#app');
