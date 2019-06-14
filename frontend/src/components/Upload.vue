<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="书名" prop="title">
      <el-input type="text" v-model="ruleForm.title"></el-input>
    </el-form-item>
    <el-form-item label="作者" prop="author">
      <el-input type="text" v-model="ruleForm.author" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-upload
        class="upload-demo"
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple
        :limit="1"
        :file-list="fileList">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
      <form action="/book-service/insert"></form>
    </el-form-item>
    <!-- 
       <el-form-item action="/book-service/insert" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="file"></el-form-item> -->
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">上传</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
<!-- 
     <input type="submit" value="上传">  -->

</template>

<script>
  export default {
    name:"upload",
    data(){
      let validateTitle = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入书名'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          title: '',
          author:''
        },
        rules: {
          title: [
            {validator: validateTitle, trigger: 'blur'}
          ]
        },
        fileList:[]
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid && this.fileList!=="" && this.fileList!==null) {
            let formData= new FormData();
            formData.append("book", this.fileList[0]);
              this.axios({
              url:"/book-service/insert",
              method:"post",
              auth:{username:"123",password:"123"},
              data:{author: this.ruleForm.author, title: this.ruleForm.title}
              , headers:{'Content-Type':'application/json;charset=UTF-8','Authorization':this.$store.state.token}
            }).then(res=>{
            this.axios({
              url:"/image-service/books/"+res.data,
              method:"post",
              data:formData
              , headers:{'Content-Type':'multipart/form-data;charset=UTF-8'}
            });
            }).then(res=>{
            alert("上传成功！");
            this.$router.push("/");
            });
            
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>

</style>
