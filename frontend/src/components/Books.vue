<template>
  <el-container class="container">
    <el-header class="header">
      <img :src="'http://localhost:8080/image-service/books/'+book.id" :alt="book.id">
    </el-header>
    <el-main>
      <h4>{{this.book.title}}({{this.book.author}})</h4>
      <el-rate v-model="rate" :colors="colors" :disabled="disabled" @change="rateBook">
      </el-rate>
    </el-main>


  </el-container>
</template>

<script>
  export default {
    name: "Book",
    props:["book"],
    data() {
      return {image:"", rate:1, colors:['#99A9BF', '#F7BA2A', '#FF9900'], disabled:false}
    },
    methods:{
      rateBook() {
        this.disabled=true;
        this.axios({
          url:"/rating-book-service/insertRate",
          method:"get",
          params:{userId:this.$store.getters.getId,bookId:this.book.id,point:this.rate},
        });
      },
      init(){
        this.rate = 1;
    this.disabled = false;//要看是否已经评分或登录
    if (this.$store.getters.getLogin)
    this.axios({
      url:"/rating-book-service/getRate/",
      method:"get",
      params:{userId:this.$store.getters.getId, bookId:this.book.id},
       headers: {'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'},
    }).then(res=>{
      if (res.data.point > 0)
    {
      this.rate = res.data.point;
      this.disabled=true;}
    });
      }
    },
   created() {
     this.init();
    },
    watch:{
      book(to, from) {
        this.init();
      }
    },
    deactivated(){this.$destroy();}
  }
</script>

<style scoped>
img {
  width: 200px;
  height: 230px;
}
  .container {
    margin: 10px auto;
    width: 210px;
  }
  .header {
    height: 200px!important;
  }
</style>
