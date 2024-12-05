<template>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="#">
			<h1>注册界面</h1>
			<input type="text" placeholder="用户名" v-model="userName"/>
			<input type="email" placeholder="邮箱"  v-model="userEmail"/>
			<input type="phone" placeholder="手机号" v-model="userPhone"/>
			<input type="password" placeholder="用户密码" v-model="userPwd" />
			<button @click="doregister()">注 册</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="#">
			<h1>登录页面</h1>
			<transition name="social-fade">
			<div class="social-container" v-if="isPhoneValidated&&userPhone!=null" :style="{ backgroundImage: 'url(' + user_icon + ')', backgroundSize: 'cover'}">
			</div>
			</transition>
			<span v-if="showIcon">Hi,你的头像很COOL!</span>
			<input type="phone" placeholder="手机号"  v-model="userPhone"  @blur="checkIfRegistered()"/>
			<input type="password" placeholder="用户密码"  v-model="userPwd"/>
			<a href="#">忘记密码?</a>
			<button @click="dologin()">登 录</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>欢迎回归!</h1>
				<p>为了与我们保持联系，请使用个人信息登录！</p>
				<button class="ghost" id="signIn">GOTO登录</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>你好, 朋友!</h1>
				<p>输入您的个人详细信息并开始我们的旅程！</p>
				<button class="ghost" id="signUp">GOTO注册</button>
			</div>
		</div>
	</div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
		userName:'',
		userEmail:'',
		userPhone: '',
		userPwd: '',
		user_icon: '',
		isPhoneValidated: false,
		showIcon:false,
    };
  },
  mounted() {
    const container = document.getElementById('container');
    const signInButton = document.getElementById('signIn');
    signInButton.addEventListener('click', () => {
      container.classList.remove("right-panel-active");
    });
    const signUpButton = document.getElementById('signUp');
    signUpButton.addEventListener('click', () => {
      container.classList.add("right-panel-active");
    });
  },
  methods: {
	async doregister() {
  try {
    const response = await axios.post('http://localhost:9961/user/register', {
      userName: this.userName,  // 注意这里应该是 this.userName
      userEmail: this.userEmail,
      userPhone: this.userPhone,
      userPwd: this.userPwd,
    });
    // 检查后端返回的状态码
    if (response.data.code === 200) {
      // 注册成功的处理逻辑
      this.$message({
        message: this.userName + '用户，注册成功！GOTO登录！',
        type: 'success',
      });
    } else {
      // 注册失败的处理逻辑
      this.$message.error('注册失败，请检查用户名密码！');
    }
  } catch (error) {
    console.error(error);
  }
},
    async dologin() {
      // 将 signInButton 的定义移到外部
      const signInButton = document.getElementById('signIn');
      try {
        const response = await axios.post('http://localhost:9961/user/login', {
          userPhone: this.userPhone,
          userPwd: this.userPwd,
        }, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        });
        // 检查后端返回的状态码
        if (response.data.code === 200) {
          // 登录成功的处理逻辑
		this.$message({
          message: '欢迎'+this.userName+'!登陆成功!',
          type: 'success'
        });
        //   console.log('Login successful');
		window.localStorage.setItem("token",response.data.msg);
		 // 使用 $router.push 跳转到 '/index' 页面，并将 token 作为参数传递
		 this.$router.push({ path: '/index'});
        } 
		if(response.data.code === 500){
			this.$message.error('登陆失败!请检查用户名密码!');
          // 登录失败的处理逻辑
        //   console.error('Login failed:', response.data);
		}
      } catch (error) {
        console.error(error);
      }
    },
	async checkIfRegistered() {
  // 检查是否已经发送过验证请求，避免重复发送
  if (!this.isPhoneValidated) {
    try {
      const response = await axios.get('http://localhost:9961/user/checkUser', {
        params: {
          userPhone: this.userPhone,
        },
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      });

      // 检查后端返回的状态码
      if (response.data.code === 200) {
        // 登录成功的处理逻辑
        this.isPhoneValidated = true;
		this.userName = response.data.loginUser.userName;
		this.user_icon = response.data.loginUser.icon;
        console.log(response.data.loginUser);
      } else if (response.data.code === 500) {
        this.isPhoneValidated = false;
        console.log(response.data);
      }
    } catch (error) {
      console.error(error);
    }
  }
}

}
}
</script>
<style scoped>
/* 样式可以根据需要进行自定义 */

@import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
* {
	box-sizing: border-box;
}

body {
	background: #f6f5f7;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	font-family: 'Montserrat', sans-serif;
	height: 100vh;
	margin: -20px 0 50px;
}

h1 {
	font-weight: bold;
	margin: 0;
}

h2 {
	text-align: center;
}

p {
	font-size: 14px;
	font-weight: 100;
	line-height: 20px;
	letter-spacing: 0.5px;
	margin: 20px 0 30px;
}

span {
	font-size: 12px;
}

a {
	color: #333;
	font-size: 14px;
	text-decoration: none;
	margin: 15px 0;
}

button {
	border-radius: 20px;
	border: 1px solid #FF4B2B;
	background-color: #FF4B2B;
	color: #FFFFFF;
	font-size: 12px;
	font-weight: bold;
	padding: 12px 45px;
	letter-spacing: 1px;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
}

button:active {
	transform: scale(0.95);
}

button:focus {
	outline: none;
}

button.ghost {
	background-color: transparent;
	border-color: #FFFFFF;
}

form {
	background-color: #FFFFFF;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 50px;
	height: 100%;
	text-align: center;
}

input {
	background-color: #eee;
	border: none;
	padding: 12px 15px;
	margin: 8px 0;
	width: 100%;
}

.container {
  margin:auto;
  margin-top: 100px;
	background-color: #fff;
	border-radius: 10px;
  	box-shadow: 0 14px 28px rgba(0,0,0,0.25), 
			0 10px 10px rgba(0,0,0,0.22);
	position: relative;
	overflow: hidden;
	width: 768px;
	max-width: 100%;
	min-height: 480px;
}

.form-container {
	position: absolute;
	top: 0;
	height: 100%;
	transition: all 0.6s ease-in-out;
}

.sign-in-container {
	left: 0;
	width: 50%;
	z-index: 2;
}

.container.right-panel-active .sign-in-container {
	transform: translateX(100%);
}

.sign-up-container {
	left: 0;
	width: 50%;
	opacity: 0;
	z-index: 1;
}

.container.right-panel-active .sign-up-container {
	transform: translateX(100%);
	opacity: 1;
	z-index: 5;
	animation: show 0.6s;
}

@keyframes show {
	0%, 49.99% {
		opacity: 0;
		z-index: 1;
	}
	
	50%, 100% {
		opacity: 1;
		z-index: 5;
	}
}

.overlay-container {
	position: absolute;
	top: 0;
	left: 50%;
	width: 50%;
	height: 100%;
	overflow: hidden;
	transition: transform 0.6s ease-in-out;
	z-index: 100;
}

.container.right-panel-active .overlay-container{
	transform: translateX(-100%);
}

.overlay {
	background: #FF416C;
	background: -webkit-linear-gradient(to right, #FF4B2B, #FF416C);
	background: linear-gradient(to right, #FF4B2B, #FF416C);
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0 0;
	color: #FFFFFF;
	position: relative;
	left: -100%;
	height: 100%;
	width: 200%;
  	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
  	transform: translateX(50%);
}

.overlay-panel {
	position: absolute;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 40px;
	text-align: center;
	top: 0;
	height: 100%;
	width: 50%;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.overlay-left {
	transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
	transform: translateX(0);
}

.overlay-right {
	right: 0;
	transform: translateX(0);
}

.container.right-panel-active .overlay-right {
	transform: translateX(20%);
}

.social-container {
	margin: 20px 0;
	border: 1px solid #DDDDDD;
	border-radius: 50%;
	display: inline-flex;
	justify-content: center;
	align-items: center;
	margin: 0 5px;
	height: 80px;
	width: 80px;
}

footer {
    background-color: #222;
    color: #fff;
    font-size: 14px;
    bottom: 0;
    position: fixed;
    left: 0;
    right: 0;
    text-align: center;
    z-index: 999;
}

footer p {
    margin: 10px 0;
}

footer i {
    color: red;
}

footer a {
    color: #3c97bf;
    text-decoration: none;
}
.social-fade-enter-active, .social-fade-leave-active {
  transition: opacity 0.5s, transform 0.5s;
}

.social-fade-enter, .social-fade-leave-to {
  opacity: 0;
  transform: scaleY(0);
}


</style>
