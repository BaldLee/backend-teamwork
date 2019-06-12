<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="书名" prop="title">
      <el-input type="text" v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="编号" prop="id">
      <el-input type="number" v-model="ruleForm.id" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="作者" prop="author">
      <el-input type="number" v-model="ruleForm.author" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-upload
        class="upload-demo"
        action="https://jsonplaceholder.typicode.com/posts/"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="1"
        :on-exceed="handleExceed"
        :file-list="fileList">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">上传</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
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
          author:'',
          id:''
        },
        rules: {
          title: [
            {validator: validateTitle, trigger: 'blur'}
          ]
        },
        fileList:""
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid && this.fileList!=="" && this.fileList!==null) {
            alert("上传成功！");
            //传图片
            // this.axios({
            //   url:"localhost:8080/user-service/signup/process",
            //   params:{username:this.ruleForm.userName, password:this.ruleForm.pass, phone:this.ruleForm.phone, email:this.ruleForm.email, address:this.ruleForm.address, realname:this.ruleForm.name}
            //   , headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
            // });
            this.$router.push("/");
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
