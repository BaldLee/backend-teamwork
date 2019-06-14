 
import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const state={
    isLogin: false,
    userName: "",
    userId:"",
    token:""
  };
const getters = {   //实时监听state值的变化(最新状态)
    getLogin(state) {  //方法名随意,主要是来承载变化的showFooter的值
       return state.isLogin
    },
    getName(){  //方法名随意,主要是用来承载变化的changableNum的值
       return state.userName
    },
    getId(){
        return state.userId
    }
};
const mutations = {
    setLogin(state, b) {   //自定义改变state初始值的方法，这里面的参数除了state之外还可以再传额外的参数(变量或对象);
        state.isLogin = b;
    },
    setName(state, n) {  //同上
        state.userName = n;
    },
    setId(state, id){ //同上，这里面的参数除了state之外还传了需要增加的值sum
       state.userId = id;
    },
    set_token(state, token) {
        state.token = token
        sessionStorage.token = token
        },
    del_token(state) {
        state.token = ''
        sessionStorage.removeItem('token')
    }
};
const actions = {
    login(context, num) {  //自定义触发mutations里函数的方法，context与store 实例具有相同方法和属性
        context.commit('setLogin', num);
    },
    name(context, num) {  //同上注释
        context.commit('setName', num);
    },
    id(context, num){   //同上注释，num为要变化的形参
        context.commit('setId',num)
     }
};
const store = new Vuex.Store({
    state,
    getters,
    mutations,
    actions
});
 
export default store;