<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" prop="userName">
      <el-input type="text" v-model="ruleForm.userName" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input type="password" v-model="ruleForm.pass" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="手机" prop="phone">
      <el-input type="tel" v-model="ruleForm.phone" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input type="email" v-model="ruleForm.email" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="名字" prop="name">
      <el-input type="text" v-model="ruleForm.name" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: 'register',
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      let validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      let validateName = (rule, value, callback) => {
        if (value === ''){
          callback(new Error('用户名不能为空'));
        } else if (this.repeat(value))
        {
          callback(new Error('用户名已存在'));
        } else callback();
      };
      return {
        ruleForm: {
          pass: '',
          checkPass: '',
          phone: '',
          email:'',
          name:'',
          userName:''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          userName:[
            {validator: validateName, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert("注册成功！");
            this.axios({
              url:"localhost:8080/user-service/signup/process",
              params:{username:this.ruleForm.userName, password:this.ruleForm.pass, phone:this.ruleForm.phone, email:this.ruleForm.email, address:this.ruleForm.address, realname:this.ruleForm.name}
             , headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
            });
            this.$router.push("/login");
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      async repeat(name) {
        let b = true;
        let a = await this.axios(
          {
            url:"localhost:8080/user-service/checkUser",
            params:{username: name},
            method:"get",
            headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
          }
        ).then(res => {b = res.data;});
      return b;
      }
    }

  }
</script>

<style>

</style>
