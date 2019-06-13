import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login  from '@/components/Login'
import Register from  '@/components/Register'
import Upload from '@/components/Upload'
Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      components: {default: Login}
    },
    {
      path: '/register',
      name: 'register',
      components: {default: Register}
    },
    {
      path: '/upload',
      name: 'upload',
      components:{default: Upload}
    }
  ]
})
