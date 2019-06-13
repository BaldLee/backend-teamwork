<template>
  <el-container class="container">
    <el-header class="header">
      <img :src="'localhost:8080/image-service/'+book.id" :alt="book.id">
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
        //评分this.books this.rate
      }
    },
   created() {
    this.disabled = false;//要看是否已经评分或登录
    }
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
