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
                callback();
              }
            };
            let checkName = (rule, value, callback)=>{
              if (value==='') callback(new Error('请输入用户名'));
              else callback();
            };
            return {b:"",
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
      validateName(userName, password){

      },
      async submitForm(formName) {
      this.b = "fail";
       this.axios({
          url:"/user-service/login/check",
          method:"get",
          params:{usn:this.ruleForm.userName, psw:this.ruleForm.pass},
          headers:{'Content-Type':'application/json;charset=UTF-8'}
        }).then(res =>{
          this.b= res.data;
          console.log(res.data.token)
          if (this.b[0]==='S') this.$store.commit('set_token', res.data.token);
          }).then(
          res=>{
       this.$refs[formName].validate((valid) => {
          if (valid && this.b[0]==='S') {
            alert('登录成功！');
            this.$store.dispatch('login', true);
            this.$store.dispatch('name', this.ruleForm.userName);
            this.$store.dispatch('id', this.b.substr(1));
            this.$router.push("/");
          } else {
            alert("登录失败！")
            return false;
          }
        });

          }

        );
        
 
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }

</script>

<style scoped>


</style>
