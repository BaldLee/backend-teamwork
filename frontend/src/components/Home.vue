<template>
<el-container class="container">
  <el-header class="header">
    <h4 v-if="$store.getters.getLogin===true">您已登录 用户名:{{$store.getters.getName}} 用户id:{{$store.getters.getId}}</h4>
    <h4 v-else>请登录</h4>
    <h1>书评网</h1>
  </el-header>
  <el-container>
    <el-aside class="aside">
      <el-menu>
        <el-menu-item-group class="items">
          <el-menu-item index="1" v-if="$store.getters.getLogin===false" @click="goto('/login')">登录</el-menu-item>
          <el-menu-item index="4" v-else @click="logout">注销</el-menu-item>
          <el-menu-item index="2" @click="goto('/register')">注册</el-menu-item>
          <el-menu-item index="3" @click="goto('/upload')">上传</el-menu-item>
        </el-menu-item-group>
      </el-menu>
    </el-aside>
    <el-main class="main" >
      <ul v-if="$store.getters.getLogin" class="book" v-for="book in books.slice(currentPage*3-3, currentPage*3)">
        <li><Book :book="book"></Book></li>
      </ul>
      <div class="button">
      <el-button class="last" @click="last" :disabled="currentPage===1">上一页</el-button>
      <el-button class="next" @click="next" :disabled="currentPage===allPage">下一页</el-button>
      </div>
    </el-main>
  </el-container>
</el-container>
</template>

<script>
  import Book from './Books'
export default {
  name: 'Home',
  data () {
    return {books: "", currentPage: 1, allPage: 1}
  },
  components:{Book},
  created() {
    this.axios({
      url:"/book-service/books",
      method:"get",
      headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
      }
    ).then(
      res => {
        console.log(res.data);
        this.books = res.data;
        this.allPage = Math.ceil(res.data.length/3);
        console.log(this.allPage);
      }
    );
  },
  methods:{
    logout(){

        alert("注销成功！");
        this.$store.dispatch("login", false);

    },
    goto(path){
      this.$router.push(path);
    },
    last(){
      this.currentPage--;
    },
    next(){
      this.currentPage++;
    }

  },
  deactivated(){
    this.$destroyed();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  * {
    padding: 0;
    margin: 0;
    color:pink;
  }
  h4 {
    float:left;
    position: relative;
    left: 83px;
    /*top: 20px;*/
    margin: 0!important;
  }
  h1 {
    text-align: center;
    margin: 0!important;
    position: relative;
    left: 60px;
    /*top :20px;*/
  }
  .header {
    border-bottom: #E6E6E6 1px solid;
    margin-bottom: 20px;

  }
  .items {
    border-bottom: 1px solid #E6E6E6;
    /*border-top: 1px solid #E6E6E6;*/
    padding-bottom: 20px;

  }
  .items * {
    height: 100px;
    font-size: 20px;
    padding: 23px;
  }
  .container {
    width:1000px;
    height:600px;
    margin: 0;
  }
.aside {
  width: 10%;
}
.main {
  height:620px;
  width: 100%;
}
.button {
  display: inline-block;
  position: absolute;
  left: 600px;
  top: 400px;

}
  .el-button {

    padding: 10px;
  }
  .book {
    display: inline-block;
    /*width: 32%;*/
    padding-left: 10px;
  }
</style>
