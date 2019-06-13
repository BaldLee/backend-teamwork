<template>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="ruleForm.userName"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
</template>

<script>
  export default {
    name: 'login',
    data() {
            let validatePass = (rule, value, callback) => {
              if (value === '') {
                callback(new Error('请输入密码'));
              } else {
                if (this.ruleForm.checkPass !== '') {
                  this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
              }
            };
            let checkName = (rule, value, callback)=>{
              if (value==='') callback(new Error('请输入用户名'));
              else callback();
            };
            return {
              ruleForm: {
                pass: '',
                userName:''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          userName: [
            { validator: checkName, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      async validateName(userName, password){
        let b = "";
        let a= await this.axios({
          url:"localhost:8080/user-service/login/check",
          methods:"post",
          data:{usn:userName, psw:password},
          headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
        }).then(res =>{b= res.data});
        return b === "Succeed";
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid && this.validateName(this.ruleForm.userName, this.ruleForm.pass)) {
            alert('登录成功！');
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

<style scoped>


</style>
